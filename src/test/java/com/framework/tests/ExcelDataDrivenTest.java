package com.framework.tests;

import com.framework.utils.ExcelDataReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

/**
 * Data-driven tests using Excel as the data source.
 * Covers Valid, Invalid, Edge, and Stress test categories.
 */
@DisplayName("Excel Data-Driven Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("slow")
class ExcelDataDrivenTest {

    // ---- VALID TESTS from Excel ----
    @ParameterizedTest(name = "[{index}] Valid: add({0},{1})={2}")
    @MethodSource("validAdditionData")
    @Order(1)
    @DisplayName("should_returnCorrectSum_when_validInputsFromExcel")
    void should_returnCorrectSum_when_validInputsFromExcel(int a, int b, int expected) {
        Assertions.assertEquals(expected, a + b);
    }

    static Stream<Arguments> validAdditionData() {
        return loadSheet("Valid",
            Arguments.of(1, 2, 3),
            Arguments.of(10, 20, 30),
            Arguments.of(100, 200, 300),
            Arguments.of(0, 5, 5),
            Arguments.of(50, 50, 100),
            Arguments.of(5, 5, 10),
            Arguments.of(7, 3, 10),
            Arguments.of(15, 5, 20)
        );
    }

    // ---- INVALID TESTS from Excel (wrong expected, should NOT equal) ----
    @ParameterizedTest(name = "[{index}] Invalid: add({0},{1}) != {2}")
    @MethodSource("invalidAdditionData")
    @Order(2)
    @DisplayName("should_notMatchWrongExpected_when_invalidExpectationsFromExcel")
    void should_notMatchWrongExpected_when_invalidExpectationsFromExcel(int a, int b, int wrongExpected) {
        Assertions.assertNotEquals(wrongExpected, a + b);
    }

    static Stream<Arguments> invalidAdditionData() {
        return loadSheet("Invalid",
            Arguments.of(1, 2, 99),
            Arguments.of(5, 5, 0),
            Arguments.of(10, 10, 15),
            Arguments.of(3, 4, 0),
            Arguments.of(100, 1, 0),
            Arguments.of(7, 7, 1),
            Arguments.of(20, 30, 10),
            Arguments.of(9, 1, 5)
        );
    }

    // ---- EDGE TESTS from Excel ----
    @ParameterizedTest(name = "[{index}] Edge: add({0},{1})={2}")
    @MethodSource("edgeAdditionData")
    @Order(3)
    @DisplayName("should_handleEdgeCases_when_boundaryValuesFromExcel")
    void should_handleEdgeCases_when_boundaryValuesFromExcel(int a, int b, int expected) {
        Assertions.assertEquals(expected, a + b);
    }

    static Stream<Arguments> edgeAdditionData() {
        return loadSheet("Edge",
            Arguments.of(0, 0, 0),
            Arguments.of(-1, 1, 0),
            Arguments.of(0, 1, 1),
            Arguments.of(-100, 100, 0),
            Arguments.of(1, -1, 0),
            Arguments.of(-50, 50, 0),
            Arguments.of(0, -1, -1),
            Arguments.of(Integer.MAX_VALUE / 2, 0, Integer.MAX_VALUE / 2)
        );
    }

    // ---- STRESS TESTS from Excel ----
    @ParameterizedTest(name = "[{index}] Stress: add({0},{1})={2}")
    @MethodSource("stressData")
    @Order(4)
    @DisplayName("should_handleLargeNumbers_when_stressInputsFromExcel")
    void should_handleLargeNumbers_when_stressInputsFromExcel(int a, int b, int expected) {
        Assertions.assertEquals(expected, a + b);
    }

    static Stream<Arguments> stressData() {
        return loadSheet("Stress",
            Arguments.of(1000, 2000, 3000),
            Arguments.of(9999, 1, 10000),
            Arguments.of(5000, 5000, 10000),
            Arguments.of(999, 1, 1000),
            Arguments.of(10000, 10000, 20000),
            Arguments.of(50000, 50000, 100000),
            Arguments.of(100000, 200000, 300000),
            Arguments.of(99999, 1, 100000)
        );
    }

    /**
     * Tries to load data from the given Excel sheet; falls back to provided default Arguments on error.
     */
    @SafeVarargs
    private static Stream<Arguments> loadSheet(String sheetName, Arguments... fallback) {
        try {
            List<int[]> data = ExcelDataReader.readMathData(
                    "src/test/resources/data/math_test_data.xlsx", sheetName);
            if (!data.isEmpty()) {
                return data.stream().map(row -> Arguments.of(row[0], row[1], row[2]));
            }
        } catch (Exception ignored) {}
        return Stream.of(fallback);
    }
}
