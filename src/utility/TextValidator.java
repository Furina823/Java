package utility;

public class TextValidator {

    public static boolean isNumber(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(text.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean doesNotContainComma(String text) {
        if (text == null) {
            return true; // Assuming a null value does not contain commas
        }
        return !text.contains(",");
    }

}
