import java.util.Scanner;

public class Board {
    private int dimensions;
    private int mines;
    private Mine[][] board;
    private int selectedBoxes;
    private boolean endGame;

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setSelectedBoxes(int selectedBoxes) {
        this.selectedBoxes += selectedBoxes;
    }

    public int getSelectedBoxes() {
        return selectedBoxes;
    }

    public Mine[][] getBoard() {
        return board;
    }

    public int getDimensions() {
        return dimensions;
    }

    public int getMines() {
        return mines;
    }


    public Board() {
        Scanner scan = new Scanner(System.in);
        int difficulty ;
        do {
            System.out.println("Enter Difficulty level to begin game");
            System.out.println("1: Beginner – 9 * 9 Board and 10 Mines ");
            System.out.println("2: Intermediate – 16 * 16 Board and 40 Mines ");
            System.out.println("3: Advanced – 24 * 24 Board and 99 Mines ");
            difficulty = scan.nextInt();
        } while (!(difficulty == 1 || difficulty ==2 || difficulty ==3));

        switch (difficulty) {
            case 1: // Beginner – 9 * 9 Board and 10 Mines
                dimensions = 9;
                mines = 10;
                break;

            case 2: //  Intermediate – 16 * 16 Board and 40 Mines
                dimensions = 16;
                mines = 40;
                break;

            case 3: //   Advanced – 24 * 24 Board and 99 Mines
                dimensions = 24;
                mines = 99;
                board = new Mine[dimensions][dimensions];
                break;

            default:
                dimensions = 0;
                mines = 0;
                break;
        }
        board = new Mine[dimensions][dimensions];
        endGame = false;
    }
}
