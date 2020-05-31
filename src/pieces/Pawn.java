package pieces;

import logic.TileBoard;
import org.jfree.fx.FXGraphics2D;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {


    public Pawn(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
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
