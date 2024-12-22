package othello.cli;

import othello.Game;
public class OthelloCLI {

    public static void main(String[] args) {
        System.out.println("Welcome to Othello!");

        // Create an instance of the game with input and output handlers
        InputHandler inputHandler = new InputHandler();
        OutputHandler outputHandler = new OutputHandler();
        Game game = new Game(inputHandler, outputHandler);

    
     // Start the game loop
        while (!game.isGameOver()) {
            outputHandler.displayBoard(game.getBoard());

            // Get user input for the move
            //System.out.print("Enter your move (row and column): ");
            int[] move = inputHandler.getMoveInput();

            // Validate and make the move
            if (game.isValidMove(move[0], move[1])) {
                game.makeMove(move[0], move[1]);
                game.switchTurns();  // Switch to the other player

				
				
				 
            } else {
                outputHandler.displayErrorMessage("Invalid move. Try again.");
            }
        }

        // Display the final state and the winner
        outputHandler.displayBoard(game.getBoard());
        outputHandler.displayWinner(game);

        // Close the scanner
        inputHandler.closeScanner();
    }
}
