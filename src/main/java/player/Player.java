package player;

import event.EventDispatcher;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.UUID;

public class Player extends Thread{

    private Socket socket;
    private UUID uuid;

    private BufferedReader in;
    private DataOutputStream out;
    private String payload = "";

    private boolean connected = true;

    public Player(Socket socket){
        this.socket = socket;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        uuid = UUID.randomUUID();
    }

    @Override
    public void run(){
        while(connected){
            try{
                // If socket is closed and we try to read this will dump an error
                payload = in.readLine();
            }catch (IOException e){
                EventDispatcher.getInstance().dispatch(new PlayerDisconnectedEvent(this));
            }
            if(payload == null){
                EventDispatcher.getInstance().dispatch(new PlayerDisconnectedEvent(this));
            }
        }
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
            connected = false;
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Player) {
            Player p = (Player) object;
            return p.uuid == this.uuid;
        }
        return false;
    }
}
