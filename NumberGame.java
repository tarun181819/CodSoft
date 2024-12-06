import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random rn = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int round = 1;
        System.out.println("Welcome to the number Guessing Game");
        while(playAgain){
            System.out.println("\n --- Round " + round + "---");
            int lowerBound = 1;
            int upperBound = 100;
            int maxAttempts = 7;
            int attempts = 0;
            int randomNumber = rn.nextInt(upperBound -lowerBound +1) + lowerBound;
            System.out.println("I have picked a number between " + lowerBound + " and "+ upperBound + ". Can you guess it?" );
            System.out.println("You have "+ maxAttempts + "attempts.");
            boolean guessedCorrectly = false;
            while (attempts < maxAttempts) {
                attempts++;
                System.out.println("Attempt " + attempts + ": Enter your guess: ");
                int guess = sc.nextInt();
                if(guess == randomNumber){
                    System.out.println("Congratulations! You guessed it right.");
                    guessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1);
                    break;
                }
                else if(guess<randomNumber){
                    System.out.println("Too low! Try again.");
                }
                else{
                    System.out.println("Too high! Try again.");
                }
            }
            if(!guessedCorrectly){
                System.out.println("Sorry , you've used all atempts. The correct number was: " + randomNumber);
            }
            System.out.println("Your total score so far: "+ totalScore);
            System.out.println("Do you want to play another round? (yes/no): ");
            String response = sc.next();
            playAgain = response.equalsIgnoreCase("yes");
            if(playAgain){
                round++;
            }
            else{
                System.out.println("Thanks for Playing ! your final score is: "+ totalScore);
            }
        }
        sc.close();
    }    
}
