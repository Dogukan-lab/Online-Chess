package pieces;

import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {


    public Bishop(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
    }

    @Override
    public boolean canMove(int x, int y) {
        return false;
    }

    @Override
    public void moveTo(int x, int y) {

    }


}
