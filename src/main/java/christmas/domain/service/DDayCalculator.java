package christmas.domain.service;

public class DDayCalculator {
    private static final int START_AMOUNT = 1000;
    private static final int INCREASE_AMOUNT = 100;

    public int calcDDAyDiscount(int visitDate, int dDay) {
        int leftDay = dDay - visitDate;
        return START_AMOUNT + (((dDay - 1) - leftDay) * INCREASE_AMOUNT);
    }
}
