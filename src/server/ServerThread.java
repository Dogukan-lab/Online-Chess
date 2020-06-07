package server;

import logic.experiment.Tile;
import logic.experiment.TileBoard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());



            TileBoard tileBoard = (TileBoard) objectInputStream.readObject();




        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
