# GeoBlinker - Legacy Resources

## Описание проекта
GeoBlinker - приложение для отслеживания GPS-трекеров и автомобилей с интеграцией IMEI-устройств.

---

## 1. ЭКРАНЫ (Screens)

### Основные экраны
- **MainScreen** - главный экран приложения
- **MapScreen** - карта с отображением устройств
- **ListScreen** - список устройств
- **NotificationsScreen** - уведомления
- **ProfileScreen** - профиль пользователя

### Экраны устройств
- **DeviceOneScreen** - первый шаг добавления устройства
- **DeviceTwoScreen** - второй шаг добавления устройства  
- **DeviceThreeScreen** - третий шаг добавления устройства
- **DeviceListSignalScreen** - список сигналов устройства
- **DeviceDetachOneScreen** - отвязка устройства (шаг 1)
- **DeviceDetachTwoScreen** - отвязка устройства (шаг 2)

### Экраны привязки
- **BindingOneScreen** - привязка устройства (шаг 1)
- **BindingTwoScreen** - привязка устройства (шаг 2)
- **BindingThreeScreen** - привязка устройства (шаг 3)

### Экраны настроек
- **SettingsScreen** - основные настройки
- **PhoneSettingsScreen** - настройки телефона
- **NotificationSettingsScreen** - настройки уведомлений
- **NameSettingsScreen** - редактирование имени
- **EmailSettingsScreen** - редактирование email
- **DeleteAccountSettingsScreen** - удаление аккаунта
- **ConfirmationCodeSettingsScreen** - подтверждение кода
- **UnitDistanceSettingsScreen** - единицы измерения расстояния

### Экраны подписки
- **SubscriptionOneScreen** - выбор подписки
- **SubscriptionTwoScreen** - оплата подписки
- **SubscriptionReadyScreen** - подтверждение подписки

### Прочие экраны
- **JournalSignalsScreen** - журнал сигналов
- **ChatsScreen** - чаты техподдержки
- **MakeRequestScreen** - создание запроса
- **FrequentQuestScreen** - FAQ
- **FrequentQuestionsScreen** - расширенные FAQ
- **AboutAppScreen** - о приложении
- **AboutCompanyScreen** - о компании
- **AboutCompanyItemScreen** - детали о компании
- **IconChooserScreen** - выбор иконки для устройства

---

## 2. API ENDPOINTS

### Основной API (ibronevik.ru)
**Base URL**: `https://ibronevik.ru/taxi/c/0/api/v1/`

#### Аутентификация
- `POST /register` - регистрация пользователя
- `POST /auth` - авторизация
- `POST /token/authorized` - получение токена по hash
- `GET /token` - получение hash по token

#### Пользователи
- `POST /user/{userName}` - редактирование данных пользователя
- `POST /user` - обновление пользователя

#### Автомобили/Устройства
- `POST /car` - добавление автомобиля
- `POST /user/authorized/car` - получение всех автомобилей
- `POST /car/{cId}` - обновление автомобиля

#### Подписки и платежи
- `POST /subscription/create` - создание подписки
- `POST /subscription/get` - получение подписок
- `POST /payment/create` - создание платежа
- `POST /payment/get` - получение информации о платеже
- `GET /data` - получение тарифов

#### Техподдержка
- `POST /mail/1/send/` - отправка email в техподдержку

#### Локализация и константы
- `GET /data?data.lang_vls={langId}` - получение языковых данных
- `GET /data?data.site_constants={s}` - получение констант сигналов устройств

### IMEI API (gps666.net)
**Base URL**: `https://www.gps666.net/mapi`

#### Аутентификация IMEI
- `POST /mapi` 
  - module: "user", func: "Login" 
  - Возвращает `sid` (session ID) и `family`

#### Устройства IMEI
- `POST /mapi?sid={sid}`
  - module: "family", func: "GetDeviceList" - список устройств
  - module: "device", func: "Add" - добавление устройства
  - module: "device", func: "GetDetail" - детали устройства (координаты, скорость)

#### Сигналы и траектория
- `POST /mapi?sid={sid}`
  - module: "signal", func: "GetSignalList" - список сигналов
  - module: "trajectory", func: "GetTrajectory" - траектория движения

---

## 3. СПЕЦИФИЧЕСКИЕ АЛГОРИТМЫ

### 3.1 Валидация IMEI
**Местоположение**: `DeviceViewModel.kt:224-260`

