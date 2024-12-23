package tr.edu.ozyegin.cs101.wordlesolver;

import java.io.IOException;
import java.util.HashMap;

public class Analysis {
    public static void main(String[] args) throws IOException {
        WordList wordList = new WordList();
        wordList.loadWords("src/tr/edu/ozyegin/cs101/wordlesolver/words.txt");

        int minOfMax = Integer.MAX_VALUE;

        for(int i=0;i < wordList.getWords().size(); i++) {

            String guess = wordList.getWords().get(i);
            Word guessWord = new Word(guess);

            int maxCount = maximumFeedbackSetCountForWord(wordList, guessWord);

            if(i % 500 == 0){
                System.out.println("Processed " + i + " out of " + wordList.getWords().size() + " words.");
            }

            if(maxCount <= minOfMax) {
                minOfMax=maxCount;
                System.out.println("Word " + guess + " : " + maxCount + " at index: " + i);
            }
        }

    }

    public static int maximumFeedbackSetCountForWord(WordList wordList, Word word){
        HashMap<String, Integer> feedbackToCountMap = new HashMap<>();

        for(String actualWordString : wordList.getWords()){
            Word actualWord = new Word(actualWordString);

            Feedback feedback = word.generateFeedbackWithActualWord(actualWord);

            if(!feedbackToCountMap.containsKey(feedback.toString())){
                feedbackToCountMap.put(feedback.toString(), 0);
            }

            feedbackToCountMap.replace(feedback.toString(), feedbackToCountMap.get(feedback.toString()) + 1);

        }
        int max = 0;

        for(int count : feedbackToCountMap.values()){
            if(count > max){
                max = count;
            }
        }
        return max;

    }
}
