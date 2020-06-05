package pieces;

import data.Direction;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Bishop extends Piece {
    private Direction direction;

    public Bishop(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
        this.direction = Direction.INIT;
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

        if (Math.abs(this.getX() - x) == Math.abs(this.getY() - y)) {
            this.direction = Direction.DIAGONAL;
        } else {
            return false;
        }

        Piece p = null;
        int spaces_to_move = Math.abs(this.getY() - y);
        System.out.println("SPACES TO MOVE: " + spaces_to_move);
        if (x < this.getX() && y < this.getY()) {
            for (int i = 1; i < spaces_to_move; i++) {
                p = this.board.getPiece(this.getX() - i, this.getY() - i);

                if (p != null) {
                    return false;
                }
            }
        }
        if (x > this.getX() && y < this.getY()) {
            for (int i = 1; i < spaces_to_move; i++) {
                p = this.board.getPiece(this.getX() + i, this.getY() - i);

                if (p != null) {
                    return false;
                }
            }
        }
        if (x < this.getX() && y > this.getY()) {
            for (int i = 1; i < spaces_to_move; i++) {
                p = this.board.getPiece(this.getX() - i, this.getY() + i);

                if (p != null) {
                    return false;
                }
            }
        }
        if (x > this.getX() && y > this.getY()) {
            for (int i = 1; i < spaces_to_move; i++) {
                p = this.board.getPiece(this.getX() + i, this.getY() + i);

                if (p != null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean moveTo(int x, int y) {
        if (canMove(x, y)) {
            this.setX(x);
            this.setY(y);
            return true;
        }
        return false;
    }


}
