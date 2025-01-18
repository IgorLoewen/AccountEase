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

1. Откройте файл `index.html` в браузере:
   ```bash
   open docs/index.html
   ```

### Запуск тестов для анотоции @Tag с классификацией юнит, для моих юнит тестов:
mvn test -DincludeTags=unit  

### Запуск JaCoCo теста для каждого теста отельно. Стираем папку docs и таргет
### и после генерации отчёта, открывается JaCoCo отчёт:
rm -rf target Docs && mvn verify -Dtest=UniqueValuesProcessorTest#testGetUniqueValues_NormalCase && open Docs/index.html





mvn verify -Dtest=

open docs/index.html