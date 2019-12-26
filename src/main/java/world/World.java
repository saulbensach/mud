package world;

import player.Player;

import java.util.ArrayList;

public class World{

    private static World world = null;

    private ArrayList<Player> players;

    private World(){
        players = new ArrayList<>();
    }

    public static World getInstance(){
        if(world == null){
            world = new World();
        }
        return world;
    }

    public synchronized void addPlayer(Player player){
        players.add(player);
    }

    public synchronized void removePlayer(Player player){
        players.remove(player);
    }

    public synchronized void update(){
        for(Player player : players){
            player.send("tick from server!");
        }
    }
}
