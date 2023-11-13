package christmas.controller;

import christmas.domain.service.*;
import christmas.view.OutputView;


import static christmas.domain.constants.Constant.BENEFIT_UNAVAILABLE;

public class DiscountController {
    private static final int D_DAY = 25;
    private static final int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;

    private final ScheduleController scheduleController;
    private final OrderController ordercontroller;
    private final OutputView outputView = new OutputView();
    private final Giveaway giveaway = new Giveaway();
    private final DDayCalculator dDayCalculator = new DDayCalculator();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    private int discountAmount;

    public DiscountController(ScheduleController scheduleController, OrderController orderController) {
        this.scheduleController = scheduleController;
        this.ordercontroller = orderController;
    }

    public void calcDiscount(int purchaseAmount) {
        showGiveawayEvent(purchaseAmount);
        outputView.printDiscountTypeMessage();
        showDDayDiscount();
        showWeekOfDateDiscount();
        showSpecialDiscount();
        showGiveawayDiscount(purchaseAmount);
        outputView.printNewLine();
        showTotalDiscountAmount();
        showExpectedPurchaseAmount(purchaseAmount);
    }

    private void showExpectedPurchaseAmount(int purchaseAmount) { // 증정 이벤트는 결제 금액에서 중복으로 차감되지 않는다.
        outputView.printExpectedPurchaseAmount(formatter.returnDecimalFormatAmount(
                purchaseAmount - discountAmount + Giveaway.GIVEAWAY_MENU.getPrice()));
        outputView.printNewLine();
    }

    private void showTotalDiscountAmount() {
        outputView.printTotalDiscountAmountMessage(formatter.returnDecimalFormatAmount(discountAmount));
        outputView.printNewLine();
    }

    private void showGiveawayDiscount(int purchaseAmount) {
        if (giveaway.isGiven(purchaseAmount)) {
            discountAmount += Giveaway.GIVEAWAY_MENU.getPrice(); // 증정 할인 누적합
            outputView.printGiveawayDiscountAmountMessage(
                    formatter.returnDecimalFormatAmount(giveaway.getGiveawayPrice()));
        }
    }

    private void showSpecialDiscount() {
        if (scheduleController.getGuest().checkSpecialDay()) {
            outputView.printSpecialDayDiscountAmountMessage(formatter.returnDecimalFormatAmount(SPECIAL_DAY_DISCOUNT_AMOUNT));
            discountAmount += SPECIAL_DAY_DISCOUNT_AMOUNT; // 특별 할인 누적합
        }
    }

    private void showWeekOfDateDiscount() {
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
        WeekendDiscount weekendDiscount = new WeekendDiscount();

        if (scheduleController.getGuest().checkDayWEEKEND()) {
            // 주말 -> 메인 메뉴 개당 2,023원 할인
            int weekendDiscountAmount = weekendDiscount.calcDiscount(ordercontroller.getOrderDetails().getMenuDetails());
            discountAmount += weekendDiscountAmount; // 주말 할인 누적합
            outputView.printWeekendDiscountAmountMessage(formatter.returnDecimalFormatAmount(weekendDiscountAmount));
            return;
        }
        // 평일 -> 디저트 메뉴 개당 2,023원 할인
        int weekdayDiscountAmount = weekdayDiscount.calcDiscount(ordercontroller.getOrderDetails().getMenuDetails());
        discountAmount += weekdayDiscountAmount; // 평일 할인 누적합
        outputView.printWeekdayDiscountAmountMessage(formatter.returnDecimalFormatAmount(weekdayDiscountAmount));
    }

    private void showDDayDiscount() {
        if (scheduleController.getGuest().checkDDay(D_DAY)) {
            int dDayDiscount = dDayCalculator.calcDDAyDiscount(scheduleController.getGuest().getDate(), D_DAY);
            discountAmount += dDayDiscount; // 디데이 할인 누적합
            outputView.printDDayDiscountAmountMessage(formatter.returnDecimalFormatAmount(dDayDiscount));
            return;
        }
        System.out.println(BENEFIT_UNAVAILABLE.getMessage());
        outputView.printNewLine();
    }

    private void showGiveawayEvent(int purchaseAmount) {
        outputView.printGiveawayEventMessage();
        System.out.println(giveaway.checkGiveaway(purchaseAmount));
        outputView.printNewLine();
    }
}