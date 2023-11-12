package christmas.controller;

import christmas.domain.service.EachAmountAdder;
import christmas.domain.service.Giveaway;
import christmas.view.OutputView;

import java.text.DecimalFormat;
import java.util.Map;

import static christmas.domain.constants.Constant.AMOUNT_UNIT;

public class DiscountController {
    private static final String REWARD_FORMAT = "###,###";

    private final ScheduleController scheduleController;
    private final OrderController orderController;
    private final OutputView outputView = new OutputView();
    private final EachAmountAdder eachAmountAdder = new EachAmountAdder();
    private final Giveaway giveaway = new Giveaway();

    private int purchaseAmount;

    public DiscountController(OrderController orderController, ScheduleController scheduleController) {
        this.orderController = orderController;
        this.scheduleController = scheduleController;
    }

    public void calcDiscount() {
        calcPurchaseAmountBeforeDiscount();
        showPurchaseAmountBeforeDiscount();
        showGiveawayEvent(purchaseAmount);
    }

    private void showGiveawayEvent(int purchaseAmount) {
        outputView.printGiveawayEventMessage();
        System.out.println(giveaway.checkGiveaway(purchaseAmount));
        outputView.printNewLine();
    }

    private void showPurchaseAmountBeforeDiscount() {
        outputView.printOrderAmountBeforeDiscountMessage();
        DecimalFormat decimalFormat = new DecimalFormat(REWARD_FORMAT);
        System.out.println(decimalFormat.format(purchaseAmount) + AMOUNT_UNIT.getMessage());
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
