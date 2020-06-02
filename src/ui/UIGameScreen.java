package ui;

import javafx.scene.input.MouseEvent;
import logic.SpriteSheetReader;
import logic.experiment.Tile;
import logic.experiment.TileBoard;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import pieces.Piece;

public class UIGameScreen {

    private ResizableCanvas canvas;
    private TileBoard tileBoard;
    private boolean switchedToGame = true;

    public UIGameScreen(ResizableCanvas canvas) {
        this.canvas = canvas;
        SpriteSheetReader spriteReader = new SpriteSheetReader("resources/sprite-sheet-pieces.png");
        this.tileBoard = new TileBoard(spriteReader.getImages());



////                canvas.setOnMouseReleased(this::mouseReleased);
////                canvas.setOnMouseDragged(this::mouseDragged);
    }



    public void update(double deltaTime){
        if(this.switchedToGame){
            this.canvas.setOnMouseClicked(this::mousePressed);
            this.switchedToGame = false;
        }

    }


    public void draw(FXGraphics2D graphics){
        this.tileBoard.draw(graphics);
    }



    private void mousePressed(MouseEvent e) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Tile t = this.tileBoard.getTiles()[x][y];
                Piece p = this.tileBoard.getTiles()[x][y].getPiece();
                System.out.println(t.toString());
                if (p != null) {
                    if (t.getRectangle().contains(e.getX(), e.getY())) {
                        System.out.println("CLICKED ON A BLACK PIECE: " + p.isBlack());
                        return;
                    }
                }
            }
        }
    }

}
