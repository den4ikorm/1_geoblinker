# 🎯 ЧЁТКИЙ ПЛАН РЕФАКТОРИНГА GEOBLINKER
## От 44 экранов к production-ready приложению

---

## 📊 ТЕКУЩЕЕ СОСТОЯНИЕ

- **Экранов объявлено:** 44
- **Реально реализовано:** 
- **Дублей:** 1
- **Требуют объединения:** 15
- **Целевое количество:** 26-28 экранов

---

# 🚀 ПЛАН ДЕЙСТВИЙ (5 ЭТАПОВ)

---

## ЭТАП 0: ПОДГОТОВКА (1 день)

### ✅ Задачи:
1. Создать ветку `refactor/screen-structure`
2. Сделать backup текущей версии
3. Создать структуру папок
4. Зафиксировать чеклист прогресса

### 📋 Команды:

```bash
# 1. Создать ветку
git checkout -b refactor/screen-structure

# 2. Backup
git tag backup-before-refactor-$(date +%Y%m%d)

# 3. Создать структуру папок (выполнить скрипт из предыдущего анализа)
bash create_structure_command.sh

# 4. Коммит структуры
git add .
git commit -m "Step 0: Create feature-based folder structure"
git push origin refactor/screen-structure
```

### ✅ Критерий завершения:
- [ ] Ветка создана
- [ ] Структура папок готова
- [ ] Чеклист создан в PROGRESS.md

---

## ЭТАП 1: ОЧИСТКА И ПОДГОТОВКА (2 дня)

### 🎯 Цель: Убрать мусор, создать фундамент

### 1.1 Удалить дубликаты (30 мин)

```bash
# Удалить FrequentQuestScreen из Screens.kt
# Оставить только FrequentQuestionsScreen

# Коммит
git add .
git commit -m "Step 1.1: Remove duplicate FrequentQuestScreen"
```

### 1.2 Создать базовые контракты (2 часа)

Для каждой фичи создать файлы:
- `{Feature}Contract.kt` - State, Event, Effect
- `{Feature}ViewModel.kt` - базовая структура
- `{Feature}Screen.kt` - композабл-заглушка

**Пример для Auth:**

```kotlin
// auth/AuthContract.kt
package org.example.geoblinker.presentation.features.auth

data class AuthState(
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class AuthEvent {
    data class OnPhoneChanged(val phone: String) : AuthEvent()
    object OnConfirmClick : AuthEvent()
}

sealed class AuthEffect {
    data class ShowError(val message: String) : AuthEffect()
    object NavigateToMain : AuthEffect()
}
```

### 1.3 Обновить BaseViewModel (1 час)

```kotlin
// core/base/BaseViewModel.kt
package org.example.geoblinker.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State, Event, Effect> : ViewModel() {
    
    abstract val initialState: State
    
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state
    
    private val _effect = Channel<Effect>()
    val effect = _effect.receiveAsFlow()
    
    abstract fun handleEvent(event: Event)
    
    protected fun updateState(update: State.() -> State) {
        _state.value = _state.value.update()
    }
    
    protected suspend fun sendEffect(effect: Effect) {
        _effect.send(effect)
    }
}
```

### 1.4 Настроить DI (1 час)

```kotlin
// presentation/di/vmModule.kt
package org.example.geoblinker.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    // Существующие
    viewModel { DeviceViewModel() }
    viewModel { ProfileViewModel() }
    
    // Новые (добавлять по мере реализации)
    // viewModel { AuthViewModel(get()) }
}
```

### ✅ Критерий завершения:
- [ ] FrequentQuestScreen удалён
- [ ] BaseViewModel создан
- [ ] Структура Contract для всех фич создана
- [ ] DI модуль настроен

---

## ЭТАП 2: РЕАЛИЗАЦИЯ ПО ПРИОРИТЕТАМ (30 дней)

### 📌 ПРИОРИТЕТ 1: КРИТИЧНЫЕ ЭКРАНЫ (7 дней)

#### 2.1 AUTH (Авторизация) - 2 дня

**Экраны:**
1. `PhoneScreen` - ввод телефона
2. `ConfirmationCodeScreen` - ввод кода

**План:**
```
День 1:
├── PhoneScreen.kt (UI + ViewModel)
├── PhoneContract.kt
├── Интеграция с API
└── Тесты навигации

День 2:
├── ConfirmationCodeScreen.kt
├── ConfirmationCodeContract.kt
├── Интеграция с API
└── E2E тест авторизации
```

