package pieces;

import data.Direction;
import logic.experiment.Tile;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Queen extends Piece {
    private Direction direction;

    public Queen(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
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

        int spaces_to_move = 0;
        Piece p = null;
        Tile tile = null;
        Tile currentTile = null;
        switch (this.direction) {
            case UP:
                if (this.isWhite()) {
                    spaces_to_move = Math.abs(y - this.getY());
                    for (int i = 1; i < spaces_to_move; i++) {
                        p = this.board.getPiece(this.getX(), this.getY() - i);
                        if (p != null && this.isWhite()) {
                            return false;
                        }
                    }
                }
                if (this.isBlack()) {
                    spaces_to_move = Math.abs(y - this.getY());
                    for (int i = 1; i < spaces_to_move; i++) {
                        p = this.board.getPiece(this.getX(), this.getY() - i);
                        if (p != null && this.isBlack()) {
                            return false;
                        }
                    }
                }
                break;
            case DOWN:
                spaces_to_move = Math.abs(y - this.getY());
                for (int i = 1; i < spaces_to_move; i++) {
                    p = board.getPiece(this.getX(), this.getY() + i);
                    if (p != null) {
                        return false;
                    }
                }
                break;
            case RIGHT:
                spaces_to_move = Math.abs(x - this.getX());
                for (int i = 1; i < spaces_to_move; i++) {
                    p = this.board.getPiece(this.getX() + i, this.getY());
                    if (p != null) {
                        return false;
                    }
                }
                break;
            case LEFT:
                spaces_to_move = Math.abs(x - this.getX());
                for (int i = 1; i < spaces_to_move; i++) {
                    p = this.board.getPiece(this.getX() - i, this.getY());
                    if (p != null) {
                        return false;
                    }
                }
                break;
            case DIAGONAL:
                spaces_to_move = Math.abs(this.getY() - y);
                System.out.println("SPACES TO MOVE: " + spaces_to_move);
                if(x < this.getX() && y < this.getY()){
                    for (int i = 1; i < spaces_to_move; i++) {
                        p = this.board.getPiece(this.getX() - i, this.getY() - i);

                        if (p != null) {
                            return false;
                        }
                    }
                }
                if(x > this.getX() && y < this.getY()){
                    for (int i = 1; i < spaces_to_move; i++) {
                        p = this.board.getPiece(this.getX() + i, this.getY() - i);

                        if (p != null) {
                            return false;
                        }
                    }
                }
                if (x < this.getX() && y > this.getY()){
                    for (int i = 1; i < spaces_to_move; i++) {
                        p = this.board.getPiece(this.getX() - i, this.getY() + i);

                        if (p != null) {
                            return false;
                        }
                    }
                }
                if (x > this.getX() && y > this.getY()){
                    for (int i = 1; i < spaces_to_move; i++) {
                        p = this.board.getPiece(this.getX() + i, this.getY() + i);

                        if (p != null) {
                            return false;
                        }
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
