package christmas.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GiveawayTest {
    private Giveaway giveaway;

    @BeforeEach
    void setUp() {
        giveaway = new Giveaway();
    }

    @DisplayName("총구매 금액을 충족했을 때 증정 이벤트 적용 로직을 검증한다.")
    @Test
    void checkGiveawayApplied() {
        int purchaseAmount = 120000;
        assertThat(giveaway.checkGiveaway(purchaseAmount)).isTrue();
    }

    @DisplayName("총구매 금액을 충족하지 못했을 때 증정 이벤트 적용 로직을 검증한다.")
    @Test
    void checkGiveawayNotApplied() {
        int purchaseAmount = 119999;
        assertThat(giveaway.checkGiveaway(purchaseAmount)).isFalse();
    }
}