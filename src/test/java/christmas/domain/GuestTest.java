package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    @Test
    public void testGuestWithInvalidDate() {
        // Given
        List<Integer> invalidDates = List.of(-1, 0, 32);

        // When, Then
        for (int invalidDate : invalidDates) {
            assertThatThrownBy(() -> new Guest(invalidDate))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @DisplayName("방문 날짜가 25일 이전이면 디데이 이벤트 적용이 가능하다.")
    @Test
    public void testCheckDDayWithDateBeforeDDay() {
        // Given
        Guest guest = new Guest(24);

        // When, Then
        assertThat(guest.checkDDay(D_DAY)).isTrue();
    }
    @DisplayName("방문 날짜가 25일이면 디데이 이벤트 적용이 가능하다.")
    @Test
    public void testCheckDDayWithDateDDay() {
        // Given
        Guest guest = new Guest(25);

        // When, Then
        assertThat(guest.checkDDay(D_DAY)).isTrue();
    }

    @DisplayName("방문 날짜가 25일 이후면 디데이 이벤트 적용이 불가능하다.")
    @Test
    public void testCheckDDayWithDateAfterDDay() {
        // Given
        Guest guest = new Guest(26);

        // When, Then
        assertThat(guest.checkDDay(D_DAY)).isFalse();
    }

    @DisplayName("방문 날짜가 금요일이면 주말이다.")
    @Test
    public void testCheckDayWeekendWithFriday() {
        // Given
        Guest guest = new Guest(1);

        // When, Then
        assertThat(guest.checkDayWeekend()).isTrue();
    }


    @DisplayName("방문 날짜가 토요일이면 주말이다.")
    @Test
    public void testCheckDayWeekendWithSunday() {
        // Given
        Guest guest = new Guest(2);

        // When, Then
        assertThat(guest.checkDayWeekend()).isTrue();
    }

    @DisplayName("방문 날짜가 일요일~목요일이면 평일이다.")
    @Test
    public void testCheckDayWeekendWithOthers() {
        for (int i = 3; i <= 7; i++) {
            // Given
            Guest guest = new Guest(i);

            // When, Then
            assertThat(guest.checkDayWeekend()).isFalse();
        }
    }

    @DisplayName("방문 날짜가 크리스마스이면 스페셜 데이이다.")
    @Test
    public void testCheckSpecialDayWithChristmas() {
        // Given
        Guest guest = new Guest(25);

        // When, Then
        assertThat(guest.checkSpecialDay()).isTrue();
    }

    @DisplayName("방문 날짜가 일요일이면 스페셜 데이이다.")
    @Test
    public void testCheckSpecialDayWithFriday() {
        // Given
        Guest guest = new Guest(24);

        // When, Then
        assertThat(guest.checkSpecialDay()).isTrue();
    }

    @DisplayName("25일을 제외한 월요일~토요일은 스페셜 데이가 아니이다.")
    @Test
    public void testCheckSpecialDayWithOthers() {
        for (int i = 4; i <= 9; i++) {
            // Given
            Guest guest = new Guest(i);

            // When, Then
            assertThat(guest.checkSpecialDay()).isFalse();
        }
    }
}