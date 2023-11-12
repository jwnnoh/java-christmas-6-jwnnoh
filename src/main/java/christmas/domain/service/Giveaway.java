package christmas.domain.service;

import christmas.domain.constants.Constant;
import christmas.domain.constants.Menu;

import static christmas.domain.constants.Menu.CHAMPAGNE;

public class Giveaway {
    private static final int AMOUNT_REQUIRED = 120000;
    private static final String GIVEAWAY_MENU_AMOUNT = " 1";
    private static final Menu GIVEAWAY_MENU = CHAMPAGNE;

    public String checkGiveaway(int purchaseAmount, int discountAmount) {
        if (purchaseAmount >= AMOUNT_REQUIRED) {
            discountAmount += GIVEAWAY_MENU.getPrice();
            return GIVEAWAY_MENU.getName() + GIVEAWAY_MENU_AMOUNT + Constant.MENU_ITEM_UNIT.getMessage();
        }
        return Constant.BENEFIT_UNAVAILABLE.getMessage();
    }

    public boolean isGiven(int purchaseAmount) {
        return purchaseAmount >= AMOUNT_REQUIRED;
    }

    public int getGiveawayPrice() {
        return GIVEAWAY_MENU.getPrice();
    }
}
