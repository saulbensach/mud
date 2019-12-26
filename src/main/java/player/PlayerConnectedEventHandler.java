package player;

import event.Handler;
import player.PlayerConnectedEvent;

public class PlayerConnectedEventHandler implements Handler<PlayerConnectedEvent> {
    @Override
    public void onEvent(PlayerConnectedEvent event) {
        event.add();
    }
}
