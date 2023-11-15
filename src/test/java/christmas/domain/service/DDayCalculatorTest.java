package christmas.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DDayCalculatorTest {

    @DisplayName("방문 날짜에 따른 디데이 할인 금액을 검증한다.")
    @CsvSource(value = {"1, 1000", "25, 3400", "3, 1200", "24, 3300"})
    @ParameterizedTest
    public void testDDayCalculator(int visitDate, int expectedDiscount) {

        DDayCalculator calculator = new DDayCalculator();
        int dDay = 25;

        assertThat(calculator.calcDDAyDiscount(visitDate, dDay)).isEqualTo(expectedDiscount);
    }
}