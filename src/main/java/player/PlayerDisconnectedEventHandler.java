package player;

import event.Handler;

public class PlayerDisconnectedEventHandler implements Handler<PlayerDisconnectedEvent> {
    @Override
    public void onEvent(PlayerDisconnectedEvent event) {
        event.disconnect();
    }
}
