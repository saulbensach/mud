package player;

import actions.Action;
import actions.ChangeNameAction;
import actions.ExitAction;
import actions.SayAction;
import event.EventDispatcher;
import net.ConsoleColors;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

public class Player extends Thread{

    private Socket socket;
    private UUID uuid;

    private BufferedReader in;
    private DataOutputStream out;
    private String payload = "";
    public String name;

    private boolean connected = true;

    private HashMap<String, Action> actions;

    public Player(Socket socket){
        this.socket = socket;
        name = socket.getInetAddress().toString();
        actions = new HashMap<>();
        actions.put("say", new SayAction(this));
        actions.put("exit", new ExitAction(this));
        actions.put("name", new ChangeNameAction(this));
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
            commandsHandler(payload);
        }
    }

    public void changeName(String name){
        this.name = name;
    }

    private void commandsHandler(String payload){
        String[] command = payload.split(" ", 2);
        String args = "";
        Action action = actions.get(command[0]);
        if(action == null){
            EventDispatcher.getInstance().dispatch(new PlayerSendMessageEvent(this, ConsoleColors.warning("wrong command!")));
        } else if(command.length > 1){
            args = command[1];
            action.perform(args);
        } else {
            action.perform("");
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
            out.close();
            in.close();
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