**Коммиты:**
```bash
git commit -m "Step 2.1.1: PhoneScreen implementation"
git commit -m "Step 2.1.2: ConfirmationCodeScreen implementation"
git commit -m "Step 2.1.3: Auth flow integration test"
```

#### 2.2 MAP (Карта) - 2 дня

**Экраны:**
1. `MainScreen` - контейнер (✅ уже есть)
2. `MapScreen` - карта (✅ уже есть)

**План:**
```
День 1:
├── Рефакторинг MainScreen под новую архитектуру
├── Вынести widgets в отдельные файлы
└── Обновить State management

День 2:
├── Рефакторинг MapScreen
├── Оптимизация WebView
└── Тесты взаимодействия
```

**Коммиты:**
```bash
git commit -m "Step 2.2.1: MainScreen refactor to new architecture"
git commit -m "Step 2.2.2: MapScreen optimization"
```

#### 2.3 PROFILE (Профиль) - 2 дня

**Экраны:**
1. `ProfileScreen` - просмотр профиля
2. `ProfileEditScreen` - редактирование (объединить Name + Email)

**План:**
```
День 1:
├── ProfileScreen.kt
├── ProfileContract.kt
├── Интеграция с ProfileViewModel (уже есть)
└── UI компоненты

День 2:
├── ProfileEditScreen.kt (объединяет Name + Email)
├── ProfileEditContract.kt
├── Валидация полей
└── Сохранение изменений
```

**Коммиты:**
```bash
git commit -m "Step 2.3.1: ProfileScreen implementation"
git commit -m "Step 2.3.2: ProfileEditScreen (merged Name+Email)"
```

#### 2.4 LIST (Список устройств) - 1 день

**Экраны:**
1. `DevicesListScreen` (✅ уже есть ListScreen)

**План:**
```
День 1:
├── Рефакторинг ListScreen
├── Адаптация под новую архитектуру
├── Добавить фильтры/сортировку
└── Тесты
```

**Коммиты:**
```bash
git commit -m "Step 2.4.1: DevicesListScreen refactor with filters"
```

### ✅ Критерий завершения Приоритета 1:
- [ ] Авторизация работает end-to-end
- [ ] Карта и список переведены на новую архитектуру
- [ ] Профиль полностью функционален
- [ ] Все коммиты сделаны

---

### 📌 ПРИОРИТЕТ 2: CORE ФУНКЦИОНАЛ (10 дней)

#### 2.5 DEVICES (Устройства) - 4 дня

**Экраны:**
1. `DeviceDetailsScreen` - детали устройства (объединить One/Two/Three)
2. `DeviceSignalsScreen` - журнал сигналов
3. `DeviceDetachScreen` - отвязка устройства

**План:**
```
День 1-2: DeviceDetailsScreen
├── Создать TabLayout для секций (Info, Settings, History)
├── Перенести логику из DeviceOne/Two/Three
├── Unified State
└── Tests

День 3: DeviceSignalsScreen
├── UI списка сигналов
├── Фильтры по типу/дате
└── Пагинация

День 4: DeviceDetachScreen
├── Step indicator (2 шага)
├── Подтверждение
└── Обработка ошибок
```

**Коммиты:**
```bash
git commit -m "Step 2.5.1: DeviceDetailsScreen (merged One/Two/Three)"
git commit -m "Step 2.5.2: DeviceSignalsScreen with filters"
git commit -m "Step 2.5.3: DeviceDetachScreen with steps"
```

#### 2.6 DEVICE_BINDING (Привязка) - 2 дня

**Экраны:**
1. `BindingFlowScreen` - привязка устройства (объединить One/Two/Three)

**План:**
```
День 1:
├── Step-based navigation
├── Progress indicator
├── Step 1: Сканирование IMEI
└── Step 2: Выбор иконки

День 2:
├── Step 3: Настройки
├── Валидация
├── API интеграция
└── Success/Error handling
```

**Коммиты:**
```bash
git commit -m "Step 2.6.1: BindingFlowScreen Step 1-2"
git commit -m "Step 2.6.2: BindingFlowScreen Step 3 + integration"
```

#### 2.7 NOTIFICATIONS (Уведомления) - 2 дня

**Экраны:**
1. `NotificationsScreen` (✅ уже есть)
2. `SignalsJournalScreen`

**План:**
```
День 1:
├── Рефакторинг NotificationsScreen
├── Группировка по дате
└── Mark as read

День 2:
├── SignalsJournalScreen
├── Детальный просмотр сигнала
└── Фильтры
```

**Коммиты:**
```bash
git commit -m "Step 2.7.1: NotificationsScreen refactor"
git commit -m "Step 2.7.2: SignalsJournalScreen implementation"
```

