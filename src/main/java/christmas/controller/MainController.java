package christmas.controller;

import christmas.domain.constants.Constant;
import christmas.view.OutputView;

import java.util.Map;

import static christmas.domain.constants.Constant.PRINT_DELIMITER;

public class MainController {
    private final ScheduleController scheduleController = new ScheduleController();
    private final OrderController orderController = new OrderController();
    private final DiscountController discountController = new DiscountController(orderController, scheduleController);
    private final OutputView outputView = new OutputView();

    public void run() {
        scheduleController.setDate();
        orderController.setMenu();
        showBenefitIntro();
        showOrderedList();
        discountController.calcDiscount();
    }

    private void showOrderedList() {
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
}
