package christmas.domain.constants;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, Category.APPETIZER),
    TAPAS("타파스", 5500, Category.APPETIZER),
    CAESAR_SALAD("시저셀러드", 8000, Category.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, Category.MAIN_DISH),
    BBQ_RIB("바비큐립", 54000, Category.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35000, Category.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, Category.MAIN_DISH),
    CHOCOLATE_CAKE("초코케이크", 15000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5000, Category.DESSERT),
    ZERO_COKE("제로콜라", 3000, Category.DRINK),
    RED_WINE("레드와인", 60000, Category.DRINK),
    CHAMPAGNE("삼페인", 25000, Category.DRINK);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public Category getCategory() {
        return category;
    }

    public static Category getCategoryByName(String menuItem) {
        Category type = null;
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuItem)) {
                type = menu.getCategory();
            }
        }
        return type;
    }

}

