package christmas.domain.service;

import christmas.domain.validation.Validator;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.constants.ConstantDelimiter.MENU_DELIMITER;
import static christmas.domain.constants.ConstantDelimiter.ORDER_DELIMITER;

public class OrderParser {
    private final Validator validator = new Validator();

    public String[] splitOrder(String order) {
        return order.split(ORDER_DELIMITER.getMessage());
    }

    public Map<String, Integer> splitMenu(String[] menu) {
        Map<String, Integer> splitMenu = new HashMap<>();
        for (String menuItem : menu) {
            splitMenuItem(menuItem, splitMenu);
        }
        return splitMenu;
    }

    private void splitMenuItem(String menuItem, Map<String, Integer> splitMenu) {
        String[] itemDetails = menuItem.split(MENU_DELIMITER.getMessage());
        if (itemDetails.length == 2) {
            String itemName = validator.validateDuplicates(itemDetails[0], splitMenu);
            int quantity = validator.validateQuantityInteger(itemDetails[1]);
            splitMenu.put(itemName, quantity);
        } else {
            throw new IllegalArgumentException();
        }
    }
}