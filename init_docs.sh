#!/bin/bash

# 1. Создаем JSON с техданными
cat > /storage/emulated/0/Documents/GEOBLINKER_KMP_FINAL/БЕТОН/GEOBLINKER_DOCS.json <<JSON
{
  "project": "GeoBlinker KMP",
  "tech_stack": {
    "core": "Kotlin Multiplatform 1.9.23",
    "ui": "Compose Multiplatform (Material 3 Custom)",
    "navigation": "Voyager",
    "network": "Ktor (Double Stack: Bronevik + GPS666)"
  },
  "logic_rules": {
    "coordinates": "raw / 1000000.0",
    "speed": "kmh / 3.6",
    "gps_auth": {
      "login": "georule",
      "pass_hash": "8bbe1a8ed834b27261f2a4dfb1418ae7"
    }
  },
  "markers": {
    "m_0": "Default Car",
    "m_6": "Cow/Pet",
    "m_7": "Ship",
    "m_1": "Blue Car"
  }
}
JSON

# 2. Создаем MD с описанием экранов
cat > /storage/emulated/0/Documents/GEOBLINKER_KMP_FINAL/БЕТОН/GEOBLINKER_SCREENS.md <<MD
# 📱 КАРТА ЭКРАНОВ GEOBLINKER

## ГРУППА А: ВХОД И РЕГИСТРАЦИЯ
1. **WelcomeScreen**: Лого, кнопки Вход/Регистрация.
2. **LoginScreen**: Поля Телефон/Пароль. API: ibronevik.ru/auth.
3. **RegistrationScreen**: Имя, Тел, Пароль, Повтор.
4. **RestorePasswordScreen**: Восстановление по Email.

## ГРУППА Б: ПРИВЯЗКА (BINDING)
5. **BindingOne**: Ввод IMEI (15 цифр) или QR.
6. **BindingTwo**: Выбор иконки (m_0-m_14) и Имени.
7. **BindingThree**: Проверка связи и активация.
MD

echo "✅ Базовая документация создана в папке БЕТОН"