#### 2.8 SUBSCRIPTION (Подписка) - 2 дня

**Экраны:**
1. `SubscriptionPlansScreen` - выбор плана
2. `SubscriptionPaymentScreen` - оплата
3. `SubscriptionSuccessScreen` - успех

**План:**
```
День 1:
├── SubscriptionPlansScreen (карточки планов)
├── Расчет цен
└── Выбор периода

День 2:
├── SubscriptionPaymentScreen (интеграция платежей)
├── SubscriptionSuccessScreen (анимация успеха)
└── E2E тест покупки
```

**Коммиты:**
```bash
git commit -m "Step 2.8.1: SubscriptionPlansScreen"
git commit -m "Step 2.8.2: Payment + Success screens"
```

### ✅ Критерий завершения Приоритета 2:
- [ ] Все операции с устройствами работают
- [ ] Привязка устройства функционирует
- [ ] Уведомления показываются корректно
- [ ] Подписка интегрирована

---

### 📌 ПРИОРИТЕТ 3: НАСТРОЙКИ И ПОДДЕРЖКА (8 дней)

#### 2.9 SETTINGS (Настройки) - 4 дня

**Экраны:**
1. `SettingsScreen` - главная
2. `NotificationSettingsScreen`
3. `UnitSettingsScreen`
4. `DeleteAccountScreen`
5. `IconChooserScreen`

**План:**
```
День 1-2: SettingsScreen + NotificationSettingsScreen
├── Навигация по настройкам
├── Toggle switches
└── Сохранение в локальное хранилище

День 3: UnitSettingsScreen + DeleteAccountScreen
├── Выбор единиц измерения
├── Подтверждение удаления аккаунта
└── API интеграция

День 4: IconChooserScreen
├── Grid иконок устройств
├── Preview
└── Применение
```

**Коммиты:**
```bash
git commit -m "Step 2.9.1: Main Settings screen"
git commit -m "Step 2.9.2: Notification + Unit settings"
git commit -m "Step 2.9.3: Delete account + Icon chooser"
```

#### 2.10 SUPPORT (Поддержка) - 3 дня

**Экраны:**
1. `SupportChatsScreen` - список чатов
2. `CreateRequestScreen` - создание запроса
3. `FAQScreen` - частые вопросы

**План:**
```
День 1: SupportChatsScreen
├── Список чатов
├── Статусы (открыт/закрыт)
└── Навигация в чат

День 2: CreateRequestScreen
├── Форма создания запроса
├── Загрузка файлов
└── API отправка

День 3: FAQScreen
├── Expandable list вопросов
├── Поиск
└── Полезные ссылки
```

**Коммиты:**
```bash
git commit -m "Step 2.10.1: SupportChatsScreen"
git commit -m "Step 2.10.2: CreateRequestScreen with file upload"
git commit -m "Step 2.10.3: FAQScreen with search"
```

#### 2.11 ABOUT (О приложении) - 1 день

**Экраны:**
1. `AboutAppScreen`
2. `AboutCompanyScreen`

**План:**
```
День 1:
├── AboutAppScreen (версия, лицензии, политика)
├── AboutCompanyScreen (контакты, реквизиты)
└── Deeplinks на внешние ресурсы
```

**Коммиты:**
```bash
git commit -m "Step 2.11: About screens (App + Company)"
```

### ✅ Критерий завершения Приоритета 3:
- [ ] Все настройки сохраняются
- [ ] Техподдержка функционирует
- [ ] О приложении заполнено

---

## ЭТАП 3: ИНТЕГРАЦИЯ И НАВИГАЦИЯ (5 дней)

### 3.1 Полная навигация (2 дня)

```kotlin
// Navigation.kt - полная реализация
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) MainScreen else PhoneScreen
    ) {
        // AUTH
        composable<PhoneScreen> { PhoneScreen(...) }
        composable<ConfirmationCodeScreen> { ConfirmationCodeScreen(...) }
        
        // MAIN
        composable<MainScreen> { MainScreen(...) }
        composable<MapScreen> { MapScreen(...) }
        composable<DevicesListScreen> { DevicesListScreen(...) }
        
        // DEVICES
        composable<DeviceDetailsScreen> { DeviceDetailsScreen(...) }
        composable<DeviceSignalsScreen> { DeviceSignalsScreen(...) }
        composable<DeviceDetachScreen> { DeviceDetachScreen(...) }
        composable<BindingFlowScreen> { BindingFlowScreen(...) }
        
        // PROFILE
        composable<ProfileScreen> { ProfileScreen(...) }
        composable<ProfileEditScreen> { ProfileEditScreen(...) }
        
        // SETTINGS
        composable<SettingsScreen> { SettingsScreen(...) }
        // ... все остальные
    }
}
```

