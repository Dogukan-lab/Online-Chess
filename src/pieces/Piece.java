package pieces;

import java.util.ArrayList;

public abstract class Piece {
    private String colour;
    private int id;
    private ArrayList<String> moves;

    public Piece(String colour, int id, ArrayList<String> moves) {
        this.colour = colour;
        this.id = id;
        this.moves = moves;
    }


}
