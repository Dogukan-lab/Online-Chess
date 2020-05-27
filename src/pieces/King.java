package pieces;

import logic.TileBoard;

public class King extends Piece {


    public King(int x, int y, boolean isWhite, String file_path, TileBoard tileBoard) {
        super(x, y, isWhite, file_path, tileBoard);
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
