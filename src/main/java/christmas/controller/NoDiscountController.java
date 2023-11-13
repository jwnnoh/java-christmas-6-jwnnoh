package christmas.controller;

import christmas.domain.service.DecimalFormatFormatter;
import christmas.view.OutputView;
import christmas.view.constants.ConstantMessage;

public class NoDiscountController {
    private final OutputView outputView = new OutputView();

    public void showDiscount(int purchaseAmount) {
        showGiveaway();
        showBenefitDiscount();
        showTotalDiscountAmount();
        showExpectedPurchaseAmount(purchaseAmount);
        showEventBadge();
    }

    private void showEventBadge() {
        System.out.println(ConstantMessage.EVENT_BADGE.getMessage());
        outputView.printUnavailable();
    }

    private void showExpectedPurchaseAmount(int purchaseAmount) {
        DecimalFormatFormatter formatter = new DecimalFormatFormatter();
        System.out.println(ConstantMessage.ORDER_AMOUNT_AFTER_DISCOUNT.getMessage());
        System.out.println(formatter.returnDecimalFormatAmount(purchaseAmount) +
                ConstantMessage.AMOUNT_UNIT.getMessage());
        outputView.printNewLine();
    }

    private void showTotalDiscountAmount() {
        System.out.println(ConstantMessage.DISCOUNT_AMOUNT.getMessage());
        outputView.printUnavailable();
    }

    private void showBenefitDiscount() {
        System.out.println(ConstantMessage.DISCOUNT_TYPE.getMessage());
        outputView.printUnavailable();
    }

    private void showGiveaway() {
        System.out.println(ConstantMessage.GIVEAWAY_MENU.getMessage());
        outputView.printUnavailable();
    }
}
