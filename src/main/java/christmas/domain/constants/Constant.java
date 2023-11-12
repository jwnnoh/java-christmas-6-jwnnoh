package christmas.domain.constants;

public enum Constant {
    ORDER_DELIMITER(","),
    MENU_DELIMITER("-"),
    PRINT_DELIMITER(" "),
    MENU_ITEM_UNIT("개"),
    AMOUNT_UNIT("원"),
    BENEFIT_UNAVAILABLE("없음");


    private final String message;

    Constant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
