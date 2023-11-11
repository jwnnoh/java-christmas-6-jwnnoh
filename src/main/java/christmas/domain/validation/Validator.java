package christmas.domain.validation;

import java.util.Map;

public class Validator {

    public void validateInputInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public int validateQuantityInteger(String quantity) {
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    public String validateDuplicates(String itemDetail, Map<String, Integer> splitMenu) {
        if (!splitMenu.isEmpty()) {
            if (splitMenu.containsKey(itemDetail)) {
                throw new IllegalArgumentException();
            }
        }
        return itemDetail;
    }
}
