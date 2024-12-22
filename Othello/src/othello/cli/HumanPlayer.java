package othello.cli;
import othello.Board;
import othello.Move;
import othello.Player;
import java.util.List;

public class HumanPlayer extends Player {

    private InputHandler inputHandler;

    public HumanPlayer(PieceColor pieceColor, InputHandler inputHandler) {
        super(pieceColor);
        this.inputHandler = inputHandler;
    }

    @Override
    public Move makeMove(Board board) {
        List<Move> possibleMoves = board.getPossibleMoves(this);

        if (!possibleMoves.isEmpty()) {
            // Check if the player is human
            if (getClass() == HumanPlayer.class) {
                // Use a separate thread for handling human moves
                Thread moveThread = new Thread(() -> {
                    int[] move = inputHandler.getMoveInput();
                    if (board.isValidMove(move[0], move[1], this)) {
                        board.placePiece(move[0], move[1], this);
                    } else {
                        System.out.println("Invalid move. Try again.");
                        makeMove(board);
                    }
                });

                moveThread.start();
            }
        }

        return null; // or return a Move object
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}