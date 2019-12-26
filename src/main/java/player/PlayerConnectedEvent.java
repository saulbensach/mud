package player;

import event.AbstractEvent;
import world.World;

import java.net.Socket;

public class PlayerConnectedEvent extends AbstractEvent {

    public Socket socket;

    public PlayerConnectedEvent(Socket socket){
        this.socket = socket;
    }

    public void add(){
        World world = World.getInstance();
        Player player = new Player(socket);
        player.start();
        world.addPlayer(player);
        System.out.println("Players connected: " + world.getPlayerCount());
    }
}
