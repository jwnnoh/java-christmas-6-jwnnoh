package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GuestTest {
    private static final int D_DAY = 25;

    @DisplayName("방문 날짜를 정확히 가져온다.")
    @Test
    public void testGetDate() {
        // Given, When
        Guest guest = new Guest(15);

        // Then
        assertThat(guest.getDate()).isEqualTo(15);
    }

    @DisplayName("1~31범위 밖의 숫자는 예외를 발생시킨다.")
    @ValueSource(ints = {-1, 0, 32})
    @ParameterizedTest
    public void testGuestWithInvalidDate(int invalidDate) {
        assertThatThrownBy(() -> new Guest(invalidDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("방문 날짜가 1일~25일이면 디데이 이벤트 적용이 가능하다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25})
    @ParameterizedTest
    public void testCheckDDayWithDateBeforeDDay(int date) {
        // Given
        Guest guest = new Guest(date);

        // When, Then
        assertThat(guest.checkDDay(D_DAY)).isTrue();
    }

    @DisplayName("방문 날짜가 25일 이후면 디데이 이벤트 적용이 불가능하다.")
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    @ParameterizedTest
    public void testCheckDDayWithDateAfterDDay(int date) {
        // Given
        Guest guest = new Guest(date);

        // When, Then
        assertThat(guest.checkDDay(D_DAY)).isFalse();
    }

    @DisplayName("방문 날짜가 금요일하고 토요일이면 주말이다.")
    @ValueSource(ints = {1, 2})
    @ParameterizedTest
    public void testCheckDayWeekendWithFridaySaturday(int date) {
        // Given
        Guest guest = new Guest(date);

        // When, Then
        assertThat(guest.checkDayWeekend()).isTrue();
    }

    @DisplayName("방문 날짜가 일요일~목요일이면 평일이다.")
    @ValueSource(ints = {3, 4, 5, 6, 7})
    @ParameterizedTest
    public void testCheckDayWeekendWithOthers(int date) {
        // Given
        Guest guest = new Guest(date);

        // When, Then
        assertThat(guest.checkDayWeekend()).isFalse();
    }

    @DisplayName("방문 날짜가 크리스마스와 일요일이면 스페셜 데이이다.")
    @ValueSource(ints = {3, 10, 17, 24, 25})
    @ParameterizedTest
    public void testCheckSpecialDayWithChristmas(int date) {
        // Given
        Guest guest = new Guest(date);

        // When, Then
        assertThat(guest.checkSpecialDay()).isTrue();
    }

    @DisplayName("25일을 제외한 월요일~토요일은 스페셜 데이가 아니이다.")
    @ValueSource(ints = {4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30})
    @ParameterizedTest
    public void testCheckSpecialDayWithOthers(int date) {
        // Given
        Guest guest = new Guest(date);

        // When, Then
        assertThat(guest.checkSpecialDay()).isFalse();
    }
}