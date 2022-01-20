// Name: Ruxun Xiang
// USC NetID: ruxunxia
// CS 455 PA4
// Fall 2020

/**
 * Create a score table, and store the score for each letter.
 */
public class ScoreTable {
    // define the score value
    public static final int ONE_SCORE = 1;
    public static final int TWO_SCORE = 2;
    public static final int THREE_SCORE = 3;
    public static final int FOUR_SCORE = 4;
    public static final int FIVE_SCORE = 5;
    public static final int EIGHT_SCORE = 8;
    public static final int TEN_SCORE = 10;

    // define the score of each upper case letter
    public static final char[] ONE_SCORE_LETTER = {'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R'};
    public static final char[] TWO_SCORE_LETTER = {'D', 'G'};
    public static final char[] THREE_SCORE_LETTER = {'B', 'C', 'M', 'P'};
    public static final char[] FOUR_SCORE_LETTER = {'F', 'H', 'V', 'W', 'Y'};
    public static final char[] FIVE_SCORE_LETTER = {'K'};
    public static final char[] EIGHT_SCORE_LETTER = {'J', 'X'};
    public static final char[] TEN_SCORE_LETTER = {'Q', 'Z'};

    // use an array of integer to store the scores, so define the length of the array
    public static final int ALPHABET_LENGTH = 26;

    // for one upper case letter, we have to find the relative location of letter 'A',
    // so the position of this letter in the array can be determined
    public static final char UPPER_A = 'A';

    // deal with lower case letter issue
    public static final char LOWER_A = 'a';

    // the score table instance variable
    private int[] alphabet;


    public ScoreTable() {
        // initialize the score table
        // the position of each upper case letter is defined as "letter - UPPER_A"
        alphabet = new int[ALPHABET_LENGTH];
        for (char letter : ONE_SCORE_LETTER){
            alphabet[letter - UPPER_A] = ONE_SCORE;
        }
        for (char letter : TWO_SCORE_LETTER){
            alphabet[letter - UPPER_A] = TWO_SCORE;
        }
        for (char letter : THREE_SCORE_LETTER){
            alphabet[letter - UPPER_A] = THREE_SCORE;
        }
        for (char letter : FOUR_SCORE_LETTER){
            alphabet[letter - UPPER_A] = FOUR_SCORE;
        }
        for (char letter : FIVE_SCORE_LETTER){
            alphabet[letter - UPPER_A] = FIVE_SCORE;
        }
        for (char letter : EIGHT_SCORE_LETTER){
            alphabet[letter - UPPER_A] = EIGHT_SCORE;
        }
        for (char letter : TEN_SCORE_LETTER){
            alphabet[letter - UPPER_A] = TEN_SCORE;
        }
    }

    /**
     * given a word, calculate its score using the score table
     * @param word the word to calculate
     * @return the score of this word
     */
    public int getWordScore(String word){
        int score = 0;
        int index;
        char letter;
        String lowerCaseWord = word.toLowerCase(); // convert the word to lower case uniformly
        // no matter lower case or upper case, the relative location of 'a' or 'A' for the letter is the same
        // for each lower case letter, find its score based on the location "letter - LOWER_A",
        // and  counts for the total score of the word
        for (int i = 0; i < lowerCaseWord.length(); i++){
            letter = lowerCaseWord.charAt(i);
            index = letter - LOWER_A;
            score += alphabet[index];
        }
        return score;
    }
}
