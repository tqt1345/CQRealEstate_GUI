package com.mycompany.cqrealestate_gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static class Text {

        // Prints a separator line
        public static String separator(int count) {
            return ("*" + "~".repeat(count) + "*");
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
    }
    // End of Validator class


    /*
    public static class FileHandler {
        public static void writeLandToFile(Land land) {
            // TODO
            try {
                File file = new File("land.txt"); // Change the file name and path as per your requirements
                FileWriter writer = new FileWriter(file, true);
                writer.write(land.toString() + "\n");
                writer.close();
            } catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }

        public static void writeHouseAndLandToFile(HouseAndLand houseAndLand) {
            // TODO
        }
    }
     */

}
