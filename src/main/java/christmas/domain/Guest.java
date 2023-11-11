package christmas.domain;

public class Guest {
    private static final int DATE_MAX = 31;
    private static final int DATE_MIN = 1;
    private final int date;

    public Guest(int date) {
        this.date = validate(date);
    }

    private int validate(int date) {
        if (!(date >= 1 && date <= 31)) {
            throw new IllegalArgumentException();
        }
        return date;
    }

    public int getDate() {
        return date;
    }

//    public boolean contains(int date) {
//        //날짜가 포함되어 있는지 확인한다.
//    }

}
