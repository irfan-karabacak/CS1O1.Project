package tr.edu.ozyegin.cs101.wordlesolver;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        WordList wordList = new WordList();

        wordList.loadWords("C:/Users/alemd/IdeaProjects/wordle solver/src/tr/edu/ozyegin/cs101/wordlesolver/words.txt");

        Random random = new Random();

        boolean finished = false;

        Scanner scanner = new Scanner(System.in);

        int guessCount = 0;

        while (!finished) {
            int index;
            if(guessCount > 0) {
                index = wordList.generateNextGuessIndex();
            }else{
                index = 9737;
            }

            if(guessCount==6){
                finished = true;
                System.out.println("You lose the game, sorry i can't find the word.");
                break;
            }
            guessCount++;

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

            if(feedback.equalsIgnoreCase("ggggg")){
                finished = true;
                System.out.println("You win the game, guess count is " + guessCount);
                break;
            }

            Feedback actualFeedback = new Feedback(feedback);

            wordList.reduce(new Word(guess),actualFeedback);
            if(wordList.getWords().isEmpty()){
                finished=true;
                System.out.println("There is no word like this. Try again!");
                break;
            }

            System.out.println("Feedback was: " + feedback);
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
    public static boolean isGoodFeedback(String feedback) {
        int count=0;
        for(int i=0; i<feedback.length();i++){
            switch (feedback.charAt(i)) {
                case 'Y','y','G','g' -> count++;
            }
        }
        if(count>2){
            return true;
        }
        return false;
    }
}