package christmas.domain;

import christmas.domain.constants.Category;
import christmas.domain.constants.Menu;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.constants.ConstantDelimiter.MENU_DELIMITER;
import static christmas.domain.constants.Menu.getCategoryByName;

public class OrderDetails {
    private static final int ORDER_MIN_LIMIT = 1;
    private static final int ORDER_MAX_LIMIT = 20;

    private final Map<String, Integer> menuDetails;

    public OrderDetails(String[] menu) {
        Map<String, Integer> menuDetails = splitMenu(menu);
        validate(menuDetails);
        this.menuDetails = menuDetails;
    }

    private Map<String, Integer> splitMenu(String[] menu) {
        Map<String, Integer> splitMenu = new HashMap<>();
        for (String menuItem : menu) {
            splitMenuItem(menuItem, splitMenu);
        }
        return splitMenu;
    }

    private void splitMenuItem(String menuItem, Map<String, Integer> splitMenu) {
        String[] itemDetails = menuItem.split(MENU_DELIMITER.getMessage());
        if (itemDetails.length == 2) {
            String itemName = itemDetails[0];
            int quantity = validateQuantityInteger(itemDetails[1]);
            splitMenu.put(itemName, quantity);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int validateQuantityInteger(String quantity) {
        try {
            return validateQuantityRange(Integer.parseInt(quantity));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private int validateQuantityRange(int quantity) {
        if (quantity < ORDER_MIN_LIMIT || quantity > ORDER_MAX_LIMIT) {
            throw new IllegalArgumentException();
        }
        return quantity;
    }

    private void validate(Map<String, Integer> menuDetails) {
        validateQuantitySum(menuDetails);
        checkValidMenu(menuDetails);
        checkOnlyDrinks(menuDetails);
    }

    private void validateQuantitySum(Map<String, Integer> menuDetails) {
        int quantitySum = 0;
        for (int quantity :
                menuDetails.values()) {
            quantitySum += quantity;
            if (quantitySum > ORDER_MAX_LIMIT) {
                throw new IllegalArgumentException();
            }
        }
    }

    public void checkValidMenu(Map<String, Integer> menuDetails) {
        for (String menuItem : menuDetails.keySet()) {
            if (!isValidMenuItem(menuItem)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private boolean isValidMenuItem(String menuItem) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuItem)) {
                return true;
            }
        }
        return false;
    }

    private void checkOnlyDrinks(Map<String, Integer> menuDetails) {
        for (String menuItem : menuDetails.keySet()) {
            if (getCategoryByName(menuItem) != Category.DRINK) {
                return;
            }
            throw new IllegalArgumentException();
        }
    }
}