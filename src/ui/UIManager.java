package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.board.ChessBoard;
import logic.SpriteSheetReader;
import logic.board.TileBoard;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import pieces.Piece;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class UIManager extends Application {


    public static boolean toMap = false;
    private boolean switchedBlock;
    private ResizableCanvas canvas;
    private double offsetx;
    private double offsety;
    private ChessBoard map;
    private UIStartScreen startScreen;
    private TileBoard board;


    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(this.canvas);
        FXGraphics2D g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);

        this.startScreen = new UIStartScreen(canvas);
        if(toMap){
            canvas.setOnMouseClicked(this::mousePressed);
            canvas.setOnMouseReleased(this::mouseReleased);
            canvas.setOnMouseReleased(this::mouseDragged);

        }

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
        SpriteSheetReader spriteReader = new SpriteSheetReader("resources/sprite-sheet-pieces.png");
        this.board = new TileBoard(map.getTiles(), spriteReader.getImages());
        this.switchedBlock = false;
        this.offsetx = 0;
        this.offsety = 0;

    }


    private void update(double deltaTime) {
        if (!toMap) {
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
            for (Piece piece : this.board.getBlackPieces()) {
                piece.draw(graphics);
            }
            for (Piece piece : this.board.getWhitePieces()) {
                piece.draw(graphics);
            }

        }

    }

    private Piece piece = null;

    public void mousePressed(MouseEvent e) {
        switchedBlock = true;
        for (Piece p : this.board.getWhitePieces()) {
            if (e.getX() < p.getImage().getWidth() && e.getX() > p.getImage().getMinX() && e.getY() < p.getImage().getHeight() && e.getY() > p.getImage().getMinY()) {
                piece = p;

                System.out.println("CLICKED");
                return;
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
                //calculate the offset of the mouse position relative to the block position
                offsetx = e.getX() - piece.getX();
                offsety = e.getY() - piece.getY();
                switchedBlock = false;
            }

            //get the translate position
            Point2D translatePos = new Point2D.Double((e.getX()) - offsetx, (e.getY() - offsety));

            //move the block to the desired position
            piece.moveTo((int) translatePos.getX(), (int) translatePos.getY());

            //draw the canvas again
//            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

            //draw the block again
//            piece.draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        }
    }


    private void redraw(FXGraphics2D graphics) {
        draw(graphics);
    }


}
