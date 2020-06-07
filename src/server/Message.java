package server;

import java.io.Serializable;

public class Message implements Serializable {

    String Message;

    public Message(String message){
        this.Message = message;

    }


    public String getMessage() {
        return Message;
    }
}
