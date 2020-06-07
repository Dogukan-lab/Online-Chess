package pieces;

import data.Direction;
import logic.experiment.Tile;
import logic.experiment.TileBoard;
import sun.rmi.runtime.Log;

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

        int spaces_to_move;
        Piece piece;
        switch (this.direction) {
            case UP:
                spaces_to_move = Math.abs(y - this.getY());
                if (this.isWhite()) {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX(), this.getY() - spaces_to_move);
                        if (piece != null && piece.isWhite()) {
                            System.out.println("Ik kan niet het speelstuk eten");
                            return false;
                        }
                    } else return false;
                } else {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX(), this.getY() - spaces_to_move);
                        if (piece != null && piece.isBlack()) {
                            System.out.println("Ik kan niet het speelstuk eten");
                            return false;
                        }
                    } else return false;
                }

                break;
            case DOWN:
                spaces_to_move = Math.abs(y - this.getY());
                if (this.isWhite()) {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX(), this.getY() + spaces_to_move);
                        if (piece != null && piece.isWhite()) {
                            System.out.println("Checking for white");
                            System.out.println("Ik kan niet het speelstuk eten");
                            return false;
                        }
                    } else return false;
                } else {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX(), this.getY() + spaces_to_move);
                        if (piece != null && piece.isBlack()) {
                            System.out.println("Checking for Black");
                            return false;
                        }
                    } else return false;
                }
                break;
            case LEFT:
                spaces_to_move = Math.abs(x - this.getX());
                if (this.isWhite()) {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX() - 1, this.getY());
                        if (piece != null && piece.isWhite()) {
                            return false;
                        }
                    } else if (spaces_to_move > 2) return false;
                } else {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX() - 1, this.getY());
                        if (piece != null && piece.isBlack()) {
                            return false;
                        }
                    } else if (spaces_to_move > 2) return false;
                }
                break;
            case RIGHT:
                spaces_to_move = Math.abs(x - this.getX());
                if (this.isWhite()) {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX() + 1, this.getY());
                        if (piece != null && piece.isWhite()) {
                            return false;
                        }
                    } else if (spaces_to_move > 2) return false;
                } else {
                    if (spaces_to_move < 2) {
                        piece = board.getPiece(this.getX() + 1, this.getY());
                        if (piece != null && piece.isBlack()) {
                            return false;
                        }
                    } else if (spaces_to_move > 2) return false;
                }

                break;
            case DIAGONAL:
                spaces_to_move = Math.abs(y - this.getY());
                if (this.isWhite()) {
                    if (spaces_to_move < 2) {
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
                    } else return false;
                } else {
                    if (spaces_to_move < 2) {
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
                    } else return false;
                }
                break;
        }
        return true;
    }

    @Override
    public boolean moveTo(int x, int y) {
        if (canMove(x, y) && !isCheckMate()) {
            this.setX(x);
            this.setY(y);
            return true;
        } else {
            System.out.println("CHECKMATE WHERE YOU GONNA MOVE NOW?");
            return false;
        }

    }

    public boolean isCheckMate() {
        for (Piece p : this.board.getAllPieces()) {
            if (p instanceof Rook) {
                if (p.isWhite() && this.isBlack()) {
                    if (p.canMove(this.getX(), this.getY())) {
                        System.out.println("Reached the white check for the rook!");
                        return true;
                    }
                    else return false;
                } else {
                    if (p.canMove(this.getX(), this.getY())) {
                        System.out.println("Reached the black check for the rook!");
                        return true;
                    } else return false;
                }
            } else if (p instanceof Pawn) {
                if (p.isWhite()) {
                    if (p.canMove(this.getX(), this.getY())) return true;
                    else return false;
                } else {
                    if (p.canMove(this.getX(), this.getY())) return true;
                    else return false;
                }
            }
        }
        return false;
    }
}
