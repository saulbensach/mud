package commands.actions;

import commands.ConsoleColors;
import net.Client;

public class InfoAction extends Action {

    public InfoAction(Client client) {
        super(client);
    }

    @Override
    public void run(String action) {
        client.send(ConsoleColors.info("you are yourself :D"));
    }
}
