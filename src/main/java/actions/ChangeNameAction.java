package actions;

import event.EventDispatcher;
import net.ConsoleColors;
import player.Player;
import player.PlayerSendMessageEvent;

public class ChangeNameAction extends Action {
    public ChangeNameAction(Player player) {
        super(player, "name", new String[]{"name"});
    }

    @Override
    public void perform(String args) {
        player.changeName(args);
        EventDispatcher.getInstance().dispatch(new PlayerSendMessageEvent(player, ConsoleColors.info("Name changed!")));
    }
}
