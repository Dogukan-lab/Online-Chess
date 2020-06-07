package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UIManager extends Application {


    public static boolean toMap = false;
    private boolean switchedToGameSettings = false;

    private ResizableCanvas canvas;
    private UIStartScreen startScreen;
    private UIGameScreen gameScreen;

    private Stage stage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;

        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(this.canvas);
        FXGraphics2D g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        this.startScreen = new UIStartScreen(canvas);
        this.gameScreen = new UIGameScreen(canvas);


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
        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Online Chess");
        stage.show();
        draw(g2d);

    }


    @Override
    public void init() throws Exception {


    }


    private void update(double deltaTime) {
        if (!toMap) {
            this.startScreen.update(deltaTime);
        }
        else {
            this.gameScreen.update(deltaTime);
        }

//        if (!switchedToGameSettings) {
//            if(toMap){
////                canvas.setOnMousePressed(this::mousePressed);
////                canvas.setOnMouseReleased(this::mouseReleased);
////                canvas.setOnMouseDragged(this::mouseDragged);
////                stage.setX(200);
////                stage.setY(20);
////                stage.setWidth(map.getWidth() + 300);
////                stage.setHeight(map.getMapHeight());
////                stage.setResizable(false);
////                canvas.setWidth(map.getWidth());
////                canvas.setHeight(map.getMapHeight());
//                switchedToGameSettings = true;
//                System.out.println(canvas.getWidth());
//                System.out.println(canvas.getHeight());
//            }
//        }
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        if (!toMap) {
            this.startScreen.draw(graphics);
        } else {
            this.gameScreen.draw(graphics);
        }

    }
}
