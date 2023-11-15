package christmas.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DecimalFormatFormatterTest {

    @DisplayName("주어진 숫자의 포맷팅을 검증한다.")
    @CsvSource(value = {"5500:5,500", "120000:120,000", "1200000:1,200,000"}, delimiter = ':')
    @ParameterizedTest
    void testReturnDecimalFormatAmount(int amount, String expectedFormattedAmount) {
        DecimalFormatFormatter formatter = new DecimalFormatFormatter();

        assertThat(formatter.returnDecimalFormatAmount(amount)).isEqualTo(expectedFormattedAmount);
    }
}