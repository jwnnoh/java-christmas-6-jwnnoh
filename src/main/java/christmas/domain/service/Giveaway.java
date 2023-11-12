package christmas.domain.service;

import christmas.domain.constants.Constant;
import christmas.domain.constants.Menu;

public class Giveaway {
    private static final int AMOUNT_REQUIRED = 120000;
    public String checkGiveaway(int purchaseAmount) {
        if (purchaseAmount >= AMOUNT_REQUIRED) {
            return Menu.CHAMPAGNE.getName();
        }
        return Constant.BENEFIT_UNAVAILABLE.getMessage();
    }
}
