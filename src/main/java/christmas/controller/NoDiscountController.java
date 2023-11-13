package christmas.controller;

import christmas.domain.service.DecimalFormatFormatter;
import christmas.view.OutputView;

public class NoDiscountController {
    private final OutputView outputView = new OutputView();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    public void showDiscount(int purchaseAmount, int discountAmount) {
        showGiveaway();
        showBenefitDiscount();
        showTotalDiscountAmount(discountAmount);
        showExpectedPurchaseAmount(purchaseAmount);
        showEventBadge();
    }

    private void showEventBadge() {
        outputView.printEventBadgeUnavailable();
    }

    private void showExpectedPurchaseAmount(int purchaseAmount) {
        outputView.printExpectedPurchaseAmountMessage(formatter.returnDecimalFormatAmount(purchaseAmount));
        outputView.printNewLine();
    }

    private void showTotalDiscountAmount(int discountAmount) {
        outputView.printTotalDiscountUnavailableMessage(discountAmount);
    }

    private void showBenefitDiscount() {
        outputView.printDiscountTypeMessage();
        outputView.printUnavailable();
    }

    private void showGiveaway() {
        outputView.printGiveawayEventUnavailable();
    }
}
