/*
Programmer: Tarique Turnbull
StudentID: 12177936
Course: COIT11134 Object-Oriented Programming
Assessment: Practical Assessment 2

This class controls functionality for various Utility methods and classes
 */

package com.mycompany.cqrealestate_gui;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Utils {
    public static class Text { // Various text and alert methods

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

        public static void clearFields(TextField[] fields) {
            for (TextField field : fields) {
                field.clear();
            }
        }
    } // END OF TEXT CLASS

    public static class Validator { // Various validation methods

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

        // Checks if TextField array is empty
        public static boolean isNotEmpty (TextField[] fields) {
            for (TextField field : fields) {
                final String INPUT_FIELD = field.getText();
                if (INPUT_FIELD.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        // Checks if input is greater than min value
        public static boolean isGreaterThan(String input, Number min) {
            return Double.parseDouble(input) > min.doubleValue();
        }

        // Checks if an ID exists in an object array.
        // Returns false if ID exists, true if it doesn't
        public static boolean idExists(String inputId, List<? extends Identifier> objects) {
            for (Identifier object : objects) {
                final int ID = object.getId();
                if (ID == Integer.parseInt(inputId)) {
                    return false;
                }
            }
            return true;
        }

    } // END OF VALIDATOR CLASS

}
