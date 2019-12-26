package net;

import event.EventDispatcher;
import player.*;
import world.World;
import tick.TickEvent;
import tick.TickEventHandler;
import tick.TickManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    private static final int PORT = 4000;
    private ServerSocket serverSocket;

    private EventDispatcher dispatcher = EventDispatcher.getInstance();

    private Server(){
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening port: " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(!serverSocket.isClosed()){
            try {
                Socket socket = serverSocket.accept();
                dispatcher.dispatch(new PlayerConnectedEvent(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        World.getInstance();
        EventDispatcher dispatcher = EventDispatcher.getInstance();

        dispatcher.registerHandler(PlayerConnectedEvent.class, new PlayerConnectedEventHandler());
        dispatcher.registerHandler(PlayerDisconnectedEvent.class, new PlayerDisconnectedEventHandler());
        dispatcher.registerHandler(PlayerSendMessageEvent.class, new PlayerSendMessageEventHandler());
        dispatcher.registerHandler(TickEvent.class, new TickEventHandler());

        new TickManager().start();
        new Server().start();
    }
}
