package othello;


public class Square {
    private Player player;


    public Square() {
        this.player = null;
    }

    public boolean isOccupied() {
        return player != null;
    }

    public void setOwner(Player player) {
        this.player = player;
    }

    public Player getOwner() {
        return player;
    }

    public void placePiece(Player player) {
        this.player = player;
    }


    
 
}
