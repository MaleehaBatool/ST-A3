package com.framework.tests;

import com.framework.utils.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for StringUtils utility class.
 */
@DisplayName("StringUtils Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("fast")
class StringUtilsTest {

    // ---- POSITIVE TESTS ----

    @ParameterizedTest
    @CsvSource({"racecar, true", "hello, false", "A man a plan a canal Panama, true", "level, true"})
    @Order(1)
    @DisplayName("should_detectPalindrome_when_inputIsChecked")
    void should_detectPalindrome_when_inputIsChecked(String input, boolean expected) {
        assertEquals(expected, StringUtils.isPalindrome(input));
    }

    @Test
    @Order(2)
    @DisplayName("should_reverseString_when_validStringProvided")
    void should_reverseString_when_validStringProvided() {
        assertEquals("dlroW olleH", StringUtils.reverse("Hello World"));
    }

    @Test
    @Order(3)
    @DisplayName("should_countVowelsCorrectly_when_stringContainsVowels")
    void should_countVowelsCorrectly_when_stringContainsVowels() {
        assertEquals(2, StringUtils.countVowels("hello"));
        assertEquals(0, StringUtils.countVowels("gym"));
    }

    @Test
    @Order(4)
    @DisplayName("should_capitalizeFirstLetter_when_stringProvided")
    void should_capitalizeFirstLetter_when_stringProvided() {
        assertEquals("Hello world", StringUtils.capitalize("hello world"));
    }

    @Test
    @Order(5)
    @DisplayName("should_detectAnagram_when_twoWordsAreAnagrams")
    void should_detectAnagram_when_twoWordsAreAnagrams() {
        assertTrue(StringUtils.isAnagram("listen", "silent"));
        assertFalse(StringUtils.isAnagram("hello", "world"));
    }

    @Test
    @Order(6)
    @DisplayName("should_countWordsCorrectly_when_sentenceProvided")
    void should_countWordsCorrectly_when_sentenceProvided() {
        assertEquals(3, StringUtils.wordCount("Hello World Test"));
        assertEquals(0, StringUtils.wordCount("   "));
    }

    @Test
    @Order(7)
    @DisplayName("should_truncateString_when_stringExceedsMaxLength")
    void should_truncateString_when_stringExceedsMaxLength() {
        assertEquals("Hello...", StringUtils.truncate("Hello World", 5));
        assertEquals("Hi", StringUtils.truncate("Hi", 10));
    }

    // ---- NEGATIVE TESTS ----

    @Test
    @Order(8)
    @DisplayName("should_throwException_when_nullInputToIsPalindrome")
    void should_throwException_when_nullInputToIsPalindrome() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.isPalindrome(null));
    }

    @Test
    @Order(9)
    @DisplayName("should_throwException_when_nullInputToReverse")
    void should_throwException_when_nullInputToReverse() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.reverse(null));
    }

    @Test
    @Order(10)
    @DisplayName("should_throwException_when_negativeTruncateLength")
    void should_throwException_when_negativeTruncateLength() {
        assertThrows(IllegalArgumentException.class, () -> StringUtils.truncate("hello", -1));
    }

    // ---- BOUNDARY TESTS ----

    @Test
    @Order(11)
    @DisplayName("should_returnEmptyString_when_reversingEmptyString")
    void should_returnEmptyString_when_reversingEmptyString() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    @Order(12)
    @DisplayName("should_returnZeroWords_when_blankStringProvided")
    void should_returnZeroWords_when_blankStringProvided() {
        assertEquals(0, StringUtils.wordCount(""));
    }
}
