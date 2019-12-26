package actions;

import event.EventDispatcher;
import player.Player;

public class SayAction extends Action{

    public SayAction(Player player) {
        super(player,"say", new String[]{"s", "say"});
    }

    public void perform(String args){
        if(args.length() > 1){
            EventDispatcher.getInstance().dispatch(new SayActionEvent(player.name, args));
        }
    }
}
