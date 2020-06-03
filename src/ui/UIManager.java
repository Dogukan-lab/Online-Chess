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



    /**
    private Piece piece = null;

    public void mousePressed(MouseEvent e) {
        switchedBlock = true;
//        for (Piece p : this.board.getWhitePieces()) {
//            if (e.getX() < p.getImage().getWidth() + p.getImage().getMinX() && e.getX() > p.getImage().getMinX() && e.getY() < p.getImage().getHeight() + p.getImage().getMinY() && e.getY() > p.getImage().getMinY()) {
//                piece = p;
//
//                System.out.println("CLICKED");
//                return;
//            }
//
//        }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Tile t = this.board.getTiles()[x][y];
                Piece p = this.board.getTiles()[x][y].getPiece();
                System.out.println(t.toString());
                if (p != null) {
                    if (t.getRectangle().contains(e.getX(), e.getY())) {
                        piece = p;
                        System.out.println("CLICKED ON A BLACK PIECE: " + piece.isBlack());
                        return;
                    }
                }
            }
        }

    }

    public void mouseReleased(MouseEvent e) {

        piece = null;
        switchedBlock = false;

    }

    private void mouseDragged(MouseEvent e) {



        if (piece != null) {

            //only calculate the offset on grabbing a new block
            if (switchedBlock) {
                System.out.println("IK KOM IN DE IF STATEMENT");
                //calculate the offset of the mouse position relative to the block position
                offsetx = e.getX() - piece.getX();
                offsety = e.getY() - piece.getY();
                switchedBlock = false;
            }

            //get the translate position
             Point2D translatePos = new Point2D.Double((e.getX() - offsetx), (e.getY() - offsety));
//            piece.setX((int)translatePos.getX()/320);
//            piece.setY((int) translatePos.getY()/320);

            //move the block to the desired position
//            piece.moveTo((int) translatePos.getX()/320, (int) translatePos.getY()/320);
            piece.moveTo((int) translatePos.getX()/320, (int) translatePos.getY()/320);

            //draw the canvas again
//            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

            //draw the block again
//            piece.draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        }
    }



    public void mouseClicked(MouseEvent e){

        double mouseX = e.getX();
        double mouseY = e.getY();
        if(!selectedAPiece){
            Piece piece = null;

            for(int y = 0; y < 8; y++){
                for (int x = 0; x < 8; x++) {
                    Tile t = this.board.getTiles()[x][y];
                    if(mouseX > t.getRectangle().getMinX() && mouseX < t.getRectangle().getMaxX() && mouseY > t.getRectangle().getMinY() && mouseY < t.getRectangle().getMaxY()){
                        piece = t.getPiece();
                    }
                }
            }
        }

    }

**/
}
