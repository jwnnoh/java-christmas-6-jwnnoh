package christmas.domain.service;

import christmas.domain.constants.Category;

import java.util.Map;

import static christmas.domain.constants.Menu.getCategoryByName;

public class WeekdayDiscount {
    private static final int DISCOUNT_AMOUNT = 2023;
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    public int calcDiscount(Map<String, Integer> menuDetails) {
        int tmpDiscountAmount = 0;
        for (String menuItem : menuDetails.keySet()) {
            if (getCategoryByName(menuItem) == Category.DESSERT) {
                tmpDiscountAmount += (DISCOUNT_AMOUNT * menuDetails.get(menuItem));
            }
        }
        return tmpDiscountAmount;
    }
}
