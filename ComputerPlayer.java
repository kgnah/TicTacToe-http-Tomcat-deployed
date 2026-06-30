public class ComputerPlayer extends Player {

    public ComputerPlayer(int mark) {
        super(mark);
    }

    public int chooseMove(Board board){
        for (int i = 0; i < 9; i++){
            if (board.isFree(i)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void makeMove(Board board) {
        int move = chooseMove(board);

        if (move != -1){
            board.setCell(move, mark);
        }
    }
}