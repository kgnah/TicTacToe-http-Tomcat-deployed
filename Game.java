public class Game {

    private Player p1;
    private Player p2;
    private Board board;
    private int turn;

    public Game(Player p1, Player p2, int start) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new Board();
        this.turn = start;
    }

    public void play() {

        // display hello, board and player's turn
        System.out.println("Hello!");
        board.display();
        System.out.println("Player#" + turn + "'s turn");

        while (true) {
            // choose current player
            Player current = (turn == 1) ? p1 : p2;

            try {
                current.makeMove(board);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                break;
            }

            // check quit
            if (current.hasQuit()) {
                break;
            }

            // display board
            board.display();

            // check win
            if (board.checkWin(turn)) {
                System.out.println("Player#" + turn + " won!");
                break;
            }

            // check draw
            if (board.isFull()) {
                System.out.println("It is a draw!");
                break;
            }

            // switch turn
            turn = (turn == 1) ? 2 : 1;
            System.out.println("Player#" + turn + "'s turn");
        }
    }
}