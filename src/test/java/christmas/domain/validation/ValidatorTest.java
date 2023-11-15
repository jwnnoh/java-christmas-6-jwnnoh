package christmas.domain.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ValidatorTest {
    private Validator validator;
    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @DisplayName("숫자 입력시 정상 통과한다.")
    @Test
    public void testValidateInputIntegerWithInteger() {
        // Given
        String quantity = "19";

        // When, Then
        assertThatCode(() -> validator.validateInputInteger(quantity))
                .doesNotThrowAnyException();
    }

    @DisplayName("공백, 문자, 빈 값 입력시 예외를 발생시킨다.")
    @ValueSource(strings = {"", " ", "five"})
    @ParameterizedTest
    public void testValidateInputIntegerWithoutString(String quantity) {
        assertThatThrownBy(() -> validator.validateInputInteger(quantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 메뉴를 반환하는 맵에 중복된 메뉴 주문이 없으면 정상 통과한다.")
    @Test
    public void testValidateDuplicatesWithNonDuplicates() {
        // Given
        Map<String, Integer> splitMenu = new HashMap<>();
        splitMenu.put("초코케이크", 1);

        // When, Then
        assertThatCode(() -> validator.validateDuplicates("제로콜라", splitMenu))
                .doesNotThrowAnyException();
    }

    @DisplayName("주문 메뉴를 반환하는 맵에 중복된 메뉴 주문이 있으면 예외를 발생시킨다.")
    @Test
    public void testValidateDuplicatesWithDuplicates() {
        // Given
        Map<String, Integer> splitMenu = new HashMap<>();
        splitMenu.put("초코케이크", 2);

        // When, Then
        assertThatThrownBy(() -> validator.validateDuplicates("초코케이크", splitMenu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}