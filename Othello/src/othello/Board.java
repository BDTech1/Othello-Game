package othello;

import java.util.ArrayList;
import java.util.List;

//import java.awt.Color;

import othello.Player.PieceColor;
import othello.cli.ComputerPlayer;
import othello.cli.HumanPlayer;
import othello.cli.InputHandler;

public class Board {

	private Square[][] squares;
	
    public Board(InputHandler inputHandler) {
        squares = new Square [8][8];
    	initializeBoard();
    }

    private void initializeBoard() {
    	InputHandler InputHandler = new InputHandler();
        // Initialize all squares and set initial four pieces
    	  for (int i = 0; i < 8; i++) {
              for (int j = 0; j < 8; j++) {
                  squares[i][j] = new Square();
              }
          }
    	 
    	  // Set initial four pieces in the center
    	    int center = 4; // index for the center of the board
    	    squares[center][center].placePiece(new HumanPlayer(PieceColor.BLACK, InputHandler));
    	    
    	    squares[center + 1][center + 1].placePiece(new HumanPlayer(PieceColor.BLACK, InputHandler));
    	    
    	    squares[center][center + 1].placePiece(new ComputerPlayer(PieceColor.WHITE));
    	    
    	    squares[center + 1][center].placePiece(new ComputerPlayer(PieceColor.WHITE));	  
    	  
    }
    ////
   
    
    
    
    
    
    
    //

    public boolean isValidMove(int x, int y, Player player) {
    	// Check if the specified coordinates are within the board boundaries
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }
        // Check if the selected square is empty
        if (squares[x][y].isOccupied()) {
            return false;
        }
        
        // Check if there is at least one opponent's piece that can be flipped
        if (!canFlipOpponentPieces(x, y, player)) {
            return false;
        }
        
       
        return true;
    }
    ////////////
    private boolean canFlipOpponentPieces(int x, int y, Player player) {
        // The directions to check (horizontal, vertical, diagonal)
        int[][] directions = {
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}, // horizontal and vertical
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // diagonal
        };

        // Check each direction
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];

            // Initialize variables for the next square
            int nextX = x + dx;
            int nextY = y + dy;

            // I Initialize flag to track if at least one opponent's piece can be flipped in this direction
            boolean flipPossible = false;

            // Move in the current direction until the edge of the board is reached
            while (nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {
                // If the next square is empty, break the loop for this direction
                if (!squares[nextX][nextY].isOccupied()) {
                    break;
                }

                // If the next square is occupied by the current player's piece, no flip is possible in this direction
                if (squares[nextX][nextY].getOwner().getColor() == player.getColor()) {
                    break;
                }

                // If the next square is occupied by the opponent's piece, set the flipPossible flag
                if (squares[nextX][nextY].getOwner().getColor() != player.getColor()) {
                    flipPossible = true;
                }

                // Move to the next square in the current direction
                nextX += dx;
                nextY += dy;
            }

            // If at least one opponent's piece can be flipped in any direction, return true
            if (flipPossible) {
                return true;
            }
        }

        // If no opponent's piece can be flipped in any direction and  return false
        return false;
    }

    
    
    
    
    
    
    
    
    
    /////

   public void placePiece(int x, int y, Player player) {
        // Place a piece on the board
	   squares[x][y].placePiece(player);
    }
    
    ////////////////here I implemened the code to display the board.

   public void displayBoard() {
	    System.out.println("  0 1 2 3 4 5 6 7"); // Column indices
	    System.out.println(" ****************");

	    for (int i = 0; i < 8; i++) {
	        System.out.print(i + "|"); // Row index
	        for (int j = 0; j < 8; j++) {
	            if (squares[i][j].isOccupied()) {
	                Player owner = squares[i][j].getOwner();
	                char pieceSymbol = (owner.getColor() == PieceColor.BLACK) ? 'B' : 'W';
	                System.out.print(pieceSymbol + "|");
	            } else {
	                System.out.print(" |");
	            }
	        }
	        System.out.println("\n ****************");
	    }
	}
////////////////////////////////////////////
   public Player getSquareOwner(int x, int y) {
       if (isValidCoordinate(x, y)) {
           return squares[x][y].getOwner();
       } else {
           // Handle invalid coordinates, for example, return null
           return null;
       }
   }
//////////////////////////////////////////////////////////
   private boolean isValidCoordinate(int x, int y) {
       return x >= 0 && x < 8 && y >= 0 && y < 8;
   }



    //here is the method I implemented for checking possible moves and I created simple class called move to implement 
   //to check the possible moves based on the row and column
   public List<Move> getPossibleMoves(Player player) {
       List<Move> possibleMoves = new ArrayList<>();

       for (int i = 0; i < 8; i++) {
           for (int j = 0; j < 8; j++) {
               if (isValidMove(i, j, player)) {
                   possibleMoves.add(new Move(i, j));
               }
           }
       }

       return possibleMoves;
   }

/////////////////////////////////////
   
   public boolean isBoardFull() {
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            if (!squares[i][j].isOccupied()) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

   

}