### 3.2 Deep Links (1 день)

```kotlin
// Настройка deeplinks для каждого экрана
composable<DeviceDetailsScreen>(
    deepLinks = listOf(
        navDeepLink<DeviceDetailsScreen>(
            basePath = "geoblinker://device/{id}"
        )
    )
) { ... }
```

### 3.3 Back Stack Management (1 день)

- Настроить правильные переходы
- Обработать системную кнопку Back
- Single Top launch mode где нужно

### 3.4 Transition Animations (1 день)

```kotlin
enterTransition = slideIntoContainer(
    towards = AnimatedContentTransitionScope.SlideDirection.Left
)
exitTransition = slideOutOfContainer(
    towards = AnimatedContentTransitionScope.SlideDirection.Left
)
```

### ✅ Критерий завершения:
- [ ] Все экраны связаны навигацией
- [ ] Deep links работают
- [ ] Back stack корректный
- [ ] Анимации добавлены

---

## ЭТАП 4: ТЕСТИРОВАНИЕ И БАГИ (7 дней)

### 4.1 Unit Tests (2 дня)

```kotlin
// Для каждой ViewModel
class AuthViewModelTest {
    @Test
    fun `phone validation success`() { ... }
    
    @Test
    fun `confirmation code error handling`() { ... }
}
```

### 4.2 Integration Tests (2 дня)

```kotlin
// Навигация между экранами
@Test
fun `auth flow navigates correctly`() {
    // Phone -> Code -> Main
}

@Test
fun `device binding flow works end to end`() {
    // IMEI scan -> Icon -> Settings -> Success
}
```

### 4.3 UI Tests (2 дня)

```kotlin
@Test
fun `profile screen shows user data`() {
    composeTestRule.setContent {
        ProfileScreen(...)
    }
    composeTestRule.onNodeWithText("Иван Иванов").assertIsDisplayed()
}
```

### 4.4 Bug Fixing (1 день)

- Собрать все найденные баги
- Приоритизировать
- Исправить critical и high

### ✅ Критерий завершения:
- [ ] 80%+ test coverage для ViewModels
- [ ] Integration tests pass
- [ ] UI tests pass
- [ ] Critical bugs fixed

---

## ЭТАП 5: ФИНАЛИЗАЦИЯ (5 дней)

### 5.1 Code Review (2 дня)

- Проверка всех PR
- Рефакторинг дублирующегося кода
- Оптимизация производительности

### 5.2 Документация (2 дня)

```markdown
# Создать:
- README.md с описанием архитектуры
- ARCHITECTURE.md с диаграммами
- NAVIGATION.md с картой экранов
- CONTRIBUTING.md для разработчиков
```

### 5.3 Merge в main (1 день)

```bash
# Final check
./gradlew clean build
./gradlew test

# Merge
git checkout main
git merge refactor/screen-structure
git tag v2.0.0-refactored
git push origin main --tags
```

### ✅ Критерий завершения:
- [ ] Code review passed
- [ ] Документация готова
- [ ] Merged в main
- [ ] Tag создан

---

## 📊 TIMELINE SUMMARY

| Этап | Длительность | Результат |
|------|--------------|-----------|
| **0. Подготовка** | 1 день | Структура готова |
| **1. Очистка** | 2 дня | Фундамент создан |
| **2. Реализация** | 30 дней | Все экраны готовы |
| **3. Интеграция** | 5 дней | Навигация работает |
| **4. Тестирование** | 7 дней | Баги исправлены |
| **5. Финализация** | 5 дней | Production ready |
| **ИТОГО** | **50 дней (~2.5 месяца)** | **✅ Готово к релизу** |

---

## 🎯 ПРАВИЛА РАБОТЫ (НЕ СМЕЩАТЬСЯ С ПУТИ!)

### ✅ ДЕЛАТЬ:
1. **Один экран за раз** - не браться за следующий, пока текущий не готов
2. **Коммитить часто** - каждые 2-4 часа работы
3. **Тестировать сразу** - написал экран → написал тесты
4. **Следовать приоритетам** - не перепрыгивать этапы
5. **Документировать** - комментарии в коде, changelog

### ❌ НЕ ДЕЛАТЬ:
1. **Не добавлять новые фичи** (геозоны, маршруты и т.д.) до завершения
2. **Не менять архитектуру** в процессе - решили MVI, делаем MVI
3. **Не пропускать тесты** - "потом напишу" = никогда не напишу
4. **Не делать рефакторинг уже сделанного** - работает = не трогай
5. **Не отвлекаться на UI polish** - сначала функционал, потом красота

