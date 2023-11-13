package christmas.controller;

import christmas.domain.service.DecimalFormatFormatter;
import christmas.domain.service.EventBadgeDraw;
import christmas.domain.service.ExpectedPurchaseAmount;
import christmas.view.OutputView;

public class ResultController {
    private final OutputView outputView = new OutputView();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    public void showResult(int purchaseAmount, int discountAmount) {
        showExpectedPurchaseAmount(purchaseAmount, discountAmount);
        showEventBadge(discountAmount);
    }

    private void showEventBadge(int discountAmount) {
        EventBadgeDraw eventBadgeDraw = new EventBadgeDraw();
        if (discountAmount >= EventBadgeDraw.LOWEST_DISCOUNT) {
            outputView.printEventBadgeMessage(eventBadgeDraw.determineBadge(discountAmount));
            return;
        }
        outputView.printEventBadgeUnavailable();
    }

    private void showExpectedPurchaseAmount(int purchaseAmount, int discountAmount) {
        ExpectedPurchaseAmount expectedPurchaseAmount = new ExpectedPurchaseAmount();
        int finalPurchaseAmount = expectedPurchaseAmount.calculate(purchaseAmount, discountAmount);
        if (finalPurchaseAmount > 0) {
            outputView.printExpectedPurchaseAmountMessage(
                    formatter.returnDecimalFormatAmount(finalPurchaseAmount));
        }
        outputView.printNewLine();
    }
}
