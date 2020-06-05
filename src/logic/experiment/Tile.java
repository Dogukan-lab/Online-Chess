package logic.experiment;

import org.jfree.fx.FXGraphics2D;
import pieces.Piece;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Tile {

    private Rectangle2D rectangle;
    private Piece piece;
    private Color color;


    public Tile(double x, double y, double width, double height, Color color) {
        this.rectangle = new Rectangle2D.Double(x, y, width, height);
        this.piece = null;
        this.color = color;
    }


    public Rectangle2D getRectangle() {
        return rectangle;
    }


    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Color getColor() {
        return color;
    }

    public void draw(FXGraphics2D graphics){
        graphics.setColor(this.color);
        graphics.draw(this.rectangle);
        graphics.fill(this.rectangle);

        graphics.setColor(Color.white);
    }

    @Override
    public String toString() {
        if (piece == null) {
            return "Tile{" +
                    "Piece=' " + " " + "  " + '\'' +
                    '}';

        } else {
            return "Tile{" +
                    "Piece=' " + piece.toString() + "  " + '\'' +
                    '}';
        }
    }
}
