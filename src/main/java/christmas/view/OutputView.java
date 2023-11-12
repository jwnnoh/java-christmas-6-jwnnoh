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

    public void printGiveawayEventMessage() {
        System.out.println(ConstantMessage.GIVEAWAY_MENU.getMessage());
    }

    public void printDiscountTypeMessage() {
        System.out.println(ConstantMessage.DISCOUNT_TYPE.getMessage());
    }

    public void printDDayDiscountAmountMessage(String amount) {
        System.out.print(ConstantMessage.D_DAY_DISCOUNT.getMessage() + ConstantMessage.DISCOUNT_AMOUNT_PREFIX.getMessage());
        System.out.println(amount + ConstantMessage.AMOUNT_UNIT.getMessage());
    }
}
