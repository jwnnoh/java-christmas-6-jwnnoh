package christmas.domain;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.constants.ConstantDelimiter.MENU_DELIMITER;

public class OrderDetails {
    private final Map<String, Integer> menuDetails;

    public OrderDetails(String[] menu) {
        Map<String, Integer> menuDetails = splitMenu(menu);
        this.menuDetails = validate(menuDetails);
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
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private Map<String, Integer> validate(Map<String, Integer> menuDetails) {
        //존재하는 메뉴와, 최대 주문 개수 초과 여부 확인
        return menuDetails;
    }
}
