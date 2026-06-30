import java.io.*;

public class HumanPlayer extends Player {
    private BufferedReader reader;

    public HumanPlayer(int mark) {
        super(mark);
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private int lastMove;

    public int getLastMove() {
        return lastMove;
    }

    @Override
    public void makeMove(Board board) {
        while (true) {
            try {
                // read input from terminal
                String input = reader.readLine();

                // input "q" = quit the game
                if (input.equals("q")) {
                    System.out.println("End of the game");
                    quit = true;
                    return;
                }

                // convert move to integer
                int move = Integer.parseInt(input);

                // check range 1 - 9
                if (move < 1 || move > 9) {
                    System.out.println("Please, input a valid number [1-9]");
                    System.out.println("Player#1's turn");
                    continue;
                }

                // check occupied cell
                if (!board.isFree(move - 1)) {
                    System.out.println("The cell is occupied!");
                    System.out.println("Player#1's turn");
                    continue;
                }

                // place mark on board
                lastMove = move - 1;
                board.setCell(move - 1, mark);
                break;

            } catch (Exception e) {
                System.out.println("Please, input a valid number [1-9]");
                System.out.println("Player#1's turn");
            }
        }
    }
}