package christmas.controller;

public class MainController {
    private final ScheduleController scheduleController = new ScheduleController();

    public void run() {
        scheduleController.setDate();
    }
}
