package christmas.domain;

import christmas.domain.constants.Category;
import christmas.domain.constants.Menu;

import java.util.Map;

import static christmas.domain.constants.Menu.getCategoryByName;

public class OrderDetails {
    private static final int ORDER_MIN_LIMIT = 1;
    private static final int ORDER_MAX_LIMIT = 20;

    private final Map<String, Integer> menuDetails;

    public OrderDetails(Map<String, Integer> menuDetails) {
        validate(menuDetails);
        this.menuDetails = menuDetails;
    }

    private void validate(Map<String, Integer> menuDetails) {
        validateQuantityRange(menuDetails);
        validateQuantitySum(menuDetails);
        checkValidMenu(menuDetails);
        checkOnlyDrinks(menuDetails);
    }

    private void validateQuantityRange(Map<String, Integer> menuDetails) {
        for (int quantity :
                menuDetails.values()) {
            if (quantity < ORDER_MIN_LIMIT || quantity > ORDER_MAX_LIMIT) {
                throw new IllegalArgumentException();
            }
        }
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