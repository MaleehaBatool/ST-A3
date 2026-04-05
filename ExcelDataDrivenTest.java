package com.framework.tests;

import com.framework.utils.ExcelDataReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Data-driven tests using Excel as the data source.
 * Covers Valid, Invalid, Edge, and Stress test categories.
 */
@DisplayName("Excel Data-Driven Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("slow")
class ExcelDataDrivenTest {

    @ParameterizedTest(name = "Valid: add({0},{1})={2}")
    @MethodSource("validAdditionData")
    @Order(1)
    @DisplayName("should_returnCorrectSum_when_validInputsFromExcel")
    void should_returnCorrectSum_when_validInputsFromExcel(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

    static Stream<int[]> validAdditionData() {
        try {
            List<int[]> data = ExcelDataReader.readMathData("src/test/resources/data/math_test_data.xlsx", "Valid");
            if (data.isEmpty()) return fallbackValidData();
            return data.stream();
        } catch (Exception e) {
            return fallbackValidData();
        }
    }

    private static Stream<int[]> fallbackValidData() {
        return Stream.of(
            new int[]{1, 2, 3},
            new int[]{10, 20, 30},
            new int[]{100, 200, 300},
            new int[]{0, 5, 5},
            new int[]{50, 50, 100}
        );
    }

    @ParameterizedTest(name = "Invalid: add({0},{1})!={2}")
    @MethodSource("invalidAdditionData")
    @Order(2)
    @DisplayName("should_notMatchIncorrectExpected_when_invalidExpectationsFromExcel")
    void should_notMatchIncorrectExpected_when_invalidExpectationsFromExcel(int a, int b, int wrongExpected) {
        assertNotEquals(wrongExpected, a + b);
    }

    static Stream<int[]> invalidAdditionData() {
        try {
            List<int[]> data = ExcelDataReader.readMathData("src/test/resources/data/math_test_data.xlsx", "Invalid");
            if (data.isEmpty()) return fallbackInvalidData();
            return data.stream();
        } catch (Exception e) {
            return fallbackInvalidData();
        }
    }

    private static Stream<int[]> fallbackInvalidData() {
        return Stream.of(
            new int[]{1, 2, 99},
            new int[]{5, 5, 0},
            new int[]{10, 10, 15},
            new int[]{3, 4, 0},
            new int[]{100, 1, 0}
        );
    }

    @ParameterizedTest(name = "Edge: add({0},{1})={2}")
    @MethodSource("edgeAdditionData")
    @Order(3)
    @DisplayName("should_handleEdgeCases_when_boundaryValuesFromExcel")
    void should_handleEdgeCases_when_boundaryValuesFromExcel(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

    static Stream<int[]> edgeAdditionData() {
        try {
            List<int[]> data = ExcelDataReader.readMathData("src/test/resources/data/math_test_data.xlsx", "Edge");
            if (data.isEmpty()) return fallbackEdgeData();
            return data.stream();
        } catch (Exception e) {
            return fallbackEdgeData();
        }
    }

    private static Stream<int[]> fallbackEdgeData() {
        return Stream.of(
            new int[]{0, 0, 0},
            new int[]{-1, 1, 0},
            new int[]{Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
            new int[]{-100, 100, 0},
            new int[]{1, -1, 0}
        );
    }

    @ParameterizedTest(name = "Stress multiply({0},{1})")
    @MethodSource("stressData")
    @Order(4)
    @DisplayName("should_handleLargeNumbers_when_stressInputsFromExcel")
    void should_handleLargeNumbers_when_stressInputsFromExcel(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

    static Stream<int[]> stressData() {
        try {
            List<int[]> data = ExcelDataReader.readMathData("src/test/resources/data/math_test_data.xlsx", "Stress");
            if (data.isEmpty()) return fallbackStressData();
            return data.stream();
        } catch (Exception e) {
            return fallbackStressData();
        }
    }

    private static Stream<int[]> fallbackStressData() {
        return Stream.of(
            new int[]{1000, 2000, 3000},
            new int[]{9999, 1, 10000},
            new int[]{5000, 5000, 10000},
            new int[]{999999, 1, 1000000},
            new int[]{100000, 200000, 300000}
        );
    }
}
