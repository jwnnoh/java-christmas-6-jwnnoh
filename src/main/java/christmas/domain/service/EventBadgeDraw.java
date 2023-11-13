package christmas.domain.service;

import christmas.domain.constants.Constant;
import christmas.domain.constants.EventBadge;

public class EventBadgeDraw {
    private static final int MAX_DISCOUNT = 20000;
    private static final int MEDIUM_DISCOUNT = 10000;
    private static final int LOWEST_DISCOUNT = 5000;
    public String determineBadge(int discountAmount) {
        if (discountAmount >= MAX_DISCOUNT) {
            return EventBadge.SANTA.getName();
        }
        if (discountAmount >= MEDIUM_DISCOUNT) {
            return EventBadge.TREE.getName();
        }
        if (discountAmount >= LOWEST_DISCOUNT) {
            return EventBadge.STAR.getName();
        }
        return Constant.BENEFIT_UNAVAILABLE.getMessage();
    }
}
