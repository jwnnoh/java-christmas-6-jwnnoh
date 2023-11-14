package christmas.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DDayCalculatorTest {

    @DisplayName("방문 날짜에 따른 디데이 할인 금액을 검증한다.")
    @Test
    public void testDDayCalculator() {
        DDayCalculator calculator = new DDayCalculator();
        int dDay = 25;

        // 방문날짜 : 1일
        int visitDate1 = 1;
        int expectedDiscount1 = 1000;
        assertThat(calculator.calcDDAyDiscount(visitDate1, dDay)).isEqualTo(expectedDiscount1);

        // 방문날짜 : 25일
        int visitDate2 = 25;
        int expectedDiscount2 = 3400;
        assertThat(calculator.calcDDAyDiscount(visitDate2, dDay)).isEqualTo(expectedDiscount2);

        // 방문날짜 : 3일
        int visitDate3 = 3;
        int expectedDiscount3 = 1200;
        assertThat(calculator.calcDDAyDiscount(visitDate3, dDay)).isEqualTo(expectedDiscount3);

        // 방문날짜 : 24일
        int visitDate4 = 24;
        int expectedDiscount4 = 3300;
        assertThat(calculator.calcDDAyDiscount(visitDate4, dDay)).isEqualTo(expectedDiscount4);
    }

}