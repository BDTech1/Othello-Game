package othello;

import othello.cli.ComputerPlayer;
import java.util.List;

import othello.cli.InputHandler;
import othello.cli.OutputHandler;

public class Game {

    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private OutputHandler outputHandler;

    public Game(InputHandler inputHandler, OutputHandler outputHandler) {
        this.board = new Board(inputHandler);
        this.players = new Player[2];
        this.players[0] = new ComputerPlayer(Player.PieceColor.BLACK);
        this.players[1] = new ComputerPlayer(Player.PieceColor.WHITE);
        this.currentPlayerIndex = 0;
        this.outputHandler = outputHandler;
    }

    
 // Start the game loop
    public void start() {
        while (!isGameOver()) {
            System.out.println("Current player: " + players[currentPlayerIndex].getClass().getSimpleName());
            outputHandler.displayBoard(board);
            players[currentPlayerIndex].makeMove(board);
            
            
            try {
                Thread.sleep(5000);  // 5 seconds delay
            } catch (InterruptedException e) {
               e.printStackTrace();
            }

            switchTurns();
        }

        outputHandler.displayBoard(board);
        outputHandler.displayWinner(this);
    }

/////////////
    public boolean isGameOver() {
        return board.isBoardFull() || !canAnyPlayerMakeMove();
    }
/////////////////
    private boolean canAnyPlayerMakeMove() {
        for (Player player : players) {
            List<Move> possibleMoves = board.getPossibleMoves(player);
            if (!possibleMoves.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void switchTurns() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
    }

    public Player getWinner() {
        int blackCount = countPieces(Player.PieceColor.BLACK);
        int whiteCount = countPieces(Player.PieceColor.WHITE);

        if (blackCount > whiteCount) {
            return players[0];
        } else if (blackCount < whiteCount) {
            return players[1];
        } else {
            return null; // It's a draw
        }
    }

    private int countPieces(Player.PieceColor color) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getSquareOwner(i, j) != null && board.getSquareOwner(i, j).getColor() == color) {
                    count++;
                }
            }
        }
        return count;
    }
    
    //////
    public Board getBoard() {
        return board;
    }
//////////
    public boolean isValidMove(int row, int col) {
        return board.isValidMove(row, col, players[currentPlayerIndex]);
    }
////////////
    public void makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board.placePiece(row, col, players[currentPlayerIndex]);
            outputHandler.displayBoard(board); 
            switchTurns();
        } else {
            outputHandler.displayErrorMessage("Invalid move. Try again.");
        }
    }
/////////
}
   
   
 

