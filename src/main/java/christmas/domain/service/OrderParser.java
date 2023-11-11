package christmas.domain.service;

import static christmas.domain.constants.ConstantDelimiter.ORDER_DELIMITER;

public class OrderParser {

    public String[] splitOrder(String order) {
        return order.split(ORDER_DELIMITER.getMessage());
    }
}