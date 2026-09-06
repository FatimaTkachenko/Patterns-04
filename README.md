# Заказ доставки карты (паттерны)

[![Build and Test](https://github.com/FatimaTkachenko/Patterns-04/actions/workflows/build.yml/badge.svg)](https://github.com/FatimaTkachenko/Patterns-04/actions/workflows/build.yml)

Автотесты для формы заказа доставки карты с перепланированием даты.

Используемые паттерны:
- **Data Class** (Lombok)
- **Utility / Generator class** (Faker)
- **Page Object** (Selenide)

## Запуск
```bash
java -jar artifacts/app-replan-delivery.jar &
./gradlew test -Dselenide.headless=true