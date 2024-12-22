package tr.edu.ozyegin.cs101.wordlesolver;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        WordList wordList = new WordList();

        wordList.loadWords("src/words.txt");

        Random random = new Random();

        boolean finished = false;

        Scanner scanner = new Scanner(System.in);

        while (!finished) {
            int index = random.nextInt(wordList.getWords().size());

            String guess = wordList.getWords().get(index);

            System.out.println("My guess is: " + guess);

            System.out.println("Enter feedback:");

            String feedback;
            boolean isValid;

            do {
                feedback = scanner.nextLine();

                isValid = isValidFeedback(feedback);

                if (!isValid) {
                    System.out.println("Invalid feedback. Try again.");
                }

            } while(!isValid);

            System.out.println("Feedback was: " + feedback);

            Feedback actualFeedback = new Feedback(feedback);

            wordList.reduce(new Word(guess),actualFeedback);

        }
    }

    public static boolean isValidFeedback(String input) {
        if (input.length() != WordleSolver.WORD_SIZE) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case 'b', 'B', 'y', 'Y', 'g', 'G':
                    break;
                default:
                    return false;
            }

        }

        return true;

    }
}