```kotlin
fun checkImei(imei: String) {
    // Проверка на дубликат
    if (devices.any { it.imei == imei && it.isConnected == 1L }) {
        return Error("IMEI уже привязан")
    }
    
    // Добавление устройства через API
    val res = apiImei.add(
        sid,
        RequestImei(
            module = "device",
            func = "Add",
            params = AddParamsImei(
                info = listOf(mapOf("imei" to imei.toLong())),
                sgid = sgid
            )
        )
    )
    
    if (res.items.isEmpty()) throw Exception("Empty response")
    
    // Получение simei для нового устройства
    val newSimei = res.items[0].simei
}
```

**Ключевые моменты**:
- IMEI хранится как строка, но отправляется как Long
- Проверка на существующую привязку через флаг `isConnected`
- После добавления получаем `simei` (internal device ID)

### 3.2 Расчет скорости
**Местоположение**: `DeviceViewModel.kt:185-222`

```kotlin
suspend fun updateLocationDevices() {
    val res = apiImei.getDetail(
        sid,
        RequestImei(
            module = "device",
            func = "GetDetail",
            params = GetDetailParamsImei(simei = device.simei)
        )
    )
    
    // Парсинг JSON внутри JSON
    val pos = json.decodeFromString<PosData>(res.posString)
    
    device.copy(
        lat = pos.lat / 1e6,  // Координаты в микроградусах
        lng = pos.lon / 1e6,
        speed = pos.speed / 3.6  // Преобразование км/ч в м/с
    )
}
```

**Ключевые моменты**:
- Скорость приходит в км/ч, конвертируется в м/с делением на 3.6
- Координаты приходят в микроградусах (умножены на 10^6)
- Данные позиции приходят как JSON-строка внутри JSON (`posString`)

### 3.3 Форматирование номера телефона
**Местоположение**: `CustomTextField.kt:102-112`

```kotlin
fun formatPhoneNumber(phoneNumber: String): String {
    val formatted = StringBuilder()
    
    phoneNumber.forEachIndexed { index, c ->
        when (index) {
            1, 4, 7, 9 -> formatted.append(" $c")
            else -> formatted.append(c)
        }
    }
    return formatted.toString()
}
```

**Формат**: `9 XXX XXX XX XX` (российский формат)

### 3.4 Синхронизация данных устройств
**Местоположение**: `DeviceViewModel.kt:81-102`

```kotlin
private fun startDataSync() {
    try {
        repository.clearAllDevices()
        
        // 1. Получение автомобилей из основного API
        fetchCars()
        
        // 2. Логин в IMEI систему
        loginImei()  // Получаем sid, sidFamily
        
        // 3. Получение IMEI устройств и связывание с автомобилями
        fetchImeiDevices()  // Получаем simei для каждого imei
        
        // 4. Обновление локаций всех устройств
        updateLocationDevices()
        
    } catch (e: Exception) {
        println("Sync error: $e")
    }
}
```

**Последовательность**:
1. Очистка локальной БД
2. Загрузка машин из основного API (imei, name, markerId и т.д.)
3. Авторизация в IMEI системе → получение `sid`, `sidFamily`
4. Связывание IMEI → получение `simei` для каждого устройства
5. Получение актуальных координат и скорости

### 3.5 Модели данных устройств

#### Device (основная модель)
```kotlin
data class Devices(
    val imei: String,           // IMEI устройства
    val id: String,             // ID из основного API
    val name: String,           // Название устройства
    val isConnected: Long,      // 0/1 - привязано ли
    val bindingTime: String,    // Время привязки
    val registrationPlate: String,
    val deviceType: String,     // Тип: tracker_model2, etc.
    val markerId: Long,         // ID маркера на карте (0-14)
    val simei: String,          // Internal ID в IMEI системе
    val powerRate: Long,        // Уровень батареи
    val signalRate: Long,       // Уровень сигнала
    val speed: Double,          // Скорость в м/с
    val lat: Double,            // Широта
    val lng: Double,            // Долгота
    val typeStatus: String,     // "Available" и т.д.
    val breakdownForecast: String?,
    val maintenanceRecommendations: String?,
    val modelName: String
)
```

#### TrajectoryImeiItem (точка траектории)
```kotlin
data class TrajectoryImeiItem(
    val lat: Double,
    val lng: Double,
    val speed: Double,  // В км/ч
    val time: Long      // Unix timestamp
)
```

