package christmas.domain.constants;

public enum ConstantDelimiter {
    ORDER_DELIMITER(","),
    MENU_DELIMITER("-");

    private final String message;

    ConstantDelimiter(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
