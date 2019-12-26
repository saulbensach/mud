package player;

import event.AbstractEvent;

public class PlayerSendMessageEvent extends AbstractEvent {

    private Player player;
    private String payload;

    public PlayerSendMessageEvent(Player player, String payload){
        this.player = player;
        this.payload = payload;
    }

    public void sendMessage(){
        player.send(payload);
    }
}
