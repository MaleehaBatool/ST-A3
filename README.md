# JUnit 5 Advanced Automation Framework

## Project Summary

A scalable, maintainable, and data-driven unit testing framework built with JUnit 5, Maven, JaCoCo, Apache POI, and OpenCSV.

---

## Tools Used

| Tool | Version | Purpose |
|------|---------|---------|
| Java | 11 | Runtime & Language |
| Maven | 3.9+ | Build & Dependency Management |
| JUnit 5 (Jupiter) | 5.10.0 | Unit Testing Framework |
| JaCoCo | 0.8.10 | Code Coverage |
| Apache POI | 5.2.3 | Excel (.xlsx) Data Reading |
| OpenCSV | 5.7.1 | CSV Data Reading |
| Maven Surefire | 3.1.2 | Parallel Test Execution |

---

## Project Structure

```
junit5-framework/
├── pom.xml
├── src/
│   ├── main/java/com/framework/
│   │   ├── model/
│   │   │   ├── BankAccount.java         # Bank account logic
│   │   │   └── Product.java             # Product model
│   │   ├── service/
│   │   │   ├── ShoppingCart.java        # Cart management
│   │   │   └── UserService.java         # User auth & roles
│   │   └── utils/
│   │       ├── MathUtils.java           # Math operations
│   │       ├── StringUtils.java         # String operations
│   │       └── ExcelDataReader.java     # Excel reader
│   └── test/
│       ├── java/com/framework/
│       │   ├── tests/
│       │   │   ├── BankAccountTest.java
│       │   │   ├── ShoppingCartTest.java
│       │   │   ├── UserServiceTest.java
│       │   │   ├── MathUtilsTest.java
│       │   │   ├── StringUtilsTest.java
│       │   │   └── ExcelDataDrivenTest.java
│       │   └── suites/
│       │       ├── FastTestSuite.java
│       │       ├── SlowTestSuite.java
│       │       ├── IntegrationTestSuite.java
│       │       └── FullTestSuite.java
│       └── resources/
│           ├── junit-platform.properties
│           └── data/
│               ├── math_test_data.csv
│               └── math_test_data.xlsx
```

---

## Test Strategy

### Functional Areas Covered

| Class | Functional Area | Tests |
|-------|----------------|-------|
| `BankAccount` | Deposits, withdrawals, transfers, freeze | 12+ |
| `ShoppingCart` | Cart operations, totals, discounts | 12+ |
| `UserService` | Registration, login, roles, deactivation | 12+ |
| `MathUtils` | Arithmetic, prime, factorial, gcd | 12+ |
| `StringUtils` | Palindrome, anagram, reverse, vowels | 12+ |
| `ExcelDataDrivenTest` | Excel-driven Valid/Invalid/Edge/Stress | 20+ |

### Test Categories

- **Positive tests**: Expected correct behavior
- **Negative tests**: Invalid inputs, wrong passwords, frozen accounts
- **Boundary tests**: Zero values, max values, empty strings
- **Exception tests**: Verify correct exceptions thrown

### AAA Pattern

All tests follow **Arrange → Act → Assert** structure.

### Naming Convention

```
should_<expectedBehavior>_when_<condition>
```

Example: `should_throwException_when_depositAmountIsNegative`

---

## Data-Driven Testing

### CSV (`math_test_data.csv`)
- Used with `@CsvFileSource` in `MathUtilsTest`
- Contains: a, b, expected columns

### Excel (`math_test_data.xlsx`)
- 4 sheets: **Valid**, **Invalid**, **Edge**, **Stress**
- 5-8 test cases per sheet
- Used via `ExcelDataReader` with `@MethodSource` in `ExcelDataDrivenTest`

---

## Test Configuration & Parallel Execution

### `junit-platform.properties`
```properties
junit.jupiter.execution.parallel.enabled=true
junit.jupiter.execution.parallel.mode.default=concurrent
junit.jupiter.execution.parallel.config.strategy=fixed
junit.jupiter.execution.parallel.config.fixed.parallelism=4
```

### Tags
| Tag | Class(es) |
|-----|-----------|
| `@Tag("fast")` | BankAccountTest, ShoppingCartTest, MathUtilsTest, StringUtilsTest |
| `@Tag("slow")` | ExcelDataDrivenTest |
| `@Tag("integration")` | UserServiceTest |

### Suites
- `FastTestSuite` – only `@Tag("fast")`
- `SlowTestSuite` – only `@Tag("slow")`
- `IntegrationTestSuite` – only `@Tag("integration")`
- `FullTestSuite` – all tests

---

## How to Run

```bash
# Run all tests + generate JaCoCo report
mvn clean verify

# Run tests only
mvn test

# Run fast tests only (via Surefire tag filter)
mvn test -Dgroups=fast

# Run integration tests only
mvn test -Dgroups=integration

# Generate Excel file (run once before tests)
mvn exec:java -Dexec.mainClass="com.framework.utils.ExcelDataReader"
```

---

## Code Coverage (JaCoCo)

After running `mvn clean verify`, coverage report is at:
```
target/site/jacoco/index.html
```

**Target**: ≥70% line coverage

---

## Challenges Faced

1. **Parallel test isolation**: Ensured each test creates its own object instance in `@BeforeEach` to avoid race conditions.
2. **Excel file generation**: `ExcelDataReader.main()` must be run before data-driven Excel tests to create the `.xlsx` file.
3. **Integer overflow in stress tests**: Used careful boundary values to avoid overflow in parameterized stress tests.

---

## Contribution

| Member | Tasks |
|--------|-------|
| Bilal | All tasks (Task 1–5): Setup, Test Design, Data-Driven, Config, Coverage |
