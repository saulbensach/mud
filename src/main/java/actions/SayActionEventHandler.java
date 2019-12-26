package actions;

import event.Handler;

public class SayActionEventHandler implements Handler<SayActionEvent> {
    @Override
    public void onEvent(SayActionEvent event) {
        event.broadcast();
    }
}
