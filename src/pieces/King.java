package pieces;

import data.Direction;
import logic.experiment.Tile;
import logic.experiment.TileBoard;
import sun.rmi.runtime.Log;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class King extends Piece {
    private Direction direction;

    public King(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
        this.direction = Direction.INIT;
    }

    @Override
    public boolean canMove(int x, int y) {

        int differenceX = Math.abs(x - this.x);
        int differenceY = Math.abs(y - this.y);

        if (differenceX > 1 || differenceY > 1) {
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
        Piece piece;
        switch (this.direction) {
            case UP:
//                spaces_to_move = Math.abs(y - this.getY());
                if (this.isWhite()) {
                        piece = board.getPiece(this.getX(), this.getY() - spaces_to_move);
                        if (piece != null && piece.isWhite()) {
                            System.out.println("Ik kan niet het speelstuk eten");
                            return false;
                    }
                } else {
                        piece = board.getPiece(this.getX(), this.getY() - spaces_to_move);
                        if (piece != null && piece.isBlack()) {
                            System.out.println("Ik kan niet het speelstuk eten");
                            return false;
                    }
                }

                break;
            case DOWN:
//                spaces_to_move = Math.abs(y - this.getY());
                if (this.isWhite()) {
                        piece = board.getPiece(this.getX(), this.getY() + spaces_to_move);
                        if (piece != null && piece.isWhite()) {
                            System.out.println("Checking for white");
                            System.out.println("Ik kan niet het speelstuk eten");
                            return false;
                        }
                } else {

                    piece = board.getPiece(this.getX(), this.getY() + spaces_to_move);
                    if (piece != null && piece.isBlack()) {
                        System.out.println("Checking for Black");
                        return false;
                    }
                }
                break;
            case LEFT:
//                spaces_to_move = Math.abs(x - this.getX());
                if (this.isWhite()) {
                    piece = board.getPiece(this.getX() - spaces_to_move, this.getY());
                    if (piece != null && piece.isWhite()) {
                        return false;
                    }

                } else {

                    piece = board.getPiece(this.getX() - spaces_to_move, this.getY());
                    if (piece != null && piece.isBlack()) {
                        return false;

                    }
                }
                break;
            case RIGHT:
//                spaces_to_move = Math.abs(x - this.getX());
                if (this.isWhite()) {
                    piece = board.getPiece(this.getX() + spaces_to_move, this.getY());
                    if (piece != null && piece.isWhite()) {
                        return false;

                    }
                } else {

                    piece = board.getPiece(this.getX() + spaces_to_move, this.getY());
                    if (piece != null && piece.isBlack()) {
                        return false;

                    }
                }

                break;
            case DIAGONAL:
//                spaces_to_move = Math.abs(y - this.getY());
                if (this.isWhite()) {
                    if (x < this.getX() && y < this.getY()) {
                        piece = board.getPiece(this.getX() - spaces_to_move, this.getY() - spaces_to_move);
                        if (piece != null && piece.isWhite()) {
                            return false;
                        }
                    }
                    if (x > this.getX() && y < this.getY()) {
                        piece = board.getPiece(this.getX() + spaces_to_move, this.getY() - spaces_to_move);
                        if (piece != null && piece.isWhite()) {
                            return false;
                        }
                    }
                    if (x < this.getX() && y > this.getY()) {
                        piece = board.getPiece(this.getX() - spaces_to_move, this.getY() + spaces_to_move);
                        if (piece != null && piece.isWhite()) {
                            return false;
                        }
                    }
                        if (x > this.getX() && y > this.getY()) {
                            piece = board.getPiece(this.getX() + spaces_to_move, this.getY() + spaces_to_move);
                            if (piece != null && piece.isWhite()) {
                                return false;
                            }
                        }
                } else {

                    if (x < this.getX() && y < this.getY()) {
                        piece = board.getPiece(this.getX() - spaces_to_move, this.getY() - spaces_to_move);
                        if (piece != null && piece.isBlack()) {
                            return false;
                        }
                    }
                    if (x > this.getX() && y < this.getY()) {
                        piece = board.getPiece(this.getX() + spaces_to_move, this.getY() - spaces_to_move);
                        if (piece != null && piece.isBlack()) {
                            return false;
                        }
                    }

                    if (x < this.getX() && y > this.getY()) {
                        piece = board.getPiece(this.getX() - spaces_to_move, this.getY() + spaces_to_move);
                        if (piece != null && piece.isBlack()) {
                            return false;
                        }
                    }
                    if (x > this.getX() && y > this.getY()) {
                        piece = board.getPiece(this.getX() + spaces_to_move, this.getY() + spaces_to_move);
                        if (piece != null && piece.isBlack()) {
                            return false;
                        }

                    }
                }
                break;
        }

        if (isSlayable(x, y)) {
            System.out.println("ITS SLAYABLE!!!!");
            return false;
        }

        return true;
    }

    @Override
    public boolean moveTo(int x, int y) {
        if (canMove(x, y)) {
            this.setX(x);
            this.setY(y);
            return true;
        } else {
            System.out.println("CHECKMATE WHERE YOU GONNA MOVE NOW?");
            return false;
        }

    }


    public boolean isCheck() {

        for (Piece p : this.board.getAllPieces()) {

            if (this.isWhite && p.isBlack()) {
                if (p.canMove(this.x, this.y)) {
                    return true;
                }
            }
            if (this.isBlack() && p.isWhite) {
                if (p.canMove(this.x, this.y)) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean isCheckMate() {
        ArrayList<Boolean> allPossibilities = new ArrayList<>();

        if (isCheck()) {
            allPossibilities.add(true);
        }

        if (canMove(this.x, this.y - 1) && isSlayable(this.x, this.y - 1)) { // checks on top of him
            allPossibilities.add(true);
        }

        if (canMove(this.x + 1, this.y - 1) && isSlayable(this.x, this.y - 1)) { // checks diagnol top right of him
            allPossibilities.add(true);
        }

        if (canMove(this.x + 1, this.y) && isSlayable(this.x, this.y - 1)) { // checks
            allPossibilities.add(true);
        }

        if (canMove(this.x, this.y - 1) && isSlayable(this.x, this.y - 1)) {
            allPossibilities.add(true);
        }


        if (allPossibilities.size() == 9) {
            return true;
        }
        return false;
    }


    private boolean isSlayable(int x, int y) {
        this.board.getTiles()[x][y].setPiece(this);
        for (Piece p : this.board.getAllPieces()) {

            if (this.isWhite && p.isBlack()) {
                if (p.canMove(x, y)) {
                    this.board.getTiles()[x][y].setPiece(null);
                    return true;
                }
            }
            if (this.isBlack() && p.isWhite) {
                if (p.canMove(x, y)) {
                    this.board.getTiles()[x][y].setPiece(null);
                    return true;
                }
            }
        }
        this.board.getTiles()[x][y].setPiece(null);

        return false;
    }

}
