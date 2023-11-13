package christmas.controller;

import christmas.domain.constants.Constant;
import christmas.domain.service.*;
import christmas.view.OutputView;

public class DiscountController {
    private static final int D_DAY = 25;
    private static final int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;

    private final ScheduleController scheduleController;
    private final OrderController ordercontroller;
    private final OutputView outputView = new OutputView();
    private final Giveaway giveaway = new Giveaway();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    private int discountAmount;

    public DiscountController(ScheduleController scheduleController, OrderController orderController) {
        this.scheduleController = scheduleController;
        this.ordercontroller = orderController;
    }

    public int showDiscount(int purchaseAmount) {
        showGiveawayEvent(purchaseAmount);
        showDiscountBenefit(purchaseAmount);
        return showTotalDiscountAmount();
        //분리
    }

    private void showDiscountBenefit(int purchaseAmount) {
        final int tmpDiscountAmount = discountAmount;

        outputView.printDiscountTypeMessage();
        showDDayDiscount();
        showWeekOfDateDiscount();
        showSpecialDiscount();
        showGiveawayDiscount(purchaseAmount);

        if (tmpDiscountAmount == discountAmount) {
            outputView.printUnavailable();
        }
    }

    private int showTotalDiscountAmount() {
        if (discountAmount > 0) {
            outputView.printTotalDiscountAmountMessage(formatter.returnDecimalFormatAmount(discountAmount));
            return discountAmount;
        }
        outputView.printTotalDiscountUnavailableMessage(discountAmount);
        return discountAmount;
    }

    private void showGiveawayDiscount(int purchaseAmount) {
        if (giveaway.isGiven(purchaseAmount)) {
            discountAmount += Giveaway.GIVEAWAY_MENU.getPrice(); // 증정 할인 누적합
            outputView.printGiveawayDiscountAmountMessage(
                    formatter.returnDecimalFormatAmount(Giveaway.GIVEAWAY_MENU.getPrice()));
        }
    }

    private void showSpecialDiscount() {
        if (scheduleController.getGuest().checkSpecialDay()) {
            int specialDiscount = SPECIAL_DAY_DISCOUNT_AMOUNT;
            outputView.printSpecialDayDiscountAmountMessage(
                    formatter.returnDecimalFormatAmount(specialDiscount));
            discountAmount += specialDiscount; // 특별 할인 누적합
        }
    }

    private void showWeekOfDateDiscount() { // TODO: 서비스로 분리하기
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        WeekendDiscount weekendDiscount = new WeekendDiscount();
        int weekOfDateDiscount;

        if (scheduleController.getGuest().checkDayWEEKEND()) { // 주말 -> 메인 메뉴 개당 2,023원 할인
            weekOfDateDiscount = weekendDiscount.calcDiscount(
                    ordercontroller.getOrderDetails().getMenuDetails());
            if (weekOfDateDiscount > 0) {
                discountAmount += weekOfDateDiscount; // 주말 할인 누적합
                outputView.printWeekendDiscountAmountMessage(
                        formatter.returnDecimalFormatAmount(weekOfDateDiscount));
                return;
            }
            return;
        }
        weekOfDateDiscount  = weekdayDiscount.calcDiscount(// 평일 -> 디저트 메뉴 개당 2,023원 할인
                ordercontroller.getOrderDetails().getMenuDetails());
        if (weekOfDateDiscount > 0) {
            discountAmount += weekOfDateDiscount; // 평일 할인 누적합
            outputView.printWeekdayDiscountAmountMessage(
                    formatter.returnDecimalFormatAmount(weekOfDateDiscount));
        }
    }

    private void showDDayDiscount() {
        if (scheduleController.getGuest().checkDDay(D_DAY)) {
            DDayCalculator dDayCalculator = new DDayCalculator();
            int dDayDiscount = dDayCalculator.calcDDAyDiscount(scheduleController.getGuest().getDate(), D_DAY);
            discountAmount += dDayDiscount; // 디데이 할인 누적합
            outputView.printDDayDiscountAmountMessage(
                    formatter.returnDecimalFormatAmount(dDayDiscount));
        }
    }

    private void showGiveawayEvent(int purchaseAmount) {
        if (giveaway.checkGiveaway(purchaseAmount)) {
            String message =  (Giveaway.GIVEAWAY_MENU.getName() + " " +
                    Giveaway.GIVEAWAY_MENU_AMOUNT + Constant.MENU_ITEM_UNIT.getMessage());
            outputView.printGiveawayEventMessage(message);
            return;
        }
        outputView.printGiveawayEventUnavailable();
    }
}