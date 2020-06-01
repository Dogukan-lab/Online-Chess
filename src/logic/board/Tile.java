package logic.board;

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

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        if (piece == null) {
            return "Tile{" +
                    "nameSpace=' " + nameSpace + "  " + '\'' +
                    "Piece=' " + " " + "  " + '\'' +
                    '}';

        } else {
            return "Tile{" +
                    "nameSpace=' " + nameSpace + "  " + '\'' +
                    "Piece=' " + piece.toString() + "  " + '\'' +
                    '}';
        }
    }
}
