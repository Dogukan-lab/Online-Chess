import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;

public class UIstartScreen extends Application {


    private ResizableCanvas canvas;


    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(this.canvas);
        FXGraphics2D g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if(last == -1){
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


    }



    private void update(double deltaTime) {

    }



    public void draw(Graphics2D graphics){

    }



}
