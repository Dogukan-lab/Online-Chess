package pieces;


import logic.experiment.TileBoard;
import org.jfree.fx.FXGraphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Piece implements Serializable {


    protected int x;
    protected int y;
    protected boolean isWhite;
    transient protected BufferedImage image;
    protected TileBoard board;

    public Piece(int x, int y, boolean isWhite, BufferedImage image, TileBoard tileBoard) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.image = image;
        this.board = tileBoard;
    }

    public abstract boolean canMove(int x, int y);

    public abstract boolean moveTo(int x, int y);

    public void draw(FXGraphics2D graphics){
        graphics.drawImage(image, 100 + (x*100), 100 + (y*100), null);
    }


    public boolean isWhite() {
        return isWhite;
    }
    public boolean isBlack(){
        return !this.isWhite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "x= " + x +
                ", y= " + y +
                '}';
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
