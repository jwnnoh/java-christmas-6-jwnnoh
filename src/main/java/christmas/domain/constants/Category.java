package christmas.domain.constants;

public enum Category {
    APPETIZER("애피타이저"),
    MAIN_DISH("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    public final String category;

    Category(final String category) {
        this.category = category;
    }
}
