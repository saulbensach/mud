package net;

import commands.CommandHandler;
import commands.ConsoleColors;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.UUID;

public class Client extends Thread{

    private Server server;
    private Socket socket;
    private UUID uuid;

    private BufferedReader reader;
    private DataOutputStream out;
    private String payload = "";
    private CommandHandler commandHandler;

    public Client(Server server, Socket socket){
        this.server = server;
        this.socket = socket;
        commandHandler = new CommandHandler(this);
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        uuid = UUID.randomUUID();
    }

    @Override
    public void run(){
        send("");
        send(ConsoleColors.welcome());
        while (socket.isConnected()){
            try{
                // If socket is closed and we try to read this will dump an error
                payload = reader.readLine();
            }catch (IOException e){
                //e.printStackTrace();
                break;
            }
            if(payload == null){
                break;
            }
            commandHandler.handle(payload);
        }
        server.removeClient(this);
    }

    public synchronized void send(String payload){
        try{
            out.writeBytes(payload);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void disconnect(){
        try{
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Client) {
            Client c = (Client) object;
            return c.uuid == this.uuid;
        }
        return false;
    }
}
