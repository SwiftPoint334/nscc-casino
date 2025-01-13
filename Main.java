import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Welcome to the Casino!");
        System.out.println("Please enter the amount of chips you would like to purchase. (Must be between 20 - 200 and a multiple of 10.)");

        int chip_balance = getValidChipBalance(scanner);

        boolean cashout = false;

        while (!cashout) {
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

                    if (chip_balance < 0) {
                        cashout = true;
                        System.out.println("You have run out of chips.");
                        break;
                    }

                    System.out.println("Do you want to continue playing? (enter) yes | (n) no");
                    String cashoutInput = scanner.nextLine().trim();

                    if (cashoutInput.equalsIgnoreCase("n")) {
                        cashout = true;
                    }

                    break;
                case "2":
                    System.out.println("Option 2 selected");

                    System.out.println("In this game you will roll two dice.");
                    System.out.println("You can roll as many times as you like, but you stop when you do not roll doubles.");
                    System.out.println("The total of all your rolls is added up, if you roll > 9 you get 7 chips, > 20 you get 10 chips");
                    System.out.println("If the game ends before you reach a total of 9, you lose 1 chip.");

                    int dice1;
                    int dice2;
                    int totalSum = 0;

                    while (true) {
                        dice1 = rand.nextInt(7);
                        dice2 = rand.nextInt(7);
                        totalSum = totalSum + dice1 + dice2;

                        System.out.println("You have rolled " + dice1 + " and " + dice2);
                        System.out.println("Your current sum is " + totalSum);

                        if (dice1 == dice2) {
                            System.out.println("You rolled doubles!");
                            System.out.println("Do you want to roll again? (enter) yes | (n) no");

                            String rollAgain = scanner.nextLine().trim();

                            if (rollAgain.equalsIgnoreCase("n")) {
                                break;
                            }
                        } else {
                            System.out.println("You did not roll doubles, the game is over!");
                            break;
                        }
                    }

                    if (totalSum >= 9 && totalSum < 20) {
                        System.out.println("You have won 7 chips!");
                        chip_balance += 7;
                    } else if (totalSum >= 20) {
                        System.out.println("You have won 10 chips");
                        chip_balance += 10;
                    } else {
                        System.out.println("You have lost 1 chip");
                        chip_balance -= 1;
                    }

                    if (chip_balance < 0) {
                        cashout = true;
                        System.out.println("You have run out of chips.");
                        break;
                    }


                    System.out.println("Do you want to continue playing? (enter) yes | (n) no");
                    cashoutInput = scanner.nextLine().trim();

                    if (cashoutInput.equalsIgnoreCase("n")) {
                        cashout = true;
                    }

                    break;
                case "3":
                    System.out.println("Option 3 selected");

                    System.out.println("In this game you will roll 2 dice. The total can never exceed 21.");
                    System.out.println("If you roll exactly 21 you will win 50 chips.");
                    System.out.println("You can choose to continue rolling the dice, or stop at any point.");
                    System.out.println("When you stop the banker will then roll an 8 sided dice repeatedly.");
                    System.out.println("The banker will stop when their total is greater than or equal to the total of the user, if they exceed 21 they will bust and you will win 12, otherwise, you lose 6 chips.");

                    int userDice1;
                    int userDice2;
                    int userSum = 0;
                    int bankerDice;
                    int bankerSum = 0;

                    System.out.println("Your turn");

                    while (true) {
                        userDice1 = rand.nextInt(7);
                        userDice2 = rand.nextInt(7);
                        userSum += userDice1 + userDice2;

                        System.out.println("You have rolled " + userDice1 + " and " + userDice2);
                        System.out.println("Your total is " + userSum);

                        if (userSum > 21) {
                            System.out.println("You have busted and lost 10 chips.");
                            chip_balance -= 10;
                            break;
                        }

                        System.out.println("Would you like to roll again? (enter) yes | (n) no");

                        String rollAgain = scanner.nextLine().trim();

                        if (rollAgain.equalsIgnoreCase("n")) {
                            break;
                        }
                    }

                    System.out.println("Dealer Turn");

                        while (bankerSum <= userSum) {

                            bankerDice = rand.nextInt(9);
                            bankerSum += bankerDice;

                            System.out.println("The banker rolled " + bankerDice + " and the banker's total is now " + bankerSum);

                        }

                        if (bankerSum > userSum && !(bankerSum <= 21)) {
                            System.out.println("The banker has won, you lose 6 chips.");
                            chip_balance -= 6;
                        } else {
                            System.out.println("The banker has busted, you win 12 chips");
                            chip_balance += 12;
                        }

                        if (chip_balance < 0) {
                            cashout = true;
                            System.out.println("You have run out of chips.");
                            break;
                        }

                        System.out.println("Do you want to continue playing? (enter) yes | (n) no");
                        cashoutInput = scanner.nextLine().trim();

                        if (cashoutInput.equalsIgnoreCase("n")) {
                            cashout = true;
                        }


                        break;

                case "q":
                    System.out.println("Quit selected");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }

        System.out.println("You have cashed out with " + chip_balance + " chips. Thank you for playing.");
    }

    public static int getValidChipBalance(Scanner scanner) {
        int chips;
        while (true) {
            try {
                chips = scanner.nextInt();
                scanner.nextLine();
                if (chips >= 20 && chips <= 200 && chips % 10 == 0) {
                    return chips;
                } else {
                    System.out.println("Must be between 20 and 200, and be a multiple of 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Must be an integer.");
            }
        }
    }

    public static void gameOne() {
        System.out.println("Option 1 selected");

        int bankerNumber = (int) rand.nextInt(1001);

        System.out.println("A number between 0 and 1000 has been chosen.");
        System.out.println("You have 10 guesses, the prize is 11 chips minus the number of guesses it took.");
        System.out.println("If you run out of guesses, you lose 10 chips.");

    }

    public static void gameTwo() {

    }

    public static void gameThree() {

    }

}