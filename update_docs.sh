#!/bin/bash

# 1. Дописываем остальные группы экранов в MD
cat >> /storage/emulated/0/Documents/GEOBLINKER_KMP_FINAL/БЕТОН/GEOBLINKER_SCREENS.md <<MD

## ГРУППА В: МОНИТОРИНГ И ДЕТАЛИ (CORE)
8. **MainScreen**: Нижняя навигация (Карта / Список / Настройки).
9. **ListScreen**: Карточки авто. Показывает: Имя, IMEI, статус (Online/Offline), иконку m_0-m_14.
10. **MapScreen**: MapLibre. Кластеризация маркеров. Пересчет координат (raw/10^6).
11. **DeviceDetailScreen**: Состояние зажигания, скорость (км/ч / 3.6), пробег, адрес.
12. **TrajectoryScreen**: Календарь + отрисовка пути устройства за период.
13. **SignalListScreen**: Список системных алертов (SOS, превышение скорости, выход из геозоны).

## ГРУППА Г: НАСТРОЙКИ (SETTINGS)
14. **SettingsMain**: Управление профилем.
15. **PhoneSettings**: Привязка мастер-номера для SMS-команд.
16. **NotificationSettings**: Переключатели Push-уведомлений.
17. **UnitDistanceSettings**: Выбор единиц (Метрическая/Имперская).
18. **NameSettings**: Смена ника устройства.
19. **EmailSettings**: Смена почты аккаунта.
20. **DeleteAccount**: Процедура удаления с подтверждением.

## ГРУППА Д: ФИНАНСЫ И ПОДДЕРЖКА
21. **SubscriptionOne**: Экран тарифов (Бронза/Серебро/Золото).
22. **SubscriptionTwo**: Выбор способа оплаты.
23. **SubscriptionReady**: Экран успеха/чека.
24. **SupportScreen**: Чат с техподдержкой ibronevik.
25. **AboutScreen**: Версия приложения и правовая информация.
MD

# 2. Обновляем JSON расширенными данными по навигации и маркерам
cat > /storage/emulated/0/Documents/GEOBLINKER_KMP_FINAL/БЕТОН/GEOBLINKER_DOCS.json <<JSON
{
  "project": "GeoBlinker KMP",
  "version": "1.0.91",
  "tech_stack": {
    "core": "Kotlin Multiplatform 1.9.23",
    "ui": "Compose Multiplatform (Material 3 Custom)",
    "navigation": "Voyager",
    "network": "Ktor (Double Stack: Bronevik + GPS666)"
  },
  "logic_rules": {
    "coordinates": "raw / 1000000.0",
    "speed": "kmh / 3.6",
    "auth_sequence": ["ibronevik_auth", "gps666_login", "sid_retrieval", "device_sync"]
  },
  "marker_map": {
    "0": "Standard Sedan",
    "1": "Blue Hatchback",
    "2": "Heavy Truck",
    "3": "Excavator",
    "4": "Motorbike",
    "5": "Person",
    "6": "Animal/Cow",
    "7": "Boat",
    "8": "Aircraft",
    "9": "Bus",
    "10": "Trailer",
    "11": "Bicycle",
    "12": "Pickup",
    "13": "Van",
    "14": "Crane"
  },
  "screen_count": 25,
  "api_endpoints": {
    "main": "https://ibronevik.ru/taxi/c/0/api/v1/",
    "tracker": "https://www.gps666.net/mapi"
  }
}
JSON

echo "✅ Документация полностью обновлена: 25 экранов и маппинг маркеров в базе."
