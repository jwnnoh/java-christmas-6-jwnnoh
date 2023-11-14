package christmas.domain.service;

import christmas.domain.constants.EventBadge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventBadgeDrawTest {

    @DisplayName("이벤트 배지가 총혜택 금액에 맞게 증정되는지 검증한다.")
    @Test
    public void testDetermineBadge() {
        EventBadgeDraw eventBadgeDraw = new EventBadgeDraw();

        // 총혜택 금액 20000원 이상 -> 산타
        int santa = 20000;
        assertThat(eventBadgeDraw.determineBadge(santa)).isEqualTo(EventBadge.SANTA.getName());

        // 총혜택 금액 1000원 이상 -> 트리
        int tree = 19999;
        assertThat(eventBadgeDraw.determineBadge(tree)).isEqualTo(EventBadge.TREE.getName());

        // 총혜택 금액 5000원 이상 -> 별
        int star = 9999;
        assertThat(eventBadgeDraw.determineBadge(star)).isEqualTo(EventBadge.STAR.getName());
    }
}