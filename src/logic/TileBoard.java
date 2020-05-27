package logic;

public class TileBoard {

    private Tile[][] tiles;


    public TileBoard(Tile[][] tiles) {
        this.tiles = tiles;
        fillBoardWithPieces();
    }

    public void fillBoardWithPieces(){


    }



    public Tile getPieceOnTile(int x, int y){
        Tile tile = this.tiles[x][y];
        return tile;
    }

}
