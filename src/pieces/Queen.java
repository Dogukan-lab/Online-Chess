package pieces;

import logic.TileBoard;

public class Queen extends Piece {


    public Queen(int x, int y, boolean isWhite, TileBoard tileBoard) {
        super(x, y, isWhite, tileBoard);
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public void moveTo(int x, int y) {

    }

    @Override
    public void draw() {

    }
}
