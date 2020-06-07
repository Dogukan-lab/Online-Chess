package pieces;

import data.Direction;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {

    private boolean firstMove = true;
    private Direction direction;

    public Pawn(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
    }

    @Override
    public boolean canMove(int x, int y) {

        int differenceX = Math.abs(x - this.x);
        int differenceY = Math.abs(y - this.y);

        int spaces_to_move = 1;

        if (differenceX > 2 || differenceY > 2) {
            return false;
        }

        if ((!this.firstMove) && (differenceX > 1 || differenceY > 1)) {
            return false;
        }

        Piece possiblePiece = board.getPiece(x, y);
        if (possiblePiece != null) {
            if (possiblePiece.isWhite && this.isWhite) {
                return false;
            }
            if (possiblePiece.isBlack() && this.isBlack()) {
                return false;
            }
        }


        if (y > this.getY()) {
            if (this.isWhite) {
                return false;
            } else {
                this.direction = Direction.DOWN;
            }

        }
        if (y < this.getY()) {
            if (this.isBlack()) {
                return false;
            } else {
                this.direction = Direction.UP;
            }

        }
        if (x > this.getX()) {
            if (!(differenceX == 1 && differenceY == 1)) {
                return false;
            } else {
                if (this.isWhite) {
                    if (this.y - y < 0 || board.getPiece(this.getX() + 1, this.getY() - 1) == null) {
                        return false;
                    } else {
                        direction = Direction.DIAGONAL_RIGHT;
                    }
                }
                if (isBlack()) {
                    if (this.y - y > 0 || board.getPiece(this.getX() + 1, this.getY() + 1) == null) {
                        return false;
                    } else {
                        direction = Direction.DIAGONAL_RIGHT;
                    }
                }


            }

        }
        if (x < this.getX()) {
            if (!(differenceX == 1 && differenceY == 1)) {
                return false;
            } else {
                if (isWhite) {
                    if (this.y - y < 0 || board.getPiece(this.getX() - 1, this.getY() - 1) == null) {
                        return false;
                    } else {
                        direction = Direction.DIAGONAL_LEFT;
                    }
                }
                if (this.isBlack()) {
                    if (this.y - y > 0 || board.getPiece(this.getX() - 1, this.getY() + 1) == null) {
                        return false;
                    } else {
                        direction = Direction.DIAGONAL_LEFT;
                    }
                }
            }


        }

        if (this.firstMove) {
            spaces_to_move = 2;
            this.firstMove = false;
        }

        switch (direction) {
            case UP:
                for (int i = 1; i <= spaces_to_move; i++) {
                    Piece p = board.getPiece(this.getX(), this.getY() - i);
                    if (p != null) {
                        return false;
                    }
                }
                break;

            case DOWN:
                for (int i = 1; i <= spaces_to_move; i++) {
                    Piece p = board.getPiece(this.getX(), this.getY() + i);
                    if (p != null) {
                        return false;
                    }
                }
                break;

            case DIAGONAL_LEFT:
                if (this.isWhite) {
                    Piece possiblePieceDiagonal = this.board.getPiece(this.x - 1, this.y - 1);
                    if (possiblePieceDiagonal != null) {
                        if (possiblePieceDiagonal.isWhite) {
                            return false;
                        }
                    }
                } else {
                    Piece possiblePieceDiagonal = this.board.getPiece(this.x - 1, this.y + 1);
                    if (possiblePieceDiagonal != null) {
                        if (possiblePieceDiagonal.isBlack()) {
                            return false;
                        }
                    }
                }

                break;

            case DIAGONAL_RIGHT:
                if (this.isWhite) {
                    Piece possiblePieceDiagonal = this.board.getPiece(this.x + 1, this.y - 1);
                    if (possiblePieceDiagonal != null) {
                        if (possiblePieceDiagonal.isWhite) {
                            return false;
                        }
                    }
                } else {
                    Piece possiblePieceDiagonal = this.board.getPiece(this.x + 1, this.y + 1);
                    if (possiblePieceDiagonal != null) {
                        if (possiblePieceDiagonal.isBlack()) {
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
            super.setX(x);
            super.setY(y);
            return true;
        }

        return false;
    }

}
