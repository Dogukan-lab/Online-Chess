package data;

import logic.Client;
import logic.SpriteSheetReader;
import logic.experiment.TileBoard;

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
    private boolean playerOneTurn;
    private boolean playerTwoTurn;

    private TileBoard tileBoard;
    private Client client;


    private Data() {
        startGame = false;
        playerOneTurn = true;
        playerTwoTurn = false;

        SpriteSheetReader spriteReader = new SpriteSheetReader("resources/sprite-sheet-pieces.png");
        this.tileBoard = new TileBoard(spriteReader.getImages());
    }


    public boolean isStartGame() {
        return startGame;
    }

    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public void setPlayerOneTurn(boolean playerOneTurn) {
        this.playerOneTurn = playerOneTurn;
    }

    public boolean isPlayerTwoTurn() {
        return playerTwoTurn;
    }

    public void setPlayerTwoTurn(boolean playerTwoTurn) {
        this.playerTwoTurn = playerTwoTurn;
    }


    public TileBoard getTileBoard() {
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
}
