package logic;

public class Player {

    private boolean isWhite;
    private boolean isTurn;
    private boolean moved_a_piece;


    public Player(boolean isWhite) {
        this.isWhite = isWhite;
        this.isTurn = false;
        this.moved_a_piece = false;
    }


    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public boolean isMoved_a_piece() {
        return moved_a_piece;
    }

    public void setMoved_a_piece(boolean moved_a_piece) {
        this.moved_a_piece = moved_a_piece;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
}
