package tr.edu.ozyegin.cs101.wordlesolver;

public class Wordle {

    private final Word secretWord;

    private int numberOfTries;

    private boolean isSolved;

    public Wordle(Word secretWord){
        this.secretWord = secretWord;
        this.numberOfTries = 0;
        this.isSolved = false;
    }

    public Feedback guessAndGetFeedbackForWord(Word guess){
        Feedback feedback = guess.generateFeedbackWithActualWord(secretWord);
        if(!this.isSolved){
            this.numberOfTries++;

            if (feedback.isAllGreen()){
                this.isSolved = true;
            }
        }

        return feedback;
    }

    public int getNumberOfTries(){
        return numberOfTries;
    }

    public boolean isSolved() {
        return isSolved;
    }
}
