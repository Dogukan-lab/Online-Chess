package pieces;

import logic.Tile;
import logic.TileBoard;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Piece {


    protected int x;
    protected int y;
    protected boolean isWhite;
    protected BufferedImage image;
    protected TileBoard board;

    public Piece(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.image = image;
        this.board = tileBoard;
    }


    public abstract boolean canMove(int x, int y);

    public abstract void moveTo(int x, int y);

    public void draw(FXGraphics2D graphics){
        AffineTransform tx = new AffineTransform();
        tx.translate(510, 160);
        tx.scale(0.3, 0.3);
        graphics.setTransform(tx);
        graphics.drawImage(image, x*320, y*320, null);
    }


    public boolean isWhite() {
        return isWhite;
    }
    public boolean isBlack(){
        return !this.isWhite;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "x= " + x +
                ", y= " + y +
                '}';
    }
}
