package ui;

import data.Data;
import javafx.scene.input.MouseEvent;
import logic.SpriteSheetReader;
import logic.experiment.Tile;
import logic.experiment.TileBoard;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import pieces.Piece;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;

public class UIGameScreen {

    private Data data = Data.getInstance();

    private ResizableCanvas canvas;
//    private TileBoard tileBoard;
    private boolean switchedToGame = true;
    private boolean receive = false;

    private Tile selectedTile;
    private Piece selectedPiece;

    public UIGameScreen(ResizableCanvas canvas) {
        this.canvas = canvas;
//        SpriteSheetReader spriteReader = new SpriteSheetReader("resources/sprite-sheet-pieces.png");
//        this.tileBoard = new TileBoard(spriteReader.getImages());

        selectedTile = null;
        selectedPiece = null;

    }



    public void update(double deltaTime){
        if(this.switchedToGame){
            this.canvas.setOnMouseClicked(this::mousePressed);
            this.switchedToGame = false;
        }

        if(data.getPlayer().isTurn()){
            data.setTurnText("It's your turn to move a piece!");
        }
        else{
            data.setTurnText("Wait for the other player to make a move!");
        }

        data.getTileBoard().update(deltaTime);

    }


    public void draw(FXGraphics2D graphics) {
        data.getTileBoard().draw(graphics);
        graphics.setFont(new Font("turnText", Font.PLAIN | Font.BOLD, 30));
        graphics.drawString("Turn status: " + data.getTurnText(), 1000, 150);
        graphics.drawString("Your color/side: " + data.getColorAsText(), 1000, 100);
        graphics.drawString("King status: " + data.getStatus(), 1000, 200);
    }



    private void mousePressed(MouseEvent e) {
        if(data.getPlayer().isTurn()){
            if(selectedPiece == null) {
                if (data.getTileBoard().getOutline().contains(e.getX(), e.getY())) {
                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            Tile t = this.data.getTileBoard().getTiles()[x][y];
                            Piece p = this.data.getTileBoard().getTiles()[x][y].getPiece();
//                        System.out.println(t.toString());
                            if (p != null) {
                                if (t.getRectangle().contains(e.getX(), e.getY())) {
                                    if(p.isWhite() && data.getPlayer().isWhite()){
                                        selectedTile = t;
                                        selectedPiece = p;
                                        System.out.println("CLICKED ON A BLACK PIECE: " + p.isBlack());
                                        return;
                                    }
                                    else if (p.isBlack() && !data.getPlayer().isWhite()){
                                        selectedTile = t;
                                        selectedPiece = p;
                                        System.out.println("CLICKED ON A BLACK PIECE: " + p.isBlack());
                                        return;
                                    }
                                    else return;
                                }
                            }
                        }
                    }
                }
            }
            else{
                if(this.data.getTileBoard().getOutline().contains(e.getX(), e.getY())){

                    Tile tileSelected = null;
                    for (int y = 0; y < 8; y++) {
                        for (int x = 0; x < 8; x++) {
                            Tile t = this.data.getTileBoard().getTiles()[x][y];
//                        System.out.println(t.toString());
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
                            Object[] list = data.getTileBoard().getAllPieces().toArray();
                            for(Object p : list){
                                if(p instanceof Piece) {
                                    Piece piece = (Piece) p;
                                    System.out.println("CHECKING FOR PIECES X: " + x + " == " + piece.getX());
                                    System.out.println("CHECKING FOR PIECES Y: " + y + " == " + piece.getY());
                                    System.out.println("");
                                    if (((int) x) == piece.getX() && ((int) y) == piece.getY()) {
                                        if(!piece.equals(selectedPiece)){
                                            System.out.println("REMOVING PIECE: " + piece.toString());
                                            data.getTileBoard().getAllPieces().remove(piece);
                                        }

                                    }
                                }
                            }
                            selectedTile.setPiece(null);
                            tileSelected.setPiece(selectedPiece);
                            data.getPlayer().setMoved_a_piece(true);
                        }

                        selectedTile = null;
                        selectedPiece = null;
                    }
                }

            }
        }
        else {
            System.out.println("ITS NOT YOUR TURN!!!");
        }


    }

}
