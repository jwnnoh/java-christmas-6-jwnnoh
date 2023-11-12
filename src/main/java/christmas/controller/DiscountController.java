package christmas.controller;

import christmas.domain.service.DDayCalculator;
import christmas.domain.service.DecimalFormatFormatter;
import christmas.domain.service.EachAmountAdder;
import christmas.domain.service.Giveaway;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.domain.constants.Constant.BENEFIT_UNAVAILABLE;

public class DiscountController {
    private static final int D_DAY = 25;

    private final ScheduleController scheduleController;
    private final OrderController orderController;
    private final OutputView outputView = new OutputView();
    private final EachAmountAdder eachAmountAdder = new EachAmountAdder();
    private final Giveaway giveaway = new Giveaway();
    private final DDayCalculator dDayCalculator = new DDayCalculator();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    private int purchaseAmount;
    private int discountAmount;

    public DiscountController(OrderController orderController, ScheduleController scheduleController) {
        this.orderController = orderController;
        this.scheduleController = scheduleController;
    }

    public void calcDiscount() {
        calcPurchaseAmountBeforeDiscount();
        showPurchaseAmountBeforeDiscount();
        showGiveawayEvent();
        outputView.printDiscountTypeMessage();
        showDDayDiscount();
    }

    private void showDDayDiscount() {
        if (scheduleController.getGuest().checkDDay(D_DAY)) {
            int dDayDiscount = dDayCalculator.calcDDAyDiscount(scheduleController.getGuest().getDate(), D_DAY, discountAmount);
            outputView.printDDayDiscountAmountMessage(formatter.returnDecimalFormatAmount(dDayDiscount));
            outputView.printNewLine();
            return;
        }
        System.out.println(BENEFIT_UNAVAILABLE.getMessage());
        outputView.printNewLine();
    }

    private void showGiveawayEvent() {
        outputView.printGiveawayEventMessage();
        System.out.println(giveaway.checkGiveaway(purchaseAmount, discountAmount));
        outputView.printNewLine();
    }

    private void showPurchaseAmountBeforeDiscount() {
        outputView.printOrderAmountBeforeDiscountMessage(formatter.returnDecimalFormatAmount(purchaseAmount));
        outputView.printNewLine();
    }

    private void calcPurchaseAmountBeforeDiscount() {
        Map<String, Integer> orderDetails = orderController.getOrderDetails().getMenuDetails();
        for (String menuItem :
                orderDetails.keySet()) {
            purchaseAmount += eachAmountAdder.addEachAmount(menuItem, orderDetails);
        }
    }
}
