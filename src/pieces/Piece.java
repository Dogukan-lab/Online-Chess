package pieces;

import java.util.ArrayList;
import java.util.Arrays;

public interface Piece {
    void move (ArrayList<ChessPiece> moves);
    void colour(String colour);
    void identification(int id);
}
