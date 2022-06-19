import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Game {
    private ArrayList<String> wordList;

    public Game() {
        wordList = new ArrayList<String>();
    }

    // Read in words from the file; this will be used in all word games
    public void setWords() {
        try {
            File wordfile = new File("src/words.txt");
            Scanner reader = new Scanner(wordfile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                wordList.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred trying to read words.txt.");
            e.printStackTrace();
        }
        return;
    }

    public String getRandomWord() {
        Random rand = new Random();
        int randIndex = rand.nextInt(wordList.size());
        String word = wordList.get(randIndex); //Interesting that you can't use array indexing
        return word;
    }

    public void gameloop() {
        String choice = "p";
        Scanner input = new Scanner(System.in); //Bro if this is not the most scuffed inputting tech I've seen
        while (choice.compareTo("q") != 0) {    //Aaand it's even more jank now
            System.out.print("[p]lay or [q]uit? ");
            choice = input.nextLine();
            if (choice.compareTo("p") == 0) {
                WordleGame wordle = new WordleGame(getRandomWord());
                wordle.play();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setWords();
        game.gameloop();

        return;
    }
}