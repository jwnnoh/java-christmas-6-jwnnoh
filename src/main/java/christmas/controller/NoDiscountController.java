package christmas.controller;

import christmas.domain.constants.Constant;
import christmas.view.constants.ConstantMessage;

import static christmas.domain.constants.Constant.BENEFIT_UNAVAILABLE;

public class NoDiscountController {
    public void showDiscount() {
        showGiveaway();
        showBenefitDiscount();
        showTotalDiscountAmount();
        showExpectedPurchaseAmount();
        showEventBadge();
    }

    private void showEventBadge() {
        System.out.println(ConstantMessage.EVENT_BADGE.getMessage());
        System.out.println(BENEFIT_UNAVAILABLE.getMessage());
    }

    private void showExpectedPurchaseAmount() {
        System.out.println(ConstantMessage.ORDER_AMOUNT_AFTER_DISCOUNT.getMessage());
        System.out.println(BENEFIT_UNAVAILABLE.getMessage() + "\n");
    }

    private void showTotalDiscountAmount() {
        System.out.println(ConstantMessage.DISCOUNT_AMOUNT.getMessage());
        System.out.println(BENEFIT_UNAVAILABLE.getMessage() + "\n");
    }

    private void showBenefitDiscount() {
        System.out.println(ConstantMessage.DISCOUNT_TYPE.getMessage());
        System.out.println(BENEFIT_UNAVAILABLE.getMessage() + "\n");
    }

    private void showGiveaway() {
        System.out.println(ConstantMessage.GIVEAWAY_MENU.getMessage());
        System.out.println(BENEFIT_UNAVAILABLE.getMessage() + "\n");
    }
}
