package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.ChessBoard;
import logic.Tile;
import logic.TileBoard;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import org.jfree.fx.ResizableCanvas;

import javax.xml.soap.Node;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class UIManager extends Application {


    public static boolean toMap = false;
    private ResizableCanvas canvas;
    private ChessBoard map;
    private UIStartScreen startScreen;


    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(this.canvas);
        FXGraphics2D g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);

        this.startScreen = new UIStartScreen(canvas);


        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        Thread.sleep(0);
        primaryStage.setScene(new Scene(mainPane, 1920, 1080));
        primaryStage.setTitle("Online Chess");
        primaryStage.show();
        draw(g2d);

    }


    @Override
    public void init() throws Exception {

        this.map = new ChessBoard("resources/ChessBoard.json", canvas);
        TileBoard board = new TileBoard(map.getTiles());

    }


    private void update(double deltaTime) {

        if(!toMap){
            this.startScreen.update(deltaTime);
        }
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        if (!toMap) {
            this.startScreen.draw(graphics);
        } else {
             this.map.draw(graphics);


        }

    }


    private void redraw(FXGraphics2D graphics) {
        draw(graphics);
    }


}
