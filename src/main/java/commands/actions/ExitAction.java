package commands.actions;

import net.Client;

public class ExitAction extends Action {

    public ExitAction(Client client){
        super(client);
        this.client = client;
    }

    @Override
    public void run(String action) {
        client.disconnect();
    }
}
