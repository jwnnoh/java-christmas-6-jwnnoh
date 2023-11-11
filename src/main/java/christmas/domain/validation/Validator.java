package christmas.domain.validation;

import java.util.HashSet;
import java.util.Set;

import static christmas.domain.constants.ConstantDelimiter.MENU_DELIMITER;

public class Validator {

    public void validateInputInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public void validateDuplicates(String[] splitOrder) {
        Set<String> identifyingItem = new HashSet<>();
        for (String orderItem :
                splitOrder) {
            identifyingItem.add(orderItem.substring(0, orderItem.indexOf(MENU_DELIMITER.getMessage())));
        }

        if (identifyingItem.size() != splitOrder.length) {
            throw new IllegalArgumentException();
        }
    }
}
