package game;

import commands.ConsoleColors;
import net.Client;

import java.util.ArrayList;
import java.util.List;

public class World extends Thread{

    private final ArrayList<Place> places;
    private final List<Client> clients;

    private static final int TICK_RATE = 2;
    private static final double ns = 1000000000f / TICK_RATE;

    private long last_time = System.nanoTime();
    private double delta_time = 0;

    public World(List<Client> clients, ArrayList<Place> places){
        this.clients = clients;
        this.places = places;
    }

    @Override
    public void run(){
        while(true){
            long now = System.nanoTime();
            delta_time += ((now - last_time) / ns);
            if(delta_time >= 1){
                update();
                delta_time--;
            }
            last_time = now;
        }
    }

    private void update(){
        synchronized (clients) {
            for(Client client : clients){
                //client.send(ConsoleColors.CYAN_BRIGHT + "Update!" + ConsoleColors.RESET);
            }
        }
    }
}
