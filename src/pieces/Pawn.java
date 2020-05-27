package pieces;

import logic.TileBoard;

public class Pawn extends Piece {


    public Pawn(int x, int y, boolean isWhite, TileBoard tileBoard) {
        super(x, y, isWhite, tileBoard);
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public void moveTo(int x, int y) {

    }
}
