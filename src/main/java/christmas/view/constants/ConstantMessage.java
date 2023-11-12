package christmas.view.constants;

public enum ConstantMessage {
    ERROR_PREFIX("[ERROR] "),
    ERROR_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ERROR_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    REQUEST_DATE_OF_VISIT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_ORDER("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFIT_INTRO_PREFIX("12월 "),
    BENEFIT_INTRO_SUFFIX("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("<주문 메뉴>"),
    ORDER_AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    DISCOUNT_TYPE("<혜택 내역>"),
    D_DAY_DISCOUNT("크리스마스 디데이 할인: "),
    WEEKDAY_DISCOUNT("평일 할인: "),
    WEEKEND_DISCOUNT("주말 할인: "),
    SPECIAL_DISCOUNT("특별 할인: "),
    GIVEAWAY_DISCOUNT("증정 이벤트: "),
    DISCOUNT_AMOUNT("<총혜택 금액>"),
    ORDER_AMOUNT_AFTER_DISCOUNT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>"),
    AMOUNT_UNIT("원"),
    DISCOUNT_AMOUNT_PREFIX("-");
    private final String message;

    ConstantMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
