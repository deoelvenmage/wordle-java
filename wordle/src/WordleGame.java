import java.util.Scanner;

public class WordleGame implements WordGame{
    String word;

    public WordleGame(String word) {
        this.word = word;
    }

    // Display the opening message for a game of Wordle
    public void displayOpeningMessage() {
        System.out.println("Welcome to Wordle!");
        System.out.println("How to play:");
        System.out.println("When prompted, guess a 5-letter word until you get it correct.");
        System.out.println("Each guess will be displayed back to you with feedback. If you guess a letter...");
        System.out.println("   - correctly and in the right position, a ! will be next to it.");
        System.out.println("   - correctly but in the wrong position, a * will be next to it.");
        System.out.println("   - entirely incorrectly, a - will be next to it.");
        System.out.println("Make sure all inputs are lowercase and the exact expected length for the time being.");
    }

    public void play() {
        displayOpeningMessage();
        System.out.println("Enter your guess, or [q]uit early: ");
        String guess = "-";
        Scanner input = new Scanner(System.in);
        while (guess.compareTo("q") != 0) {
            guess = input.nextLine();

            // This checking ensures that validateWord will work
            if ((guess == null) || (guess.length() != word.length())) {
                System.out.println("Incorrect letter count; please enter a new word.");
            }
            else { // Quick check if they're the same; can just finish here if they are
                if (guess.compareTo(word) == 0) {
                    System.out.println("Congratulations, you did it!");
                    return;
                }

                //if not, look over each letter in the guess and tell the user how they did
                String code = validateWord(guess);
                for (int i = 0; i < word.length(); i++) {
                    System.out.print(code.charAt(i));
                    System.out.print(guess.charAt(i));
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
        return;
    }

    private String validateWord(String guess) {
        String code = "";
        for (int i = 0; i < word.length(); i++) {
            if (guess.charAt(i) == word.charAt(i)) {
                code = code + "!"; //Can do + operator or concat() for string concatenation
            }
            else {
                boolean foundAny = false;
                for (int j = 0; j < word.length(); j++) {
                    if (guess.charAt(i) == word.charAt(j)) {
                        foundAny = true;
                    }
                }
                if (foundAny) {
                    code = code + "*";
                } else {
                    code = code + "-";
                }
            }
        }
        return code;
    }
}
