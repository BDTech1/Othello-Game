package othello.cli;
import othello.Board;
import othello.Move;
import othello.Player;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public Move makeMove(Board board) {
        synchronized (board) {
            List<Move> possibleMoves = board.getPossibleMoves(this);

            if (!possibleMoves.isEmpty()) {
                Random random = new Random();
                Move randomMove = possibleMoves.get(random.nextInt(possibleMoves.size()));
                return randomMove;
            }

            // Handle the case where there are no possible moves
            return null;
        }
    }
}
