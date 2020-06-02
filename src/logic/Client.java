package logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String hostName;
    private int port;
    private boolean isRunning;

    public static void main(String[] args) {
        Client client = new Client("localhost", 24224);
        client.connect();
    }

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        this.isRunning = true;
    }

    private void connect() {

        try {
            Socket socket = new Socket(this.hostName, this.port);
            Scanner scanner = new Scanner(System.in);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            outputStream.writeUTF(name);

            String input = scanner.nextLine();

            input = scanner.nextLine();
            outputStream.writeUTF(input);
            outputStream.flush();
            System.out.println(input);


            inputStream.close();
            outputStream.close();
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
