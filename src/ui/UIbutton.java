package ui;

import javafx.scene.transform.Affine;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class UIbutton {

    private String titleName;
    private double translateX;
    private double translateY;
    private double scale;
    private double rotation;

    private Rectangle2D rectangle;
    private Point2D position;
    private Color color;
    private double height;
    private double width;


    public UIbutton(String titleName, double translateX, double translateY, double scale, double rotation, double positionX, double positionY, double width, double height, Color color) {
        this.titleName = titleName;
        this.translateX = translateX;
        this.translateY = translateY;
        this.scale = scale;
        this.rotation = rotation;
        this.position = new Point2D.Double(width, height);

        this.rectangle = new Rectangle2D.Double(this.position.getX(), this.position.getY(), width, height);
        this.width = width;
        this.height = height;
        this.color = color;

    }



    public void draw(FXGraphics2D graphics){
        graphics.setTransform(getTransform());
        graphics.setColor(this.color);
        graphics.draw(this.rectangle);
        graphics.fill(this.rectangle);

        graphics.setColor(Color.white);
        graphics.drawString(this.titleName, (int)(this.rectangle.getMinX() + (this.rectangle.getMaxX() - this.rectangle.getMinX())/3),
                            (int)(this.rectangle.getMinY() + (this.rectangle.getMaxY() - this.rectangle.getMinY())/2));

    }

    public void update(double deltaTime){

        this.rectangle.setRect(this.position.getX() - this.rectangle.getWidth()/2, this.position.getY() - this.rectangle.getHeight()/2, this.width, this.height);
    }


    public AffineTransform getTransform(){
        AffineTransform tx = new AffineTransform();
        tx.translate(this.translateX, this.translateY);
        tx.scale(this.scale, this.scale);
        tx.rotate(this.rotation);
        return tx;
    }

    public Rectangle2D getRectangle() {
        return rectangle;
    }

    public Point2D getPosition() {
        return position;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setPosition(double x, double y) {
        this.position.setLocation(new Point2D.Double(x, y));
    }
}
