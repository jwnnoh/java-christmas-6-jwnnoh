package christmas.controller;

import christmas.domain.service.*;
import christmas.view.OutputView;


import static christmas.domain.constants.Constant.BENEFIT_UNAVAILABLE;

public class DiscountController {
    private static final int D_DAY = 25;
    private static final int SPECIAL_DAY_DISCOUNT_AMOUNT = 1000;
    private static final int DAY_DISCOUNT_AMOUNT = 2023;

    private final ScheduleController scheduleController;
    private final OrderController ordercontroller;
    private final OutputView outputView = new OutputView();
    private final Giveaway giveaway = new Giveaway();
    private final DDayCalculator dDayCalculator = new DDayCalculator();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();
    private final WeekdayDiscount weekdayDiscount = new WeekdayDiscount();
    private final WeekendDiscount weekendDiscount = new WeekendDiscount();


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
    }

    private void showWeekOfDateDiscount() {
        if (scheduleController.getGuest().checkDayWEEKEND()) {
            // 주말 -> 메인 메뉴 개당 2,023원 할인
            String amount = weekendDiscount.calcDiscount(ordercontroller.getOrderDetails().getMenuDetails(), discountAmount);
            outputView.printWeekendDiscountAmountMessage(amount);
            return;
        }
        // 평일 -> 디저트 메뉴 개당 2,023원 할인
        String amount = weekdayDiscount.calcDiscount(ordercontroller.getOrderDetails().getMenuDetails(), discountAmount);
        outputView.printWeekdayDiscountAmountMessage(amount);
    }

    private void showDDayDiscount() {
        if (scheduleController.getGuest().checkDDay(D_DAY)) {
            int dDayDiscount = dDayCalculator.calcDDAyDiscount(scheduleController.getGuest().getDate(), D_DAY, discountAmount);
            outputView.printDDayDiscountAmountMessage(formatter.returnDecimalFormatAmount(dDayDiscount));
            return;
        }
        System.out.println(BENEFIT_UNAVAILABLE.getMessage());
        outputView.printNewLine();
    }

    private void showGiveawayEvent(int purchaseAmount) {
        outputView.printGiveawayEventMessage();
        System.out.println(giveaway.checkGiveaway(purchaseAmount, discountAmount));
        outputView.printNewLine();
    }
}