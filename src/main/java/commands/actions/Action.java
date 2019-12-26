package commands.actions;

import net.Client;

public abstract class Action {

    protected Client client;

    public Action(Client client){
        this.client = client;
    }

    public abstract void run(String action);
}
