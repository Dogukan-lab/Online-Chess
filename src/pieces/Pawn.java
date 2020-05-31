package pieces;

import logic.board.TileBoard;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {


    public Pawn(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
    }

    @Override
    public boolean canMove(int x, int y) {
        return true;
    }

    @Override
    public void moveTo(int x, int y) {
        if(canMove(x,y)){
            super.setX(x);
            super.setY(y);
        }

    }

}
