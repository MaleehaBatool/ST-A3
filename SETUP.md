# Project Setup Note (Task 1)

## Environment Requirements
- **JDK**: Java 11 or higher (IntelliJ JBR recommended).
- **Build Tool**: Apache Maven 3.9+.
- **IDE**: IntelliJ IDEA 2026.1 (recommended).

## Installation & Configuration
1. **Clone/Import**: Import the project into your IDE specifically as a Maven project.
2. **JDK Setup**: Ensure your project SDK is set to Java 11 in Project Settings > Project.
3. **Build Dependency**: Run `mvn clean install` to download JUnit 5, Apache POI, and JaCoCo dependencies automatically.
4. **Excel Data Generation**: Run the `ExcelDataReader.java` main method once to generate the `math_test_data.xlsx` file required for data-driven tests.

## Running Tests
- **All Tests**: Run `mvn test` or use the `FullTestSuite.java`.
- **Coverage**: Run `mvn clean verify` and view results at `target/site/jacoco/index.html`.

## Demo Video Requirement
The assignment requires a 3-5 minute demo video demonstrating:
- Repo cloning/importing.
- Running the project.
- Executing unit tests and coverage results.
*(Video file to be recorded and added manually).*
