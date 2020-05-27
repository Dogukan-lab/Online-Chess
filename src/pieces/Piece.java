package pieces;

import logic.Tile;
import logic.TileBoard;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {


    protected int x;
    protected int y;
    protected boolean isWhite;
    protected String file_path;
    protected TileBoard board;

    public Piece(int x, int y, boolean isWhite, String file_path,TileBoard tileBoard) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.file_path = file_path;
        this.board = tileBoard;
    }


    public abstract boolean canMove(int x, int y);

    public abstract void moveTo(int x, int y);

    public abstract void draw();


    public boolean isWhite() {
        return isWhite;
    }
    public boolean isBlack(){
        return !this.isWhite;
    }
}
