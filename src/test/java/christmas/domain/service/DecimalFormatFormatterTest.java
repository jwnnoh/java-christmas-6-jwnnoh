package christmas.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DecimalFormatFormatterTest {

    @DisplayName("주어진 숫자의 포맷팅을 검증한다.")
    @Test
    void testReturnDecimalFormatAmount() {
        DecimalFormatFormatter formatter = new DecimalFormatFormatter();

        int amount1 = 5500;
        String expectedFormattedAmount1 = "5,500";
        assertThat(formatter.returnDecimalFormatAmount(amount1)).isEqualTo(expectedFormattedAmount1);

        int amount2 = 120000;
        String expectedFormattedAmount2 = "120,000";
        assertThat(formatter.returnDecimalFormatAmount(amount2)).isEqualTo(expectedFormattedAmount2);

        int amount3 = 1200000;
        String expectedFormattedAmount3 = "1,200,000";
        assertThat(formatter.returnDecimalFormatAmount(amount3)).isEqualTo(expectedFormattedAmount3);
    }
}