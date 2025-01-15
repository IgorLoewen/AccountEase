# Инструкция по вызову Allure и JaCoCo отчетов через терминал (Mac OS)

## Вызов Allure тестов и отчетов

### Запуск тестов с генерацией Allure отчета:
```bash
mvn clean test
```

### Открытие Allure отчета локально:
```bash
allure serve target/allure-results
```

---

## Вызов JaCoCo отчетов

### Запуск тестов с генерацией JaCoCo отчета:
```bash
mvn clean verify
```

### Открытие JaCoCo отчета локально:
Откройте сгенерированный HTML-отчет в папке `docs`:
1. Перейдите в папку с отчетом:
   ```bash
   cd docs
   ```
2. Откройте файл `index.html` в браузере:
   ```bash
   open docs/index.html
   ```
