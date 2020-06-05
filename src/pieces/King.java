package pieces;

import data.Direction;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class King extends Piece {
    private Direction direction;

    public King(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
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
            if (this.getX() != x && this.getY() != y) {
                return false;
            }
            if (y > this.getY()) {
                this.direction = Direction.DOWN;
            }
            if (y < this.getY()) {
                this.direction = Direction.UP;
            }
            if (x > this.getX()) {
                this.direction = Direction.RIGHT;
            }
            if (x < this.getX()) {
                this.direction = Direction.LEFT;
            }
        }

        int spaces_to_move = 1;
        Piece piece = null;

        switch (this.direction) {
            case UP:
                piece = board.getPiece(this.getX(), this.getY() - spaces_to_move);
                if (piece != null) {
                    return false;
                }

                break;

            case DOWN:
                piece = board.getPiece(this.getX(), this.getY() + spaces_to_move);
                if (piece != null) {
                    return false;
                }
                break;

            case LEFT:
                piece = board.getPiece(this.getX() - spaces_to_move, this.getY());
                if (piece != null) {
                    return false;
                }
                break;

            case RIGHT:
                piece = board.getPiece(this.getX() + spaces_to_move, this.getY());
                if (piece != null) {
                    return false;
                }
                break;

            case DIAGONAL:
                if (x < this.getX() && y < this.getY()) {
                    piece = board.getPiece(this.getX() - spaces_to_move, this.getY() - spaces_to_move);
                    if (piece != null) {
                        return false;
                    }
                }
                if (x > this.getX() && y < this.getY()) {
                    piece = board.getPiece(this.getX() + spaces_to_move, this.getY() - spaces_to_move);
                    if (piece != null) {
                        return false;
                    }

                }
                if (x < this.getX() && y > this.getY()) {
                    piece = board.getPiece(this.getX() - spaces_to_move, this.getY() + spaces_to_move);
                    if (piece != null) {
                        return false;
                    }

                }
                if (x > this.getX() && y > this.getY()) {
                    piece = board.getPiece(this.getX() + spaces_to_move, this.getY() + spaces_to_move);
                    if (piece != null) {
                        return false;
                    }

                }
                break;
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
