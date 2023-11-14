package christmas.domain.service;

import christmas.domain.constants.Calender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendDiscountTest {
    private WeekendDiscount weekendDiscount;
    private Map<String, Integer> menuDetails;

    @BeforeEach
    void setUp() {
        weekendDiscount = new WeekendDiscount();
        menuDetails = new HashMap<>();
    }

    @DisplayName("주말에는 메인 메뉴가 개당 2023원씩 할인된다.")
    @Test
    void testCalcDiscountForMain() {
        // Given
        menuDetails.put("티본스테이크", 3);

        // When
        int discountAmount = weekendDiscount.calcDiscount(menuDetails);

        // Then
        int expectedDiscount = Calender.YEAR.getValue() * menuDetails.get("티본스테이크");
        assertThat(discountAmount).isEqualTo(expectedDiscount);
    }

    @DisplayName("주말은 메인 메뉴를 제외한 메뉴가 할인되지 않는다.")
    @Test
    void testCalcDiscountForDessert() {
        // Given
        menuDetails.put("초코케이크", 2);

        // When
        int discountAmount = weekendDiscount.calcDiscount(menuDetails);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }
}