package othello.cli;

import othello.Board;
import othello.Game;
import othello.Player;

public class OutputHandler {

    public void displayBoard(Board board) {
        System.out.println("   0 1 2 3 4 5 6 7");
        System.out.println(" ****************");

        for (int i = 0; i < 8; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < 8; j++) {
                Player owner = board.getSquareOwner(i, j);
                char piece = (owner != null) ? owner.getColor().toString().charAt(0) : ' ';
                System.out.print(piece + " ");
            }
            System.out.println("|");
        }

        System.out.println(" ****************");
    }

    public void displayWinner(Game game) {
        System.out.println("Game Over. Winner: " + game.getWinner());
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}