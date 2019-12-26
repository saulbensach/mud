package net;

import game.World;
import game.WorldLoader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server extends Thread{
    private static final int PORT = 4000;
    private final List<Client> clients;
    private ServerSocket serverSocket;
    private World world;

    private Server(){
        clients = Collections.synchronizedList(new ArrayList<>());
        world = new World(clients, new WorldLoader().load());
        world.start();
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("net.Server listening port: " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(!serverSocket.isClosed()){
            try {
                Socket socket = serverSocket.accept();
                synchronized (clients){
                    Client client = new Client(this, socket);
                    client.start();
                    clients.add(client);
                    System.out.println("Clients connected: "+ clients.size());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeClient(Client client){
        synchronized (clients){
             clients.remove(client);
        }
        System.out.println("net.Client disconnected");
    }

    public static void main(String[] args){
        new Server().start();
    }
}
