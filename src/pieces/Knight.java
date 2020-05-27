package pieces;

import logic.TileBoard;

public class Knight extends Piece {


    public Knight(int x, int y, boolean isWhite, TileBoard tileBoard) {
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
