package christmas.domain.service;

import java.util.Map;

import static christmas.domain.constants.Menu.getPriceByName;

public class EachAmountAdder {
    public int addEachAmount(String menuItem, Map<String, Integer> orderDetails) {
        int quantity = orderDetails.get(menuItem);
        return quantity * getPriceByName(menuItem);
    }
}
