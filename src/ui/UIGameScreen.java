package ui;

import javafx.scene.input.MouseEvent;
import logic.SpriteSheetReader;
import logic.experiment.Tile;
import logic.experiment.TileBoard;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import pieces.Piece;

import java.util.ArrayList;
import java.util.Iterator;

public class UIGameScreen {

    private ResizableCanvas canvas;
    private TileBoard tileBoard;
    private boolean switchedToGame = true;

    private Tile selectedTile;
    private Piece selectedPiece;

    public UIGameScreen(ResizableCanvas canvas) {
        this.canvas = canvas;
        SpriteSheetReader spriteReader = new SpriteSheetReader("resources/sprite-sheet-pieces.png");
        this.tileBoard = new TileBoard(spriteReader.getImages());

        selectedTile = null;
        selectedPiece = null;

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
        if(selectedPiece == null) {
            if (this.tileBoard.getOutline().contains(e.getX(), e.getY())) {
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        Tile t = this.tileBoard.getTiles()[x][y];
                        Piece p = this.tileBoard.getTiles()[x][y].getPiece();
//                        System.out.println(t.toString());
                        if (p != null) {
                            if (t.getRectangle().contains(e.getX(), e.getY())) {
                                selectedTile = t;
                                selectedPiece = p;
                                System.out.println("CLICKED ON A BLACK PIECE: " + p.isBlack());
                                return;
                            }
                        }
                    }
                }
            }
        }
        else{
            if(this.tileBoard.getOutline().contains(e.getX(), e.getY())){
                Tile tileSelected = null;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        Tile t = this.tileBoard.getTiles()[x][y];
                        if (t.getRectangle().contains(e.getX(), e.getY())) {
                            tileSelected = t;
                            break;
                        }
                    }
                }

                System.out.println(tileSelected.toString());
                if(tileSelected.getPiece() != null && tileSelected.getPiece().equals(selectedPiece)){
                    selectedTile = null;
                    selectedPiece = null;
                }
                else {
                    double x = tileSelected.getRectangle().getMinX()/100;
                    double y = tileSelected.getRectangle().getMinY()/100;

                    if(selectedPiece.moveTo((int)x, (int)y)){
                        Object[] list = this.tileBoard.getAllPieces().toArray();
                        for(Object p : list){
                            if(p instanceof Piece) {
                                Piece piece = (Piece) p;
                                System.out.println("CHECKING FOR PIECES X: " + x + " == " + piece.getX());
                                System.out.println("CHECKING FOR PIECES Y: " + y + " == " + piece.getY());
                                System.out.println("");
                                if (((int) x) == piece.getX() && ((int) y) == piece.getY()) {
                                    if(!piece.equals(selectedPiece)){
                                        System.out.println("REMOVING PIECE: " + piece.toString());
                                        this.tileBoard.getAllPieces().remove(piece);
                                    }

                                }
                            }
                        }
                        selectedTile.setPiece(null);
                        tileSelected.setPiece(selectedPiece);
                    }

                    selectedTile = null;
                    selectedPiece = null;
                }
            }

        }

    }

}
