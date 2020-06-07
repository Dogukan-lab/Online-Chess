package logic;

import data.Data;
import logic.experiment.TileBoard;
import server.Message;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private String hostName;
    private int port;
    private boolean connected;
    private Socket socket;

    private Data data = Data.getInstance();
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Player player;
    private SpriteSheetReader spriteSheetReader;

//    public static void main(String[] args) {
//        Client client = new Client("localhost", 24224);
//        client.connect();
//    }

    public Client(String hostName, int port, boolean isWhite) {
        this.hostName = hostName;
        this.port = port;
        this.connected = false;
        this.socket = null;

        this.player = new Player(isWhite);
        data.setPlayer(player);
        spriteSheetReader = new SpriteSheetReader("resources/sprite-sheet-pieces.png");
        data.getTileBoard().setImagesPieces(spriteSheetReader.getImages());
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




    public boolean connectAndPlayObject() {

        if(connected){
            System.out.println("Client already connected to the server!");
            return true;

        }

        try {

            this.socket = new Socket(this.hostName, this.port);
            this.connected = true;

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());


            Message serverID = (Message) objectInputStream.readObject();
            System.out.println("Connected with server: " + serverID.getMessage());

            objectOutputStream.writeObject(new Message(player.isWhite() + ""));
            System.out.println("Player is white side: " + this.player.isWhite());

            Message response = (Message) objectInputStream.readObject();
            if(!response.getMessage().equals("fine")){
                this.player.setWhite(!this.player.isWhite());
            }
            System.out.println("Player is white side: " + this.player.isWhite());


//            Scanner scanner = new Scanner(System.in);
            while(this.connected){
                System.out.println("Waiting for action to do...");
                Message choice = (Message) objectInputStream.readObject();
                System.out.println("I got something: " + choice.getMessage());
                if(choice.getMessage().equals("1")){
                    System.out.println("Time to receive the board!");
                    TileBoard tileBoard = (TileBoard) objectInputStream.readObject();
                    ArrayList<BufferedImage> images = data.getImagesPieces();
                    data.setTileBoard(tileBoard);
                    data.getTileBoard().setImagesPieces(spriteSheetReader.getImages());
                }
                else if(choice.getMessage().equals("2")){
                    System.out.println("your turn!!!");
                    data.getPlayer().setTurn(true);
                    while (!data.getPlayer().isMoved_a_piece()){
                        System.out.println(data.getPlayer().isMoved_a_piece());
                    }
                    data.getPlayer().setTurn(false);
                    data.getPlayer().setMoved_a_piece(false);
                    TileBoard tileBoard = data.getTileBoard();
                    objectOutputStream.writeObject(tileBoard);
                }


            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



        return false;
    }



}
