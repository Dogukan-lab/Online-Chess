package pieces;

import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        super(x, y, isWhite, image, tileBoard);
    }



//    public List<Move> calculateMoves() {
//        List<Move> legalMoves = new ArrayList<>();
//
//        for (int y = 0; y < board.getTiles().length; y++) {
//            for (int x = 0; x < board.getTiles().length; x++) {
//                if (inGrid(x, y)) {
//                    legalMoves.add(new Move(x, y));
//
//                }
//            }
//        }
//        return legalMoves;
//    }
//
//
//    public void printMoves(List<Move> legalmoves) {
//        legalmoves = calculateMoves();
//        for (Move move : legalmoves) {
//            System.out.println("X: " + move.getX() + "\t" + "Y: " + move.getY());
//        }
//    }

    private boolean inGrid(final int x, final int y) {
//        if (x < 0 && x > board.getTiles().length && y < 0 && y > board.getTiles().length)
//            return false;
       return true;
    }


    @Override
    public boolean canMove(final int x, final int y) {
        if (inGrid(x, y)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean moveTo(final int x, final int y) {
        if (canMove(x, y)) {
            super.setX(x);
            super.setY(y);
            return true;
        }
        return false;
    }


}
