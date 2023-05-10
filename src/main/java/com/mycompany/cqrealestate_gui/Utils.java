package com.mycompany.cqrealestate_gui;

public class Utils {
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

    }

    public static class FileHandler {
        public static void writeLandToFile(Land land) {
            // TODO
        }

        public static void writeHouseAndLandToFile(HouseAndLand houseAndLand) {
            // TODO
        }
    }
}
