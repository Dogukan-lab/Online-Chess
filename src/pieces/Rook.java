package pieces;

import data.Direction;
import logic.board.TileBoard;

import java.awt.image.BufferedImage;

public class Rook extends Piece {
    private Direction direction;

    public Rook(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
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
        if (x < this.getY()) {
            this.direction = Direction.LEFT;
        }

        if (this.direction.equals(Direction.DOWN)) {
            int spaces_to_move = Math.abs(y - this.getY());
            for (int i = 1; i < spaces_to_move; i++) {
                Piece p = board.getPiece(this.getX(), this.getY() + i);
                if (p != null) {
                    return false;
                }
            }
        }
        if (this.direction.equals(Direction.UP)) {
            int spaces_to_move = Math.abs(y - this.getY());
            for (int i = 1; i < spaces_to_move; i++) {
                Piece p = this.board.getPiece(this.getX(), this.getY() - i);
                if(p != null){
                    return false;
                }
            }
        }
        if (this.direction.equals(Direction.LEFT)) {
            int spaces_to_move = Math.abs(x - this.getX());
            for (int i = 1; i < spaces_to_move; i++) {
                Piece p = this.board.getPiece(this.getX() - i, this.getY());
                if(p != null){
                    return false;
                }
            }
        }
        if (this.direction.equals(Direction.RIGHT)) {
            int spaces_to_move = Math.abs(x - this.getX());
            for (int i = 1; i < spaces_to_move; i++) {
                Piece p = this.board.getPiece(this.getX() + i, this.getY());
                if(p != null){
                    return false;
                }
            }
        }

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
