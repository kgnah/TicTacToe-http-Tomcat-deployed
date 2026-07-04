import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // extracts board from URL
        String boardString = request.getParameter("board");

        // validates the request before decoding
        if (boardString == null || boardString.length() != 9 || !boardString.matches("[012]+")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Invalid board\"}");
            return;
        }

        // board decoding
        Board board = BoardProtocol.decode(boardString);

        // computer player
        ComputerPlayer computer = new ComputerPlayer(2);

        int move = computer.chooseMove(board);

        // sets the computer move to the board
        if (move != -1) {
            board.setCell(move, 2);
        }

        // encodes the updated board
        String updatedBoard = BoardProtocol.encode(board);

        // creates JSON response
        String json = "{\"move\":" + move + ",\"board\":\"" + updatedBoard + "\"}";

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
