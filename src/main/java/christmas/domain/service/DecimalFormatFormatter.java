package christmas.domain.service;

import java.text.DecimalFormat;

public class DecimalFormatFormatter {
    private static final String REWARD_FORMAT = "###,###";

    public String returnDecimalFormatAmount(int amount) {
        DecimalFormat decimalFormat = new DecimalFormat(REWARD_FORMAT);
        return decimalFormat.format(amount);
    }
}