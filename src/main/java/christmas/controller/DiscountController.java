package christmas.controller;

import christmas.domain.constants.Constant;
import christmas.domain.service.DecimalFormatFormatter;
import christmas.domain.service.DDayCalculator;
import christmas.domain.service.Giveaway;
import christmas.domain.service.WeekdayDiscount;
import christmas.domain.service.WeekendDiscount;
import christmas.view.OutputView;

public class DiscountController {
    private static final int D_DAY = 25;
    private static final int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;

    private final ScheduleController scheduleController;
    private final OrderController orderController;
    private final OutputView outputView = new OutputView();
    private final Giveaway giveaway = new Giveaway();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    public DiscountController(ScheduleController scheduleController, OrderController orderController) {
        this.scheduleController = scheduleController;
        this.orderController = orderController;
    }

    public int showDiscount(int purchaseAmount) {
        int discountAmount = 0;

        showGiveawayEvent(purchaseAmount);
        outputView.printDiscountTypeMessage();
        discountAmount += calculateDDayDiscount();
        discountAmount += calculateWeekOfDayDiscount();
        discountAmount += calculateSpecialDayDiscount();
        discountAmount += calculateGiveawayDiscount(purchaseAmount);
        if (discountAmount == 0) {
            outputView.printUnavailable();
        }
        showTotalDiscountAmount(discountAmount);
        return discountAmount;
    }

    private int calculateDDayDiscount() {
        if (scheduleController.getGuest().checkDDay(D_DAY)) {
            DDayCalculator dDayCalculator = new DDayCalculator();
            int dDayDiscount = dDayCalculator.calcDDAyDiscount(scheduleController.getGuest().getDate(), D_DAY);
            outputView.printDDayDiscountAmountMessage(formatter.returnDecimalFormatAmount(dDayDiscount));
            return dDayDiscount;
        }
        return 0;
    }

    private int calculateWeekOfDayDiscount() {
        if (scheduleController.getGuest().checkDayWeekend()) {
            return calculateWeekendDiscount();
        }
        return calculateWeekdayDiscount();
    }

    private int calculateWeekendDiscount() {
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        int weekendDiscountAmount = weekendDiscount.calcDiscount(orderController.getOrderDetails().getMenuDetails());
        if (weekendDiscountAmount > 0) {
            outputView.printWeekendDiscountAmountMessage(formatter.returnDecimalFormatAmount(weekendDiscountAmount));
        }
        return weekendDiscountAmount;
    }

    private int calculateWeekdayDiscount() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        int weekdayDiscountAmount = weekdayDiscount.calcDiscount(orderController.getOrderDetails().getMenuDetails());
        if (weekdayDiscountAmount > 0) {
            outputView.printWeekdayDiscountAmountMessage(formatter.returnDecimalFormatAmount(weekdayDiscountAmount));
        }
        return weekdayDiscountAmount;
    }

    private int calculateSpecialDayDiscount() {
        if (scheduleController.getGuest().checkSpecialDay()) {
            int specialDiscount = SPECIAL_DAY_DISCOUNT_AMOUNT;
            outputView.printSpecialDayDiscountAmountMessage(formatter.returnDecimalFormatAmount(specialDiscount));
            return specialDiscount;
        }
        return 0;
    }

    private int calculateGiveawayDiscount(int purchaseAmount) {
        if (giveaway.checkGiveaway(purchaseAmount)) {
            int giveawayDiscountAmount = Giveaway.GIVEAWAY_MENU.getPrice();
            outputView.printGiveawayDiscountAmountMessage(formatter.returnDecimalFormatAmount(giveawayDiscountAmount));
            return giveawayDiscountAmount;
        }
        return 0;
    }

    private void showTotalDiscountAmount(int totalDiscountAmount) {
        if (totalDiscountAmount > 0) {
            outputView.printTotalDiscountAmountMessage(formatter.returnDecimalFormatAmount(totalDiscountAmount));
            return;
        }
        outputView.printTotalDiscountUnavailableMessage(totalDiscountAmount);
    }

    private void showGiveawayEvent(int purchaseAmount) {
        if (giveaway.checkGiveaway(purchaseAmount)) {
            String message = (Giveaway.GIVEAWAY_MENU.getName() + " " +
                    Giveaway.GIVEAWAY_MENU_AMOUNT + Constant.MENU_ITEM_UNIT.getMessage());
            outputView.printGiveawayEventMessage(message);
            return;
        }
        outputView.printGiveawayEventUnavailable();
    }
}
