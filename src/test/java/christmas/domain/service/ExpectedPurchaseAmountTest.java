package christmas.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExpectedPurchaseAmountTest {
    private ExpectedPurchaseAmount expectedPurchaseAmount = new ExpectedPurchaseAmount();


    @BeforeEach
    void setUp() {
        expectedPurchaseAmount = new ExpectedPurchaseAmount();
    }

    @DisplayName("총구매 금액이 12만원 이상인 경우일 때 증정 로직을 검증한다.")
    @Test
    void calculatePurchaseAmountAboveRequirement() {
        int purchaseAmount = 120000;
        int discountAmount = 29096;

        assertThat(expectedPurchaseAmount.calculate(purchaseAmount, discountAmount))
                .isEqualTo(115904);
    }

    @DisplayName("총구매 금액이 12만원 이하인 경우일 때 증정 로직을 검증한다.")
    @Test
    void calculatePurchaseAmountBelowRequirement() {
        int purchaseAmount = 110000;
        int discountAmount = 9096;

        assertThat(expectedPurchaseAmount.calculate(purchaseAmount, discountAmount))
                .isEqualTo(100904);
    }
}