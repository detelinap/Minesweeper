import java.util.Scanner;


public class BoardService {

    private Board board;

    private void initialFill() { //initial fill of the board before the initial selection
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                board.getBoard()[i][j] = new Mine(false, false);
            }
        }
    }

    private void fillBoard(int a, int b) { //fills the board with mines
        int minesCopy = Integer.valueOf(board.getMines());
        while (minesCopy != 0) {
            int randRow = (int) (Math.random() * board.getDimensions());
            int randCol = (int) (Math.random() * board.getDimensions());
            if (randRow != a && randCol != b) {
                board.getBoard()[randRow][randCol].setMine(true);
                minesCopy--;
            }
        }

    }

    private void makeInitialSelection(int a, int b) { //makes the initial selection to ensure the first selection is not a mine
        board.getBoard()[a][b].setMine(false);
        board.getBoard()[a][b].setSelected(true);
        board.getBoard()[a][b].setSymbol(Integer.toString(findMines(a, b)));
        fillBoard(a, b);
        selectPoint(a, b);
    }

    private void showBoard() { //visualizes the board
        System.out.print("   ");
        for (int i = 0; i < board.getDimensions(); i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < board.getDimensions(); i++) {
            System.out.print(i + " ");
            if (i < 10) {
                System.out.print(" ");
            }
            for (int j = 0; j < board.getDimensions(); j++) {
                if (!board.getBoard()[i][j].isSelected()) {
                    System.out.print("- ");
                    if (j > 9) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(board.getBoard()[i][j].getSymbol() + " ");
                    if (j > 9) {
                        System.out.print(" ");
                    }
                    //   System.out.print(board.getBoard()[i][j].getSymbol() + " ");
                }

            }
            System.out.println();
        }
    }

    private int findMines(int a, int b) { //finds the number of mines in coordinates surrounding (a,b) and returns their number
        int foundMines = 0;
        for (int n = -1; n < 2; n++) {
            for (int m = -1; m < 2; m++) {
                if (!isOutOfBound(a, m) && !isOutOfBound(b, n)) {
                    if (board.getBoard()[a + m][b + n].isMine()) {
                        foundMines++;
                    }
                }
            }
        }
        return foundMines;
    }

    private boolean isOutOfBound(int a, int b) { //checks if the coordinates are out of array bounds
        return (a + b >= board.getDimensions() || a + b < 0);
    }

    private boolean checkIfMine(int a, int b) {
        if (board.getBoard()[a][b].isMine() == false) {
            return false;
        } else {
            endGame();
            return true;
        }
    }

    private void showMines() { //shows all mines on the board after the game has ended and the player has lost
        for (int i = 0; i < board.getDimensions(); i++) {
            for (int j = 0; j < board.getDimensions(); j++) {
                if (!board.getBoard()[i][j].isSelected() && board.getBoard()[i][j].isMine()) {
                    board.getBoard()[i][j].setSelected(true);
                }
            }
        }

    }

    private void ifWon() { // ends the game only if the player has won
        if (board.getSelectedBoxes() == ((board.getDimensions() * board.getDimensions()) - board.getMines())) {
            endGame();
        }
    }

    private void selectPoint(int a, int b) { //allows the player to select coordinates
        board.getBoard()[a][b].setSelected(true);
        board.setSelectedBoxes(1);
        if (checkIfMine(a, b)) {
            return;
        }
        int s = findMines(a, b);
        board.getBoard()[a][b].setSymbol(Integer.toString(s));
        if (s == 0) {
            for (int n = -1; n < 2; n++) {
                for (int m = -1; m < 2; m++) {
                    if (!(isOutOfBound(a, m) || isOutOfBound(b, n))) {
                        if (!board.getBoard()[a + m][b + n].isSelected()) {
                            selectPoint(a + m, b + n);
                        }
                    }
                }
            }
        }
    }

    private void endGame() {
        if (board.getSelectedBoxes() != ((board.getDimensions() * board.getDimensions()) - board.getMines())) {
            showMines();
            System.out.println("You have selected a mine. Game over");
        } else {
            System.out.println("Congradulations, you have won!");
        }
        board.setEndGame(true);
    }

    public void startGame() {
        board = new Board();
        initialFill();
        showBoard();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the coordinates of your first selection");
        int a = scan.nextInt();
        int b = scan.nextInt();
        makeInitialSelection(a, b);
        showBoard();
        do {
            System.out.println("Please enter the coordinates of your next selection");
            int c = scan.nextInt();
            int d = scan.nextInt();
            if (isSelected(c, d)) {
                System.out.println("This point has already been selected. Please try again");
                continue;
            }
            selectPoint(c, d);
            showBoard();
            ifWon();
        } while (!board.isEndGame());
    }

    private boolean isSelected(int c, int d) {
        if (!(isOutOfBound(c, 0) || isOutOfBound(d, 0))) {
            if (board.getBoard()[c][d].isSelected()) {
                return true;
            }
        }
        return false;
    }
}
