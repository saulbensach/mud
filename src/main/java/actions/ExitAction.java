package actions;

import event.EventDispatcher;
import player.Player;
import player.PlayerDisconnectedEvent;

public class ExitAction extends Action {

    public ExitAction(Player player) {
        super(player,"exit", new String[]{"exit"});
    }

    @Override
    public void perform(String args) {
        EventDispatcher.getInstance().dispatch(new PlayerDisconnectedEvent(player));
    }
}
