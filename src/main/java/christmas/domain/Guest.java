package christmas.domain;

import christmas.domain.constants.Calender;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Guest {
    private static final int DATE_MAX = 31;
    private static final int DATE_MIN = 1;
    private final int date;


    public Guest(int date) {
        this.date = validate(date);
    }

    private int validate(int date) {
        if (!(date >= DATE_MIN && date <= DATE_MAX)) {
            throw new IllegalArgumentException();
        }
        return date;
    }

    public int getDate() {
        return date;
    }

    public boolean checkDDay(int dDay) {
        return date <= dDay;
    }

    public boolean checkDayWEEKEND() {
        LocalDate dateTime = LocalDate.of(Calender.YEAR.getValue(), Calender.MONTH.getValue(), this.date);
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean checkSpecialDay() {
        LocalDate dateTime = LocalDate.of(Calender.YEAR.getValue(), Calender.MONTH.getValue(), this.date);
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

        if (dateTime.getDayOfMonth() == 25) {
            return true;
        }
        return dayOfWeek == DayOfWeek.SUNDAY;
    }
}
