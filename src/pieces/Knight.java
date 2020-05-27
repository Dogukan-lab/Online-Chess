package pieces;

import logic.TileBoard;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(int x, int y, boolean isWhite, String file_path, TileBoard tileBoard) {
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
