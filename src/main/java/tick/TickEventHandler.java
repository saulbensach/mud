package tick;

import event.Handler;

public class TickEventHandler implements Handler<TickEvent> {
    @Override
    public void onEvent(TickEvent event) {
        event.tick();
    }
}
