# Patterns Homework

## Задача №1: Заказ доставки карты (изменение даты)
Автотест для проверки функции перепланирования встречи.

## Задача №2: Тестовый режим
Автотесты для API создания пользователей в тестовом режиме.

## Время, затраченное на тестирование
- Ручное тестирование (минут): 30
- Автоматизация (минут): 90

## Как запустить
1. Запустить SUT для первой задачи: `java -jar artifacts/app-replan-delivery.jar`
2. Запустить SUT для второй задачи: `java -jar artifacts/app-ibank.jar -P:profile=test`
3. Выполнить тесты: `./gradlew clean test`

## CI
[![Build Status](https://github.com/ВАШ_ЛОГИН/Patterns-Homework/actions/workflows/build.yml/badge.svg)](https://github.com/ВАШ_ЛОГИН/Patterns-Homework/actions/workflows/build.yml)