import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String boardString = request.getParameter("board");

        Board board = BoardProtocol.decode(boardString);

        ComputerPlayer computer = new ComputerPlayer(2);

        int move = computer.chooseMove(board);

        if (move != -1) {
            board.setCell(move, 2);
        }

        String updatedBoard = BoardProtocol.encode(board);

        String json =
                "{\"move\":" + move +
                        ",\"board\":\"" + updatedBoard + "\"}";

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}