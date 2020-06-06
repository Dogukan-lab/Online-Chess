package server;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import logic.experiment.TileBoard;

import java.awt.geom.AffineTransform;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private int port = 24224;
    private boolean stop = false;

    private Socket socketPlayerOne = null;
    private Socket socketPlayerTwo = null;
    private boolean isRunning = true;

    public static void main(String[] args) {
        Server server = new Server();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {

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

            System.out.println("Waiting for another client to connect...");
            Socket client2 = this.serverSocket.accept();

            new Thread(() -> {
                handleClientConnectionObject(client, client2);

            }).start();
        }


    }

    private void handleClientConnectionObject(Socket client, Socket client2) {
        System.out.println("client connected, handling connection.");


        try {
//            DataOutputStream dataOutputStreamClient1 = new DataOutputStream(client.getOutputStream());
//            DataOutputStream dataOutputStreamClient2 = new DataOutputStream(client.getOutputStream());

            ObjectOutputStream outClient1 = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inClient1 = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream outClient2 = new ObjectOutputStream(client2.getOutputStream());
            ObjectInputStream inClient2 = new ObjectInputStream(client2.getInputStream());


            boolean connected = true;
            outClient1.writeObject(new Message("I am object server 2.0"));
            outClient2.writeObject(new Message("I am object server 2.0"));

            while (connected) {

                outClient1.writeObject(new Message("2"));
                System.out.println("Writing 2 to client 1");
                Message message = (Message) inClient1.readObject();
                System.out.println("message received: " + message + "... sending now the message to client 2");
                outClient2.writeObject(new Message("1"));
                outClient2.writeObject(message);


                System.out.println("let the client 2 know that there is a message!");
                outClient2.writeObject(new Message("2"));
                System.out.println("Client 2 had now the option to send a message");

                Message messageClient2 = (Message) inClient2.readObject();
                System.out.println("message received: " + messageClient2.getMessage() + "... sending now the message to client 1");
                outClient1.writeObject(new Message("1"));
                outClient1.writeObject(messageClient2);


                System.out.println("let the client 1 know that there is a message!");
            }

            client.close();

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
