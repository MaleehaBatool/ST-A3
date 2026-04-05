# Test Strategy Document

## 1. Objective
To design and implement a scalable, maintainable, and data-driven unit testing framework using JUnit 5.

## 2. Functional Areas
- **Account Management**: Validating deposits, withdrawals, transfers, and account freezing logic in `BankAccount`.
- **E-commerce Logic**: Testing product stock management, pricing, and discount application in `Product`.
- **Shopping Services**: Managing cart items, calculating totals with discounts, and stock validation in `ShoppingCart`.
- **User Authentication**: Secure registration, login, role assignment, and account deactivation in `UserService`.
- **Mathematical Utilities**: Verifying core algorithms like Primes, Factorials, GCD, and division.
- **String Manipulation**: Handling palindromes, anagrams, word counting, and formatting.

## 3. Risk Zones
- **Concurrency**: Parallel execution of tests may cause state pollution if not handled via `@BeforeEach` isolation.
- **Data Integrity**: Excel and CSV data must match the expected method signatures (Int, Double, etc.).
- **Boundary Conditions**: Large numbers (Integer.MAX_VALUE), empty strings, and null inputs.

## 4. Edge Cases
- Division by zero in `MathUtils`.
- Null/Empty inputs in `StringUtils`.
- Negative initial balances or deposits in `BankAccount`.
- Applying >100% discount in `ShoppingCart`.
- Registering duplicate emails in `UserService`.

## 5. Test Design Patterns
- **AAA Pattern**: All tests follow the Arrange-Act-Assert structure.
- **Naming Convention**: `should_<expectedBehavior>_when_<condition>`.
- **Data-Driven**: Separating test data (CSV/Excel) from test logic.
- **Suites**: Grouping tests by impact (Fast vs Slow) and type (Unit vs Integration).
