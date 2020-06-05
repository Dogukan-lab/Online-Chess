package pieces;

import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class King extends Piece {

    public King(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x,y,isWhite,image, tileBoard);
    }

    @Override
    public boolean canMove(int x, int y) {
        Piece possiblePiece = board.getPiece(x, y);
        if (possiblePiece != null) {
            if (possiblePiece.isWhite && this.isWhite) {
                return false;
            }
            if (possiblePiece.isBlack() && this.isBlack()) {
                return false;
            }
        }


        return true;
    }

    @Override
    public boolean moveTo(int x, int y) {
        return true;
    }


}
