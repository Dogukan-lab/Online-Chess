package server;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import logic.experiment.Tile;
import logic.experiment.TileBoard;

import java.awt.geom.AffineTransform;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket;
    private int port = 24224;
    private boolean stop = false;

    private ArrayList<Socket> listPlayers = new ArrayList<>();



    private boolean playerOne = false;
    private boolean playerTwo = false;
    private boolean isRunning = true;


    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException, ClassNotFoundException {

        if (this.serverSocket != null) {
            System.out.println("Server already created socket, please stop first!");
            return;
        }

        try {
            this.serverSocket = new ServerSocket(this.port);
            this.isRunning = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (isRunning) {

                System.out.println("Waiting for client to connect...");
                Socket client = this.serverSocket.accept();
                this.listPlayers.add(client);



                System.out.println("Waiting for another client to connect...");
                Socket client2 = this.serverSocket.accept();
                this.listPlayers.add(client2);




            if (client.isConnected() && client2.isConnected()) {
                new Thread(() -> {
                    handleClientConnectionObject(client, client2);

                }).start();

                this.playerOne = true;
                this.playerTwo = false;
            }
            else {
                System.out.println("oNE OF THE CLIENTS ISN'T CONNECTED!");
            }


        }
    }


    private void handleClientConnectionObject(Socket client, Socket client2) {
        System.out.println("client connected, handling connection.");


        try {
            ObjectOutputStream outClient1 = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inClient1 = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream outClient2 = new ObjectOutputStream(client2.getOutputStream());
            ObjectInputStream inClient2 = new ObjectInputStream(client2.getInputStream());


            boolean connected = true;
            outClient1.writeObject(new Message("I am object server 2.0"));
            outClient2.writeObject(new Message("I am object server 2.0"));

            Message colorClient1 = (Message) inClient1.readObject();
            outClient1.writeObject(new Message("fine"));

            Message colorClient2 = (Message) inClient2.readObject();
            if(colorClient1.getMessage().equals(colorClient2.getMessage())){
                System.out.println("HAS TO CHANGE COLOR");
                outClient2.writeObject(new Message("color already has been taken, change it"));
            }
            else {
                outClient2.writeObject(new Message("fine"));
            }


            while (connected) {

                outClient1.writeObject(new Message("2"));
                System.out.println("Writing 2 to client 1");
                TileBoard message = (TileBoard) inClient1.readObject();
                System.out.println("message received: " + message + "... sending now the message to client 2");
                outClient2.writeObject(new Message("1"));
                outClient2.writeObject(message);


                System.out.println("let the client 2 know that there is a message!");
                outClient2.writeObject(new Message("2"));
                System.out.println("Client 2 had now the option to send a message");

                TileBoard messageClient2 = (TileBoard) inClient2.readObject();
                System.out.println("message received: " + messageClient2 + "... sending now the message to client 1");
                outClient1.writeObject(new Message("1"));
                outClient1.writeObject(messageClient2);


                System.out.println("let the client 1 know that there is a message!");
            }

            System.out.println("CLEARING ALL DATA AND LET THE GAME END...");

            this.listPlayers.remove(client);
            this.listPlayers.remove(client2);

            client.close();
            client2.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleClientConnectionData(Socket client) {
        System.out.println("client connected, handling connection.");


        try {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            boolean connected = true;
            out.writeUTF("Hi I am the server 1.0");

            while (connected) {
                String message = in.readUTF();
                out.writeUTF(message);
            }

            out.writeUTF("Hi and bye!");
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
