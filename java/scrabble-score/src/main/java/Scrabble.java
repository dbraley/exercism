
public class Scrabble {
    private final int score;

    public Scrabble(String input) {
        if (input != null) {
            score = input.toLowerCase().chars().mapToObj(i -> (char) i).mapToInt(this::scoreForChar).sum();
        } else {
            score = 0;
        }
    }

    private int scoreForChar(char i) {
        switch (i){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'l':
            case 'n':
            case 'r':
            case 's':
            case 't':
                return 1;
            case 'd':
            case 'g':
                return 2;
            case 'b':
            case 'c':
            case 'm':
            case 'p':
                return 3;
            case 'f':
            case 'h':
            case 'v':
            case 'w':
            case 'y':
                return 4;
            case 'k':
                return 5;
            case 'j':
            case 'x':
                return 8;
            case 'q':
            case 'z':
                return 10;
            default:
                return 0;
        }
    }

    public int getScore() {
        return score;
    }
}
