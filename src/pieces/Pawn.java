package pieces;

import data.Direction;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {
    private boolean firstMove;
    private Direction direction;

    public Pawn(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
        this.firstMove = true;

    }

    @Override
    public boolean canMove(int x, int y) {
        Piece possiblePiece = this.board.getPiece(x, y);
        if (possiblePiece != null) {
            if (possiblePiece.isWhite && this.isWhite) {
                return false;
            }
            if (possiblePiece.isBlack() && this.isBlack()) {
                return false;
            }
        }

//        if (Math.abs(x - this.getX()) == Math.abs(y - this.getY())) {
//            this.direction = Direction.DIAGONAL;
//        }
        if (y < this.getY()) {
            if(this.isBlack()){
                return false;
            }
            this.direction = Direction.UP;
        }
        if (y > this.getY()) {
            if (this.isWhite){
                return false;
            }
            this.direction = Direction.DOWN;
        }

        int spaces_to_move = 0;
        switch (this.direction) {
            case UP:
                if (this.firstMove) {
                    spaces_to_move = 2;
                    this.firstMove = false;
                } else {
                    spaces_to_move = Math.abs(y - this.getY());
                    if (spaces_to_move >= 2) {
                        return false;

                    } else {
                        for (int i = 1; i <= spaces_to_move; i++) {
                            Piece p = board.getPiece(this.getX(), this.getY() - i);
                            if (p != null) {
                                return false;
                            }
                        }
                    }
                }
                break;
            case DOWN:
                if (this.firstMove) {
                    spaces_to_move = 2;
                    this.firstMove = false;
                } else {
                    spaces_to_move = Math.abs(y - this.getY());
                    if (spaces_to_move >= 2) {
                        return false;

                    } else {
                        for (int i = 1; i <= spaces_to_move; i++) {
                            Piece p = board.getPiece(this.getX(), this.getY() + i);
                            if (p != null) {
                                return false;
                            }
                        }
                    }
                }
                break;
            case DIAGONAL:
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
