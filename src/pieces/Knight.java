package pieces;

import data.Direction;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Knight extends Piece {

    public Knight(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
    }

    @Override
    public boolean canMove(final int x, final int y) {
        Piece possiblePiece = board.getPiece(x, y);
        if (possiblePiece != null) {
            if (possiblePiece.isWhite && this.isWhite) {
                return false;
            }
            if (possiblePiece.isBlack() && this.isBlack()) {
                return false;
            }
        }

        if ((this.getX() - 2) == x && (this.getY() - 1) == y) {
            return true;
        } else if ((this.getX() - 2) == x && (this.getY() + 1) == y) {
            return true;
        } else if ((this.getY() - 2) == y && (this.getX() - 1) == x) {
            return true;
        } else if ((this.getY() - 2) == y && (this.getX() + 1) == x) {
            return true;
        } else if ((this.getX() + 2) == x && (this.getY() - 1) == y) {
            return true;
        } else if ((this.getX() + 2) == x && (this.getY() + 1) == y) {
            return true;
        } else if ((this.getY() + 2) == y && (this.getX() - 1) == x) {
            return true;
        } else if ((this.getY() + 2) == y && (this.getX() + 1) == x) {
            return true;
        } else {
            return false;
        }
}

    @Override
    public boolean moveTo(final int x, final int y) {
        if (canMove(x, y)) {
            this.setX(x);
            this.setY(y);
            return true;
        }
        return false;
    }


}
