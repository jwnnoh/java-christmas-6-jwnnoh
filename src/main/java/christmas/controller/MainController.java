package christmas.controller;

public class MainController {
    private final ScheduleController scheduleController = new ScheduleController();
    private final OrderController orderController = new OrderController();

    public void run() {
        scheduleController.setDate();
        orderController.setMenu();
    }
}
