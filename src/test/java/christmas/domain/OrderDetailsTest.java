package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class OrderDetailsTest {
    private Map<String, Integer> menuDetails;

    @BeforeEach
    public void setUp() {
        menuDetails = new HashMap<>();
    }

    @DisplayName("주문한 메뉴가 담긴 맵을 변경할 수 없는 맵으로 가져온다.")
    @Test
    public void testGetMenuDetails() {
        // Given
        menuDetails.put("초코케이크", 2);
        menuDetails.put("티본스테이크", 1);

        // When
        OrderDetails orderDetails = new OrderDetails(menuDetails);

        // Then
        assertThat(orderDetails.getMenuDetails()).isEqualTo(Collections.unmodifiableMap(menuDetails));
    }

    @DisplayName("주문 최대 개수의 수량을 넘으면 예외가 발생한다.")
    @Test
    public void testValidateQuantityRangeOver() {
        // Given
        menuDetails.put("해산물파스타", 21);

        // When, Then
        assertThatThrownBy(() -> new OrderDetails(menuDetails))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 최소 개수의 수량보다 적으면 예외가 발생한다.")
    @Test
    public void testValidateQuantityRangeUnder() {
        // Given
        menuDetails.put("해산물파스타", 0);

        // When, Then
        assertThatThrownBy(() -> new OrderDetails(menuDetails))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 주문 개수의 수량이 최대 개수의 수량을 넘으면 예외가 발생한다.")
    @Test
    public void testValidateQuantitySumOver() {
        // Given
        menuDetails.put("해산물파스타", 10);
        menuDetails.put("제로콜라", 11);

        // When, Then
        assertThatThrownBy(() -> new OrderDetails(menuDetails))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("존재하지 않는 메뉴 주문 시 예외를 발생한다.")
    @Test
    public void testCheckValidMenuWithInvalidMenu() {
        // Given
        menuDetails.put("일반콜라", 1);

        // When, Then
        assertThatThrownBy(() -> new OrderDetails(menuDetails))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문했을 경우 예외를 발생한다.")
    @ValueSource(strings = {"제로콜라, 레드와인, 샴페인"})
    @ParameterizedTest
    public void testCheckOnlyDrinks(String drink) {
        // Given
        menuDetails.put(drink, 1);

        // When, Then
        assertThatThrownBy(() -> new OrderDetails(menuDetails))
                .isInstanceOf(IllegalArgumentException.class);
    }
}