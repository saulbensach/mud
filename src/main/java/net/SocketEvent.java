package net;

import event.AbstractEvent;

import java.net.Socket;

public class SocketEvent extends AbstractEvent {

    private Socket socket;

    public SocketEvent(Socket socket){
        this.socket = socket;
    }

    public void accept(){

    }
}
