package othello;

public abstract class Player {
    private PieceColor pieceColor;
   // private String color;

    public Player(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public PieceColor getColor() {
        return pieceColor;
    }
    
    public enum PlayerMode {
        HUMAN, COMPUTER
    }
    public abstract Move makeMove(Board board);

    

    //////////
   // public Player(String color) {
        //this.color = color;
   // }
    
    
    //////////
    
   // public String getColor1() {
        //return color;
    //}
    
    
    
    
    
    
    
    
    
    
    // Add other Player methods as needed











 
    // Additional methods as needed
    public enum PieceColor {
        BLACK, WHITE
    }



	

}

