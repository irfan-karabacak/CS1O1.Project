package tr.edu.ozyegin.cs101.wordlesolver;

public class Word {
    private String letters;

    public Word(String letters) {
        this.letters = letters;
    }

    public Feedback generateFeedbackWithActualWord(Word actualWord) {
        char[] feedback = new char[WordleSolver.WORD_SIZE];

        for (int i = 0; i < feedback.length; i++) {
            feedback[i] = 'B';
        }

        char[] actualLetters = actualWord.letters.toCharArray();

        for (int i = 0; i < this.letters.length(); i++) {
            if (this.letters.charAt(i) == actualLetters[i]) {
                feedback[i] = 'G';
                actualLetters[i] = ' ';
            }
        }

        for (int i = 0; i < this.letters.length(); i++) {
            char c = this.letters.charAt(i);

            for (int j = 0; j < actualLetters.length; j++) {
                if (c == actualLetters[j]) {
                    if(feedback[i]!='G') {
                        feedback[i] = 'Y';
                        actualLetters[j] = ' ';
                    }
                }
            }
        }


        return new Feedback(new String(feedback));
    }

    public static void main(String[] args) {
        Word actual = new Word("sauna");
        Word guess = new Word("sanes");

        Feedback feedback = guess.generateFeedbackWithActualWord(actual);

        System.out.println(feedback);

    }


}