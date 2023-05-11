package com.mycompany.cqrealestate_gui;
import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static class Text {

        // Prints a separator line
        public static String separator(int count) {
            return ("*" + "~".repeat(count) + "*");
        }

        public static void showConfirmation(String message) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
            alert.showAndWait();
        }

        public static void showError(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR, message);
            alert.showAndWait();
        }
    }

    public static class Validator {

        // Checks if input is an int
        public static boolean isInteger(String input) {
            try {
                Integer.parseInt(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Checks if input is a double
        public static boolean isDouble(String input) {
            try {
                Double.parseDouble(input);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Checks if input is a name
        public static boolean isName(String input) {
            return input.matches("[a-zA-Z]+");
        }

        // Checks if input is a phone number
        public static boolean isPhoneNumber(String input) {
            return input.length() == 8 && input.matches("[0-9]+");
        }

        // Checks if date is of the correct format
        public static boolean isDate(String input) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(input, formatter);
                return formatter.format(date).equals(input);
            } catch (Exception e) {
                return false;
            }
        }
    }

}
