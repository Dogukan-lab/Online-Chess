package logic;

import data.Data;
import logic.experiment.TileBoard;
import server.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String hostName;
    private int port;
    private boolean connected;
    private Socket socket;

    private Data data = Data.getInstance();
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

//    public static void main(String[] args) {
//        Client client = new Client("localhost", 24224);
//        client.connect();
//    }

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        this.connected = false;
        this.socket = null;
    }

    public boolean connectData() {

        if(connected){
            System.out.println("Client already connected to the server!");
            return true;

        }

        try {
            this.socket = new Socket(this.hostName, this.port);
            this.connected = true;

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

//            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            objectInputStream = new ObjectInputStream(socket.getInputStream());

            String serverID = inputStream.readUTF();
            System.out.println("Connected with server: " + serverID);

            Scanner scanner = new Scanner(System.in);
            while(this.connected){
                System.out.print("Type here your message: ");
                String input = scanner.nextLine();

                outputStream.writeUTF(input);


                String response = inputStream.readUTF();
                System.out.println("got server response: " + response);
            }

//            System.out.println("Got message from server: " + message);

//            System.out.println("Enter your name: ");
//            String name = scanner.nextLine();
//            outputStream.writeUTF(name);
//
//            String input = scanner.nextLine();
//
//            input = scanner.nextLine();
//            outputStream.writeUTF(input);
//            outputStream.flush();
//            System.out.println(input);



//            inputStream.close();
//            outputStream.close();
//            socket.close();
//            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return false;
    }



    public void makeMove(){

        try {
            objectOutputStream.writeObject(data.getTileBoard());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMove(){
        TileBoard board = null;
        try {
            board = (TileBoard) objectInputStream.readObject();
            System.out.println(board);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        data.setTileBoard(board);
    }



    public boolean connectObject() {

        if(connected){
            System.out.println("Client already connected to the server!");
            return true;

        }

        try {
            this.socket = new Socket(this.hostName, this.port);
            this.connected = true;

//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());


            Message serverID = (Message) objectInputStream.readObject();
            System.out.println("Connected with server: " + serverID.getMessage());

            Scanner scanner = new Scanner(System.in);
            while(this.connected){
                System.out.println("Waiting for response...");
                Message choice = (Message) objectInputStream.readObject();
                System.out.println("I got something: " + choice.getMessage());
                if(choice.getMessage().equals("1")){
                    Message message = (Message) objectInputStream.readObject();
                    System.out.println("got a message: " + message.getMessage());
                }
                else if(choice.getMessage().equals("2")){
                    System.out.print("Type here your message: ");
                    Message input = new Message(scanner.nextLine());

                    objectOutputStream.writeObject(input);
                }


            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



        return false;
    }



}
