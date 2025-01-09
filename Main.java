import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to the Casino!");
        System.out.println("Please enter the amount of chips you would like to purchase. (Must be between 20 - 200 and a multiple of 10.)");

        int chip_balance = 0;
        while (chip_balance < 20 || chip_balance > 200 || chip_balance % 10 != 0) {
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

                    int bankerNumber = (int) rand.nextInt(1001);

                    System.out.println("A number between 0 and 1000 has been chosen.");
                    System.out.println("You have 10 guesses, the prize is 11 chips minus the number of guesses it took.");
                    System.out.println("If you run out of guesses, you lose 10 chips.");

                    for (int i = 0; i < 10; i++) {
                        int guess;
                        System.out.println("Enter your guess (Guess # " + i + " )");
                        try {
                            guess = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input, enter an integer.");
                            scanner.nextLine();
                            break;
                        }
                        if (guess > 1000) {
                            System.out.println("That was not correct, remember that you need to guess a number between 0 and 1000.");
                        } else if (guess == bankerNumber) {
                            System.out.println("That is correct!");
                            int prize = 11 - i;
                            chip_balance += prize;
                            System.out.println("You have been awarded " + prize + " chips. Your balance is now " + chip_balance + " chips.");
                            break;
                        } else {
                            System.out.println("Sorry, that was not correct - try again.");
                            if (i >= 9) {
                                System.out.println("You have run out of guesses, 10 chips will be deducted from your balance.");
                                chip_balance -= 10;
                                break;
                            }
                        }
                    }

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