---

## 📋 ЕЖЕДНЕВНЫЙ CHECKLIST

```markdown
### Начало дня:
- [ ] git pull origin refactor/screen-structure
- [ ] Проверить PROGRESS.md - что делаю сегодня
- [ ] Запустить тесты - всё работает?

### Конец дня:
- [ ] Код написан
- [ ] Тесты написаны
- [ ] Тесты проходят
- [ ] git add . && git commit -m "..."
- [ ] git push origin refactor/screen-structure
- [ ] Обновить PROGRESS.md
- [ ] Записать блокеры (если есть)
```

---

## 🚨 БЛОКЕРЫ И РЕШЕНИЯ

| Проблема | Решение |
|----------|---------|
| **Нет дизайна для экрана** | Сделать минимальный UI, пометить TODO |
| **API не готов** | Использовать mock данные |
| **Не понятна бизнес-логика** | Задать вопрос → записать ответ → продолжить |
| **Сложный экран** | Разбить на подзадачи, делать по одной |
| **Хочется добавить фичу** | Записать в BACKLOG.md, вернуться после рефакторинга |

---

## 📈 ОТСЛЕЖИВАНИЕ ПРОГРЕССА

### PROGRESS.md (создать в корне проекта)

```markdown
# РЕФАКТОРИНГ PROGRESS

## Статус: В ПРОЦЕССЕ
## Прогресс: 12/28 экранов (43%)

### ✅ Готово (12):
- [x] PhoneScreen
- [x] ConfirmationCodeScreen
- [x] MainScreen
- [x] MapScreen
- [x] DevicesListScreen
- [x] ProfileScreen
- [x] ProfileEditScreen
- [x] DeviceDetailsScreen
- [x] DeviceSignalsScreen
- [x] DeviceDetachScreen
- [x] BindingFlowScreen
- [x] NotificationsScreen

### 🚧 В работе (1):
- [ ] SignalsJournalScreen (50% готово)

### ⏳ Осталось (15):
- [ ] SubscriptionPlansScreen
- [ ] SubscriptionPaymentScreen
- [ ] SubscriptionSuccessScreen
- [ ] SettingsScreen
- [ ] NotificationSettingsScreen
- [ ] UnitSettingsScreen
- [ ] DeleteAccountScreen
- [ ] IconChooserScreen
- [ ] SupportChatsScreen
- [ ] CreateRequestScreen
- [ ] FAQScreen
- [ ] AboutAppScreen
- [ ] AboutCompanyScreen

### 📊 Метрики:
- Время на экран: ~1.5 дня (среднее)
- Осталось дней: ~22
- Ожидаемая дата завершения: 26.02.2026
```

---

## 🎓 ИТОГОВЫЙ ЧЕКЛИСТ "ГОТОВНОСТИ К РЕЛИЗУ"

```markdown
### Функциональность:
- [ ] Все 26-28 экранов реализованы
- [ ] Навигация работает без багов
- [ ] Авторизация работает end-to-end
- [ ] Операции с устройствами работают
- [ ] Подписка интегрирована
- [ ] Уведомления приходят

### Качество кода:
- [ ] Нет дубликатов кода
- [ ] Все ViewModel следуют паттерну
- [ ] DI настроен корректно
- [ ] Нет хардкода строк (все в strings)
- [ ] Нет TODO в критичных местах

### Тесты:
- [ ] Unit tests coverage > 80%
- [ ] Integration tests pass
- [ ] UI tests для критичных flow
- [ ] Manual QA passed

### Документация:
- [ ] README.md обновлён
- [ ] ARCHITECTURE.md создан
- [ ] Комментарии в сложных местах
- [ ] Changelog ведётся

### Производительность:
- [ ] Нет утечек памяти
- [ ] Нет лагов при скролле
- [ ] Размер APK приемлемый
- [ ] Startup time < 3 сек

### Готовность:
- [ ] Merged в main
- [ ] Tag создан
- [ ] CI/CD проходит
- [ ] Готово к деплою
```

---

**ПОМНИ:** Лучше сделать 26 простых рабочих экранов, чем 44 недоделанных.

**ФОКУС:** Один экран за раз. Тесты. Коммит. Следующий.

**ЦЕЛЬ:** Production-ready приложение через 50 дней.

---

**Дата создания плана:** 04.02.2026  
**Проект:** GeoBlinker  
**Версия плана:** 1.0  
**Целевая дата завершения:** 26.03.2026
