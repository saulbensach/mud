package tick;

import event.AbstractEvent;
import world.World;

public class TickEvent extends AbstractEvent {

    public void tick(){
        World.getInstance().update();
    }
}
