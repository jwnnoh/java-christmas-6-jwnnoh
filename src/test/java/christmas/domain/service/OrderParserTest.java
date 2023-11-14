package christmas.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class OrderParserTest {
    private OrderParser orderParser;

    @BeforeEach
    void setUp() {
        orderParser = new OrderParser();
    }

    @DisplayName(" ','를 기준으로 주문서에서 각 메뉴를 분리해 리스트로 반환하는 로직을 검증한다.")
    @Test
    void testSplitOrder() {
        String order = "타파스-2,제로콜라-1,레드와인-3";
        String[] expectedResult = orderParser.splitOrder(order);

        assertThat(expectedResult).containsExactly("타파스-2", "제로콜라-1", "레드와인-3");
    }

    @DisplayName("주문서에서 분리된 각 메뉴를 (key:메뉴명 : value:수량)으로 하여 맵으로 반환하는 로직을 검증한다.")
    @Test
    void testSplitValidMenu() {
        String[] menu = {"타파스-2", "제로콜라-1", "레드와인-3"};
        Map<String, Integer> expectedResult = orderParser.splitMenu(menu);

        assertThat(expectedResult).hasSize(menu.length)
                .containsEntry("타파스", 2)
                .containsEntry("제로콜라", 1)
                .containsEntry("레드와인", 3);
    }

    @DisplayName("중복된 메뉴를 주문시 맵을 반환하지 않고 오류를 발생시킨다.")
    @Test
    void testSplitDuplicatedMenu() {
        String[] menu = {"타파스-2", "타파스-14", "레드와인-3"};

        assertThatThrownBy(() -> orderParser.splitMenu(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 개수가 숫자가 아니면 아니면 맵을 반환하지 않고 오류를 발생시킨다.")
    @Test
    void testSplitInvalidQuantity() {
        String[] menu = {"타파스-2", "제로콜라-one", "레드와인-3"};

        assertThatThrownBy(() -> orderParser.splitMenu(menu))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
