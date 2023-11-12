package christmas.controller;

import christmas.domain.service.DDayCalculator;
import christmas.domain.service.DecimalFormatFormatter;
import christmas.domain.service.Giveaway;
import christmas.view.OutputView;


import static christmas.domain.constants.Constant.BENEFIT_UNAVAILABLE;

public class DiscountController {
    private static final int D_DAY = 25;

    private final ScheduleController scheduleController;
    private final OutputView outputView = new OutputView();
    private final Giveaway giveaway = new Giveaway();
    private final DDayCalculator dDayCalculator = new DDayCalculator();
    private final DecimalFormatFormatter formatter = new DecimalFormatFormatter();

    private int discountAmount;

    public DiscountController(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    public void calcDiscount(int purchaseAmount) {
        showGiveawayEvent(purchaseAmount);
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

    private void showGiveawayEvent(int purchaseAmount) {
        outputView.printGiveawayEventMessage();
        System.out.println(giveaway.checkGiveaway(purchaseAmount, discountAmount));
        outputView.printNewLine();
    }
}