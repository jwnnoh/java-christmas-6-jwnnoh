package christmas.domain.constants;

public enum Calender {
    YEAR(2023),
    MONTH(12);

    private final int value;

    Calender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
