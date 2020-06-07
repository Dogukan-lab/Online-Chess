package pieces;

import data.Direction;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Knight extends Piece {
    private Direction direction;

    public Knight(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
        this.direction = Direction.INIT;
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
            this.direction = Direction.LEFT_UP;
        } else if ((this.getX() - 2) == x && (this.getY() + 1) == y) {
            this.direction = Direction.LEFT_DOWN;
        } else if ((this.getY() - 2) == y && (this.getX() - 1) == x) {
            this.direction = Direction.UP_LEFT;
        } else if ((this.getY() - 2) == y && (this.getX() + 1) == x) {
            this.direction = Direction.UP_RIGHT;
        } else if ((this.getX() + 2) == x && (this.getY() - 1) == y) {
            this.direction = Direction.RIGHT_UP;
        } else if ((this.getX() + 2) == x && (this.getY() + 1) == y) {
            this.direction = Direction.RIGHT_DOWN;
        } else if ((this.getY() + 2) == y && (this.getX() - 1) == x) {
            this.direction = Direction.DOWN_LEFT;
        } else if ((this.getY() + 2) == y && (this.getX() + 1) == x) {
            this.direction = Direction.DOWN_RIGHT;
        } else {
            return false;
        }


    Piece piece = null;
        switch(this.direction)

    {
        case LEFT_UP:
            piece = this.board.getPiece(this.getX() - 2, this.getY() - 1);
            if (piece != null) {
                return false;
            }
            break;
        case LEFT_DOWN:
            piece = this.board.getPiece(this.getX() - 2, this.getY() + 1);
            if (piece != null) {
                return false;
            }
            break;
        case UP_LEFT:
            piece = this.board.getPiece(this.getX() - 1, this.getY() - 2);
            if (piece != null) {
                return false;
            }
            break;
        case UP_RIGHT:
            piece = this.board.getPiece(this.getX() + 1, this.getY() - 2);
            if (piece != null) {
                return false;
            }
            break;
        case RIGHT_UP:
            piece = this.board.getPiece(this.getX() + 2, this.getY() - 1);
            if (piece != null) {
                return false;
            }
            break;
        case RIGHT_DOWN:
            piece = this.board.getPiece(this.getX() + 2, this.getY() + 1);
            if (piece != null) {
                return false;
            }
            break;
        case DOWN_RIGHT:
            piece = this.board.getPiece(this.getX() + 1, this.getY() + 2);
            if (piece != null) {
                return false;
            }
            break;
        case DOWN_LEFT:
            piece = this.board.getPiece(this.getX() - 1, this.getY() + 2);
            if (piece != null) {
                return false;
            }
            break;
    }

        return true;
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
