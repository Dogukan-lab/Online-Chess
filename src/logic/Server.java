package logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private int port = 24224;
    private boolean isRunning = true;

    public static void main(String[] args) {
        Server server = new Server();
        server.connect();
    }

    public void connect() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Waiting for clients...");
            Socket socket = this.serverSocket.accept();

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            System.out.println("Client has connected from: " + socket.getInetAddress().getHostName());
            while (this.isRunning) {
                String read = inputStream.readUTF();
                if (read.equals("quit")) {
                    isRunning = false;
                    System.out.println("Client has disconnected from: " + socket.getInetAddress().getHostName());
                }
                System.out.println(read);
            }

            outputStream.close();
            socket.close();
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
