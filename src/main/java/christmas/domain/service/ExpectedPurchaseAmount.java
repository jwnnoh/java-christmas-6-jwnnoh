package christmas.domain.service;

public class ExpectedPurchaseAmount {
    public int calculate(int purchaseAmount, int discountAmount) {
        if (purchaseAmount >= Giveaway.AMOUNT_REQUIRED) {
            return purchaseAmount - discountAmount + Giveaway.GIVEAWAY_MENU.getPrice();
        }
        return purchaseAmount - discountAmount;
    }
}
