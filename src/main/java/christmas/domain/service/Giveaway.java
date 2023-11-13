package christmas.domain.service;

import christmas.domain.constants.Menu;

import static christmas.domain.constants.Menu.CHAMPAGNE;

public class Giveaway {
    public static final String GIVEAWAY_MENU_AMOUNT = " 1";
    public static final int AMOUNT_REQUIRED = 120000;
    public static final Menu GIVEAWAY_MENU = CHAMPAGNE;

    public boolean checkGiveaway(int purchaseAmount) {
        return purchaseAmount >= AMOUNT_REQUIRED;
    }

    public boolean isGiven(int purchaseAmount) {
        return purchaseAmount >= AMOUNT_REQUIRED;
    }

}
