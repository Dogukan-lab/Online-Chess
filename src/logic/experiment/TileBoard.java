package logic.experiment;

import org.jfree.fx.FXGraphics2D;
import pieces.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileBoard {

    private Rectangle2D outline;
    private Tile[][] tiles;
    private ArrayList<Piece> allPieces;
    private ArrayList<BufferedImage> imagesPieces;
    private int positioning = 100;
    private float thickness = 5;


    public TileBoard(ArrayList<BufferedImage> images){
        this.imagesPieces = images;
        this.tiles = new Tile[8][8];
        makeBoard();

        this.allPieces = new ArrayList<>();
        fillBoard();

        double x_min = tiles[0][0].getRectangle().getMinX();
        double y_min = tiles[0][0].getRectangle().getMinY();
        double width = tiles[0][0].getRectangle().getWidth();
        double height = tiles[0][0].getRectangle().getHeight();

        this.outline = new Rectangle2D.Double(x_min, y_min, width*8, height*8);

    }


    public void makeBoard(){
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if(y%2==0){
                    if(x%2==0){
                        tiles[x][y] = new Tile((x+1)*positioning, (y+1)*positioning, positioning, positioning, Color.white);
                    }
                    else {
                        tiles[x][y] = new Tile((x+1)*positioning, (y+1)*positioning, positioning, positioning, Color.red.darker().darker().darker());
                    }
                }
                else {
                    if(x%2==0){
                        tiles[x][y] = new Tile((x+1)*positioning, (y+1)*positioning, positioning, positioning, Color.red.darker().darker().darker());
                    }
                    else {
                        tiles[x][y] = new Tile((x+1)*positioning, (y+1)*positioning, positioning, positioning, Color.white);
                    }
                }

            }
        }
    }

    public void fillBoard(){

        Piece rook1Black = new Rook(0,0, false, this.imagesPieces.get(10), this);
        tiles[0][0].setPiece(rook1Black);

        Piece knight1Black = new Knight(1,0, false, this.imagesPieces.get(9), this);
        tiles[1][0].setPiece(knight1Black);

        Piece bishop1Black = new Bishop(2, 0, false, this.imagesPieces.get(8), this);
        tiles[2][0].setPiece(bishop1Black);

        Piece kingBlack = new King(3, 0, false, this.imagesPieces.get(6), this);
        tiles[3][0].setPiece(kingBlack);

        Piece queenBlack = new Queen(4, 0, false, this.imagesPieces.get(7), this);
        tiles[4][0].setPiece(queenBlack);

        Piece bischop2Black = new Bishop(5, 0, false, this.imagesPieces.get(8), this);
        tiles[5][0].setPiece(bischop2Black);

        Piece knight2Black = new Knight(6,0, false, this.imagesPieces.get(9), this);
        tiles[6][0].setPiece(knight2Black);

        Piece rook2Black = new Rook(7,0, false, this.imagesPieces.get(10), this);
        tiles[7][0].setPiece(rook2Black);


        Piece pawn1 = new Pawn(0,1, false, this.imagesPieces.get(11), this);
        tiles[0][1].setPiece(pawn1);

        Piece pawn2 = new Pawn(1,1, false, this.imagesPieces.get(11), this);
        tiles[1][1].setPiece(pawn2);

        Piece pawn3 = new Pawn(2,1, false, this.imagesPieces.get(11), this);
        tiles[2][1].setPiece(pawn3);

        Piece pawn4 = new Pawn(3,1, false, this.imagesPieces.get(11), this);
        tiles[3][1].setPiece(pawn4);

        Piece pawn5 = new Pawn(4,1, false, this.imagesPieces.get(11), this);
        tiles[4][1].setPiece(pawn5);

        Piece pawn6 = new Pawn(5,1, false, this.imagesPieces.get(11), this);
        tiles[5][1].setPiece(pawn6);

        Piece pawn7 = new Pawn(6,1, false, this.imagesPieces.get(11), this);
        tiles[6][1].setPiece(pawn7);

        Piece pawn8 = new Pawn(7,1, false, this.imagesPieces.get(11), this);
        tiles[7][1].setPiece(pawn8);

        this.allPieces.add(rook1Black);
        this.allPieces.add(rook2Black);
        this.allPieces.add(knight1Black);
        this.allPieces.add(knight2Black);
        this.allPieces.add(bishop1Black);
        this.allPieces.add(bischop2Black);
        this.allPieces.add(kingBlack);
        this.allPieces.add(queenBlack);
        this.allPieces.add(pawn1);
        this.allPieces.add(pawn2);
        this.allPieces.add(pawn3);
        this.allPieces.add(pawn4);
//        this.allPieces.add(pawn5);
        this.allPieces.add(pawn6);
        this.allPieces.add(pawn7);
        this.allPieces.add(pawn8);




        Piece rook1White = new Rook(0,7, true, this.imagesPieces.get(4), this);
        tiles[0][7].setPiece(rook1White);

        Piece knight1White = new Knight(1,7, true, this.imagesPieces.get(3), this);
        tiles[1][7].setPiece(knight1White);

        Piece bischop1White = new Bishop(2, 7, true, this.imagesPieces.get(2), this);
        tiles[2][7].setPiece(bischop1White);

        Piece kingWhite = new King(3, 7, true, this.imagesPieces.get(0), this);
        tiles[3][7].setPiece(kingWhite);

        Piece queenWhite = new Queen(4, 7, true, this.imagesPieces.get(1), this);
        tiles[4][7].setPiece(queenWhite);

        Piece bischop2White = new Bishop(5, 7, true, this.imagesPieces.get(2), this);
        tiles[5][7].setPiece(bischop2White);

        Piece knight2White = new Knight(6,7, true, this.imagesPieces.get(3), this);
        tiles[6][7].setPiece(knight2White);

        Piece rook2White = new Rook(7,7, true, this.imagesPieces.get(4), this);
        tiles[7][7].setPiece(rook2White);


        Piece pawnW1 = new Pawn(0,6, true, this.imagesPieces.get(5), this);
        tiles[0][6].setPiece(pawnW1);

        Piece pawnW2 = new Pawn(1,6, true, this.imagesPieces.get(5), this);
        tiles[1][6].setPiece(pawnW2);

        Piece pawnW3 = new Pawn(2,6, true, this.imagesPieces.get(5), this);
        tiles[2][6].setPiece(pawnW3);

        Piece pawnW4 = new Pawn(3,6, true, this.imagesPieces.get(5), this);
        tiles[3][6].setPiece(pawnW4);

        Piece pawnW5 = new Pawn(4,6, true, this.imagesPieces.get(5), this);
        tiles[4][6].setPiece(pawnW5);

        Piece pawnW6 = new Pawn(5,6, true, this.imagesPieces.get(5), this);
        tiles[5][6].setPiece(pawnW6);

        Piece pawnW7 = new Pawn(6,6, true, this.imagesPieces.get(5), this);
        tiles[6][6].setPiece(pawnW7);

        Piece pawnW8 = new Pawn(7,6, true, this.imagesPieces.get(5), this);
        tiles[7][6].setPiece(pawnW8);


        this.allPieces.add(rook1White);
        this.allPieces.add(rook2White);
        this.allPieces.add(knight1White);
        this.allPieces.add(knight2White);
        this.allPieces.add(bischop1White);
        this.allPieces.add(bischop2White);
        this.allPieces.add(kingWhite);
        this.allPieces.add(queenWhite);
        this.allPieces.add(pawnW1);
        this.allPieces.add(pawnW2);
        this.allPieces.add(pawnW3);
        this.allPieces.add(pawnW4);
//        this.allPieces.add(pawnW5);
        this.allPieces.add(pawnW6);
        this.allPieces.add(pawnW7);
        this.allPieces.add(pawnW8);


//        for(int yC = 0; yC < 8; yC++){
//            for(int xC = 0; xC < 8; xC++){
//                System.out.println(tiles[xC][yC].toString());
//            }
//        }
    }

    public Piece getPiece(int x, int y) {
        for (Piece p : this.allPieces)
        {
            if (p.getX() == x && p.getY() == y)
            {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Piece> getAllPieces() {
        return allPieces;
    }

    public void draw(FXGraphics2D graphics){
        for (int y = 0; y < this.tiles.length; y++) {
            for (int x = 0; x < this.tiles.length; x++) {
                tiles[x][y].draw(graphics);
            }
        }

        Stroke stroke = graphics.getStroke();
        graphics.setStroke(new BasicStroke(this.thickness));

        graphics.setColor(new Color(153, 102, 51));
        graphics.draw(this.outline);
        graphics.setStroke(stroke);

        for(Piece p : this.allPieces){
            p.draw(graphics);
        }
    }


    public Tile[][] getTiles() {
        return tiles;
    }

    public Rectangle2D getOutline() {
        return outline;
    }
}
