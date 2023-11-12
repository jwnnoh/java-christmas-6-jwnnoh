package christmas.domain.service;

public class DDayCalculator {
    private static final int START_AMOUNT = 1000;
    private static final int INCREASE_AMOUNT = 100;

    public int calcDDAyDiscount(int visitDate, int dDay, int discountAmount) {
        int leftDay = dDay - visitDate;
        int tmpDiscountAmount =  START_AMOUNT + (((dDay - 1) - leftDay) * INCREASE_AMOUNT);
        discountAmount += tmpDiscountAmount;
        return tmpDiscountAmount;
    }
}
