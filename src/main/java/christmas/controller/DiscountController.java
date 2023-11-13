package christmas.controller;

import christmas.domain.constants.Constant;
import christmas.domain.service.*;
import christmas.view.OutputView;
import christmas.view.constants.ConstantMessage;

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

    public void showDiscount(int purchaseAmount) {
        showGiveawayEvent(purchaseAmount);
        showDiscountBenefit(purchaseAmount);
        showTotalDiscountAmount();
        showExpectedPurchaseAmount(purchaseAmount);
        showEventBadge();
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

    private void showEventBadge() {
        EventBadgeDraw eventBadgeDraw = new EventBadgeDraw();
        if (discountAmount >= EventBadgeDraw.LOWEST_DISCOUNT) {
            outputView.printEventBadgeMessage(eventBadgeDraw.determineBadge(discountAmount));
            return;
        }
        outputView.printEventBadgeUnavailable();
    }

    private void showExpectedPurchaseAmount(int purchaseAmount) {
        ExpectedPurchaseAmount expectedPurchaseAmount = new ExpectedPurchaseAmount();
        int finalPurchaseAmount = expectedPurchaseAmount.calculate(purchaseAmount, discountAmount);
        if (finalPurchaseAmount > 0) {
            outputView.printExpectedPurchaseAmountMessage(
                    formatter.returnDecimalFormatAmount(finalPurchaseAmount));
        }
        outputView.printNewLine();
    }

    private void showTotalDiscountAmount() {
        if (discountAmount > 0) {
            outputView.printTotalDiscountAmountMessage(formatter.returnDecimalFormatAmount(discountAmount));
            return;
        }
        outputView.printTotalDiscountUnavailableMessage(discountAmount);
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
        int weekOfDateDiscount = 0;

        if (scheduleController.getGuest().checkDayWEEKEND()) {
            // 주말 -> 메인 메뉴 개당 2,023원 할인
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
        // 평일 -> 디저트 메뉴 개당 2,023원 할인
        weekOfDateDiscount  = weekdayDiscount.calcDiscount(
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