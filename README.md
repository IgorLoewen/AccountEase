# Instructions for Running Allure and JaCoCo Reports via Terminal (Mac OS)

## Running Allure Tests and Reports

### Running Tests with Allure Report Generation:
```bash
mvn clean test
```

### Opening Allure Report Locally:
```bash
allure serve target/allure-results
```

---

## Running JaCoCo Reports

### Running Tests with JaCoCo Report Generation:
```bash
mvn clean verify 
```

### Opening JaCoCo Report Locally:
Open the generated HTML report in the `docs` folder:

1. Open the `index.html` file in the browser:
   ```bash
   open docs/index.html
   ```

### Running Tests for @Tag Annotation with Unit Classification for Unit Tests:
```bash
mvn test -DincludeTags=unit  
```

### Running JaCoCo Test for Each Test Separately:
#### This command removes the `docs` and `target` folders, runs the test, generates the report, and then opens it:
```bash
rm -rf target docs && mvn verify -Dtest=UniqueValuesProcessorTest#testGetUniqueValues_NormalCase && open docs/index.html
