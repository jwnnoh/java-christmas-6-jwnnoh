package christmas.domain.service;

import christmas.domain.constants.Calender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekdayDiscountTest {
    private WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
    Map<String, Integer> menuDetails;

    @BeforeEach
    void setUp() {
        weekdayDiscount = new WeekdayDiscount();
        menuDetails = new HashMap<>();
    }

    @DisplayName("평일에는 디저트 메뉴가 개당 2023원씩 할인된다.")
    @Test
    public void testCalcDiscountForDessert() {
        // Given
        menuDetails.put("초코케이크", 2);

        // When
        int discountAmount = weekdayDiscount.calcDiscount(menuDetails);

        // Then
        int expectedDiscount = Calender.YEAR.getValue() * menuDetails.get("초코케이크");
        assertThat(discountAmount).isEqualTo(expectedDiscount);
    }

    @DisplayName("평일은 디저트 메뉴가 아닌 메뉴는 할인되지 않는다.")
    @Test
    public void testCalcDiscountForMain() {
        // Given
        menuDetails.put("타파스", 3);

        // When
        int discountAmount = weekdayDiscount.calcDiscount(menuDetails);

        // Then
        assertThat(discountAmount).isEqualTo(0);
    }
}
