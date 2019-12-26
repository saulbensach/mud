package player;

import event.AbstractEvent;
import world.World;

public class PlayerDisconnectedEvent extends AbstractEvent {

    private Player player;

    public PlayerDisconnectedEvent(Player player){
        this.player = player;
    }

    public void disconnect(){
        World world = World.getInstance();
        world.removePlayer(player);
        player.disconnect();
    }

}
