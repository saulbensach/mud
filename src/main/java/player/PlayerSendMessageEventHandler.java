package player;

import event.Handler;

public class PlayerSendMessageEventHandler implements Handler<PlayerSendMessageEvent> {

    @Override
    public void onEvent(PlayerSendMessageEvent event) {
        event.sendMessage();
    }
}
