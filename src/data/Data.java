package data;

import logic.Client;
import logic.Player;
import logic.SpriteSheetReader;
import logic.experiment.TileBoard;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Data {
     private static Data instance;

    synchronized public static Data getInstance() {
        if (instance == null) {
            System.out.println("Making the DATA");
            instance = new Data();
        }
        return instance;
    }

    private boolean startGame;


    private TileBoard tileBoard;
    private Client client;
    private Player player;
    private ArrayList<BufferedImage> imagesPieces;

    private String turnText;
    private String status;



    private Data() {
        startGame = false;

//        this.imagesPieces = ;

        SpriteSheetReader reader = new SpriteSheetReader("resources/sprite-sheet-pieces.png");

        this.tileBoard = new TileBoard(reader.getImages());
    }


    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }


    synchronized public TileBoard getTileBoard() {
        return tileBoard;
    }

    public void setTileBoard(TileBoard tileBoard) {
        this.tileBoard = tileBoard;
    }

    public static void setInstance(Data instance) {
        Data.instance = instance;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<BufferedImage> getImagesPieces() {
        return imagesPieces;
    }

    public String getTurnText() {
        return turnText;
    }

    public void setTurnText(String turnText) {
        this.turnText = turnText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColorAsText(){
        if(this.player.isWhite()){
            return "white";
        }
        else {
            return "black";
        }
    }
}
