package util;

public class InputValidator {

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 4;
    }

    public static boolean isValidOption(String option) {

        if (option == null) {
            return false;
        }

        option = option.trim().toUpperCase();

        return option.equals("A") ||
                option.equals("B") ||
                option.equals("C") ||
                option.equals("D");
    }
}