import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Casino!");
        System.out.println("Please enter the amount of chips you would like to purchase. (Must be between 20 - 200 and a multiple of 10.)");

        int chip_balance = 0;
        while (chip_balance < 20 || chip_balance > 200) {
            try {
                chip_balance = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer between 20 and 200.");
                scanner.nextLine();
            }
            if (chip_balance < 20 || chip_balance > 200 || chip_balance % 10 != 0) {
                System.out.println("Input is invalid, must be between 20 and 200, and be a multiple of 10.");
            }
        }

        while (true) {
            System.out.println("You have " + chip_balance + " chips.");
            System.out.println("Please select an option: (1) Game 1 | (2) Game 2 | (3) Game 3 | (q) Quit");

            String menu_input = scanner.nextLine();

            switch (menu_input) {
                case "1":
                    System.out.println("Option 1 selected");
                    break;
                case "2":
                    System.out.println("Option 2 selected");
                    break;
                case "3":
                    System.out.println("Option 3 selected");
                    break;
                case "q":
                    System.out.println("Quit selected");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
}