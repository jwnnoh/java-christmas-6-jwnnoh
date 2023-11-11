package christmas.controller;

public class MainController {
    private final ScheduleController scheduleController = new ScheduleController();
    void run() {
        scheduleController.setDate();
    }
}
