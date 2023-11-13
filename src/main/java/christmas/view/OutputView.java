package christmas.view;

import christmas.view.constants.ConstantMessage;

public class OutputView {

    public void printErrorFromDateMessage() {
        System.out.println(ConstantMessage.ERROR_PREFIX.getMessage() +
                ConstantMessage.ERROR_DATE.getMessage());
    }

    public void printErrorFromOrderMessage() {
        System.out.println(ConstantMessage.ERROR_PREFIX.getMessage() +
                ConstantMessage.ERROR_ORDER.getMessage());
    }

    public void printWelcomeMessage() {
        System.out.println(ConstantMessage.WELCOME.getMessage());
    }

    public void printRequestDateOfVisitMessage() {
        System.out.println(ConstantMessage.REQUEST_DATE_OF_VISIT.getMessage());
    }

    public void printRequestOrderMessage() {
        System.out.println(ConstantMessage.REQUEST_ORDER.getMessage());
    }

    public void printBenefitIntroPrefixMessage() {
        System.out.print(ConstantMessage.BENEFIT_INTRO_PREFIX.getMessage());
    }

    public void printBenefitIntroSuffixMessage() {
        System.out.println(ConstantMessage.BENEFIT_INTRO_SUFFIX.getMessage());
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printOrderedMenuMessage() {
        System.out.println(ConstantMessage.ORDERED_MENU.getMessage());
    }

    public void printOrderAmountBeforeDiscountMessage(String amount) {
        System.out.println(ConstantMessage.ORDER_AMOUNT_BEFORE_DISCOUNT.getMessage());
        System.out.println(amount + ConstantMessage.AMOUNT_UNIT.getMessage());
    }

    public void printGiveawayEventMessage(String message) {
        System.out.println(ConstantMessage.GIVEAWAY_MENU.getMessage());
        System.out.println(message);
        printNewLine();
    }

    public void printGiveawayEventUnavailable() {
        System.out.println(ConstantMessage.GIVEAWAY_MENU.getMessage());
        System.out.println(ConstantMessage.BENEFIT_UNAVAILABLE.getMessage());
        printNewLine();
    }

    public void printDiscountTypeMessage() {
        System.out.println(ConstantMessage.DISCOUNT_TYPE.getMessage());
    }

    public void printDDayDiscountAmountMessage(String amount) {
        System.out.println(ConstantMessage.D_DAY_DISCOUNT.getMessage() +
                ConstantMessage.DISCOUNT_AMOUNT_PREFIX.getMessage() +
                amount + ConstantMessage.AMOUNT_UNIT.getMessage());
    }

    public void printWeekdayDiscountAmountMessage(String amount) {
        System.out.println(ConstantMessage.WEEKDAY_DISCOUNT.getMessage() +
                ConstantMessage.DISCOUNT_AMOUNT_PREFIX.getMessage() +
                amount + ConstantMessage.AMOUNT_UNIT.getMessage());
    }

    public void printWeekendDiscountAmountMessage(String amount) {
        System.out.println(ConstantMessage.WEEKEND_DISCOUNT.getMessage() +
                ConstantMessage.DISCOUNT_AMOUNT_PREFIX.getMessage() +
                amount + ConstantMessage.AMOUNT_UNIT.getMessage());
    }

    public void printSpecialDayDiscountAmountMessage(String amount) {
        System.out.println(ConstantMessage.SPECIAL_DISCOUNT.getMessage() +
                ConstantMessage.DISCOUNT_AMOUNT_PREFIX.getMessage() +
                amount + ConstantMessage.AMOUNT_UNIT.getMessage());
    }

    public void printGiveawayDiscountAmountMessage(String amount) {
        System.out.print(ConstantMessage.GIVEAWAY_DISCOUNT.getMessage() +
                ConstantMessage.DISCOUNT_AMOUNT_PREFIX.getMessage());
        System.out.println(amount + ConstantMessage.AMOUNT_UNIT.getMessage());
        printNewLine();
    }

    public void printTotalDiscountAmountMessage(String amount) {
        System.out.println(ConstantMessage.DISCOUNT_AMOUNT.getMessage());
        System.out.println(ConstantMessage.DISCOUNT_AMOUNT_PREFIX.getMessage() +
                amount + ConstantMessage.AMOUNT_UNIT.getMessage());
        printNewLine();
    }

    public void printTotalDiscountUnavailableMessage(int amount) {
        System.out.println(ConstantMessage.DISCOUNT_AMOUNT.getMessage());
        System.out.println(amount + ConstantMessage.AMOUNT_UNIT.getMessage());
        printNewLine();
    }

    public void printExpectedPurchaseAmountMessage(String amount) {
        System.out.println(ConstantMessage.ORDER_AMOUNT_AFTER_DISCOUNT.getMessage());
        System.out.println(amount + ConstantMessage.AMOUNT_UNIT.getMessage());
    }

    public void printEventBadgeMessage(String badge) {
        System.out.println(ConstantMessage.EVENT_BADGE.getMessage());
        System.out.println(badge);
    }

    public void printEventBadgeUnavailable() {
        System.out.println(ConstantMessage.EVENT_BADGE.getMessage());
        System.out.println(ConstantMessage.BENEFIT_UNAVAILABLE.getMessage());
    }

    public void printUnavailable() {
        System.out.println(ConstantMessage.BENEFIT_UNAVAILABLE.getMessage());
        printNewLine();
    }
}
