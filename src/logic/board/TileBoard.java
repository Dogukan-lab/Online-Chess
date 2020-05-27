package logic.board;

import pieces.Knight;
import pieces.Piece;

import java.util.ArrayList;

public class TileBoard {

    private Tile[][] tiles;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;


    public TileBoard(Tile[][] tiles) {
        this.tiles = tiles;
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();

        fillBoardWithPieces();
    }

    public void fillBoardWithPieces(){
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getPieceOnTile(int x, int y){
        Tile tile = this.tiles[x][y];
        return tile;
    }


}
