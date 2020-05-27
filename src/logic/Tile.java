package logic;

import pieces.Piece;

import java.awt.geom.Rectangle2D;

public class Tile {

    private Rectangle2D rectangle;
    private String nameSpace;
    private Piece piece;


    public Tile(int x, int y, int width, int height, String name) {
        this.rectangle = new Rectangle2D.Double(x, y, width, height);
        this.nameSpace = name;
    }


    public Rectangle2D getRectangle() {
        return rectangle;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "nameSpace='" + nameSpace + '\'' +
                '}';
    }
}
