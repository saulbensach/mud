package world;

import event.EventDispatcher;
import game.Place;
import net.ConsoleColors;
import player.Player;
import player.PlayerSendMessageEvent;

import java.sql.Timestamp;
import java.util.ArrayList;

public class World{

    private static World world = null;

    private ArrayList<Player> players;
    private ArrayList<Place> places;
    private EventDispatcher dispatcher = EventDispatcher.getInstance();

    private World(){
        players = new ArrayList<>();
        places = new WorldLoader().load();
    }

    public static World getInstance(){
        if(world == null){
            world = new World();
        }
        return world;
    }

    public synchronized void addPlayer(Player player){
        players.add(player);
        dispatcher.dispatch(new PlayerSendMessageEvent(player, places.toString()));
    }

    public synchronized void removePlayer(Player player){
        players.remove(player);
    }

    public synchronized void update(){
        /*Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for(Player player : players){
            String payload = ConsoleColors.info("tick from server at " + timestamp.toString());
            dispatcher.dispatch(new PlayerSendMessageEvent(player, payload));
        }*/
    }

    public synchronized void broadcastMessage(String from, String payload){
        String formatted = ConsoleColors.RED_BOLD_BRIGHT + " [ " + from + " ] " + ConsoleColors.info(payload);
        for(Player player : players){
            dispatcher.dispatch(new PlayerSendMessageEvent(player, formatted));
        }
    }

    public synchronized int getPlayerCount(){
        return players.size();
    }
}
