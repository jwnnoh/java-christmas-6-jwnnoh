package christmas.domain.constants;

public enum Constant {
    ORDER_DELIMITER(","),
    MENU_DELIMITER("-"),
    PRINT_DELIMITER(" "),
    MENU_ITEM_UNIT("개");


    private final String message;

    Constant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