### 3.6 Алгоритм получения траектории
**Параметры запроса**:
```kotlin
data class ParamsTrajectoryImei(
    val limitSize: Int = 2000,         // Максимум точек
    val simei: String,                  // ID устройства
    val timeBegin: Long = 1,            // Unix timestamp начала
    val timeEnd: Long = currentTime     // Unix timestamp конца
)
```

---

## 4. КЛЮЧЕВЫЕ КОНСТАНТЫ И НАСТРОЙКИ

### Session Storage (SharedPreferences/Settings)
- `token` - токен авторизации основного API
- `hash` - хэш пользователя
- `sid` - session ID для IMEI API
- `sidFamily` - ID семьи устройств в IMEI системе
- `unitsDistance` - единицы расстояния (Boolean: true=км, false=мили)
- `updateMap` - автообновление карты

### Типы устройств (deviceType)
- `tracker_model2` - основной тип трекера
- Другие типы определяются по `markerId` (0-14)

### Маркеры на карте (markerId)
Доступны иконки m_0 до m_14:
- m_0 - круглый маркер
- m_1 - автомобиль
- m_2 - мотоцикл
- m_3 - грузовик
- m_4 - камион
- m_5 - автобус
- m_6 - бульдозер
- m_7 - трактор
- m_8 - прицеп
- m_9 - человек
- m_10 - ребенок
- m_11 - кошка
- m_12 - собака
- m_13 - лошадь
- m_14 - корова

---

## 5. СТРУКТУРА ДАННЫХ API

### Основной API (Form Data)
Все запросы используют `application/x-www-form-urlencoded`:
```kotlin
mapOf(
    "token" to token,
    "u_hash" to hash,
    // другие параметры
)
```

### IMEI API (JSON)
Все запросы используют `application/json`:
```kotlin
RequestImei(
    module: String,  // "user", "family", "device", "signal", "trajectory"
    func: String,    // Название функции
    params: Any      // Параметры конкретной функции
)
```

---

## 6. БАЗА ДАННЫХ (SQLDelight)

### Таблицы
- **Device** - устройства
- **Signal** - сигналы/уведомления
- **News** - новости
- **MessageTechSupport** - сообщения техподдержки
- **ChatTechSupport** - чаты техподдержки
- **TypeSignal** - типы сигналов

---

## 7. АРХИТЕКТУРНЫЕ ПАТТЕРНЫ

### Repository Pattern
- `ApiRepository` - работа с основным API
- `ApiImeiRepository` - работа с IMEI API
- `Repository` - работа с локальной БД
- `NotificationRepository` - управление уведомлениями
- `SubscriptionRepository` - управление подписками
- `TechSupportRepository` - техподдержка

### MVVM
- ViewModels: `DeviceViewModel`, `JournalViewModel`, `ProfileViewModel`, `NotificationViewModel`, `SubscriptionViewModel`, `ChatsViewModel`
- States: `DeviceState`, `ProfileState`, `NotificationState`, `SubscriptionState`, `JournalState`, `ChatsState`

### Dependency Injection
Используется Koin

---

## 8. ВАЖНЫЕ ЗАМЕЧАНИЯ ДЛЯ KMP МИГРАЦИИ

1. **Координаты**: Всегда делить на 10^6 при получении
2. **Скорость**: Конвертировать из км/ч в м/с (/ 3.6)
3. **IMEI**: Хранить как String, отправлять как Long
4. **Session Management**: Нужен `sid` для всех IMEI операций
5. **Синхронизация**: Последовательность критична (fetchCars → loginImei → fetchImeiDevices → updateLocationDevices)
6. **Парсинг JSON в JSON**: `posString` содержит вложенный JSON, требует отдельного парсинга
7. **Автообновление**: Реализовать через Workers/Background tasks для периодического обновления локаций

---

## РЕЗЮМЕ ДЛЯ НОВОЙ АРХИТЕКТУРЫ

### Что переиспользовать напрямую:
- Модели данных (Devices, TrajectoryImeiItem, etc.)
- API endpoints структура
- Алгоритмы конвертации координат и скорости
- Последовательность синхронизации данных
- Форматирование номера телефона
- Структуру RequestImei для IMEI API

### Что адаптировать:
- ViewModels → современная KMP архитектура
- Repository pattern → возможно использовать UseCase layer
- SQLDelight queries → оптимизировать для KMP
- Settings management → KMP-совместимое решение
- Workers → KMP background tasks solution

### Что пересмотреть:
- Обработка ошибок (сейчас через try-catch + println)
- Логирование (добавить структурированное)
- Валидация данных (добавить больше проверок)
- Кэширование (добавить стратегию)
- Offline-first подход
