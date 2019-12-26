package actions;

import player.Player;

public abstract class Action {
    protected Player player;

    private String key;
    private String[] aliases;

    public Action(Player player, String key, String[] aliases){
        this.key = key;
        this.aliases = aliases;
        this.player = player;
    }

    public abstract void perform(String args);
}
