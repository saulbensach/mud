package actions;

import event.AbstractEvent;
import world.World;

public class SayActionEvent extends AbstractEvent {

    private String from;
    private String payload;

    public SayActionEvent(String from, String payload){
        this.from = from;
        this.payload = payload;
    }

    public void broadcast(){
        World.getInstance().broadcastMessage(from, payload);
    }

}
