package christmas.controller;

import christmas.domain.constants.Constant;
import christmas.domain.service.DecimalFormatFormatter;
import christmas.domain.service.EachAmountAdder;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.domain.constants.Constant.PRINT_DELIMITER;

public class MainController {
    private static final int DISCOUNT_REQUIREMENT_AMOUNT = 10000;

    private final ScheduleController scheduleController = new ScheduleController();
    private final OrderController orderController = new OrderController();
    private final DiscountController discountController = new DiscountController(scheduleController, orderController);
    private final OutputView outputView = new OutputView();
    private final NoDiscountController noDiscountController = new NoDiscountController();
    private final ResultController resultController = new ResultController();

    private int purchaseAmount;


    public void run() {
        int discountAmount = 0;
        scheduleController.setDate();
        orderController.setMenu();
        showBenefitIntro();
        showOrderedList();
        showPurchaseAmountBeforeDiscount();
        if (purchaseAmount >= DISCOUNT_REQUIREMENT_AMOUNT) {
            discountAmount = discountController.showDiscount(purchaseAmount);
            resultController.showResult(purchaseAmount, discountAmount);
            return;
        }
        noDiscountController.showDiscount(purchaseAmount, discountAmount);
    }

    private void showOrderedList() {
        calcPurchaseAmountBeforeDiscount();

        outputView.printOrderedMenuMessage();
        Map<String, Integer> orderDetails = orderController.getOrderDetails().getMenuDetails();
        for (String menuItem : orderDetails.keySet()) {
            System.out.println(menuItem +
                    PRINT_DELIMITER.getMessage() +
                    orderDetails.get(menuItem) + Constant.MENU_ITEM_UNIT.getMessage());
        }
        outputView.printNewLine();
    }

    private void showBenefitIntro() {
        outputView.printBenefitIntroPrefixMessage();
        System.out.print(scheduleController.getGuest().getDate());
        outputView.printBenefitIntroSuffixMessage();
        outputView.printNewLine();
    }

    private void showPurchaseAmountBeforeDiscount() {
        DecimalFormatFormatter formatter = new DecimalFormatFormatter();
        outputView.printOrderAmountBeforeDiscountMessage(formatter.returnDecimalFormatAmount(purchaseAmount));
        outputView.printNewLine();
    }

    private void calcPurchaseAmountBeforeDiscount() {
        EachAmountAdder eachAmountAdder = new EachAmountAdder();
        Map<String, Integer> orderDetails = orderController.getOrderDetails().getMenuDetails();
        for (String menuItem :
                orderDetails.keySet()) {
            purchaseAmount += eachAmountAdder.addEachAmount(menuItem, orderDetails);
        }
    }
}
