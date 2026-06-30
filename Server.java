import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        server.createContext("/move", new MoveHandler());

        server.setExecutor(null);

        System.out.println("HTTP Server started");

        server.start();
    }

    static class MoveHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String query = exchange.getRequestURI().getQuery();

            String boardString = query.substring(6);

            Board board = BoardProtocol.decode(boardString);

            ComputerPlayer computer = new ComputerPlayer(2);

            int move = computer.chooseMove(board);

            if (move != -1) {
                board.setCell(move, 2);
            }

            String updatedBoard = BoardProtocol.encode(board);

            String response = "{\"move\":" + move + ",\"board\":\"" + updatedBoard + "\"}";

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");

            exchange.sendResponseHeaders(200, response.getBytes().length);

            try (OutputStream output = exchange.getResponseBody()) {

                output.write(response.getBytes());
            }
        }
    }
}
