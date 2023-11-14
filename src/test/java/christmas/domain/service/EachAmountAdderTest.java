package christmas.domain.service;

import christmas.domain.constants.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class EachAmountAdderTest {

    @DisplayName("각 메뉴의 수량에 따른 주문 가격 계산을 검증한다.")
    @Test
    public void testAddEachAmount() {
        EachAmountAdder adder = new EachAmountAdder();


        Map<String, Integer> orderDetails = new HashMap<>();
        orderDetails.put("타파스", 2);

        int expectedTotalAmount = Menu.getPriceByName("타파스") * 2;

        assertThat(adder.addEachAmount("타파스", orderDetails)).isEqualTo(expectedTotalAmount);
    }
}