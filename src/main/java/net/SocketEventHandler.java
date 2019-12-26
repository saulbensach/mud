package net;

import event.Handler;

public class SocketEventHandler implements Handler<SocketEvent> {
    @Override
    public void onEvent(SocketEvent event) {
        event.accept();
    }
}
