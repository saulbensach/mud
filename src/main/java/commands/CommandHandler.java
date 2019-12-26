package commands;

import commands.actions.Action;
import commands.actions.ExitAction;
import commands.actions.InfoAction;
import net.Client;

import java.util.HashMap;

public class CommandHandler {

    private HashMap<String, Action> actions;
    private Client client;

    public CommandHandler(Client client){
        this.client = client;
        actions = new HashMap<>();
        actions.put("exit", new ExitAction(client));
        actions.put("info", new InfoAction(client));
    }

    public void handle(String payload){
        String[] command = payload.split(" ", 2);
        String args = "";
        if(command.length > 1){
            args = command[1];
        }
        Action action = actions.get(command[0]);
        if(action == null){
            client.send(ConsoleColors.warning("What are you saying?"));
        } else {
            actions.get(command[0]).run(args);
        }
    }

}
