import java.util.ArrayList;

public class World {
    public static ArrayList<Place> places;
    ClientManager clientManager;

    public World(ArrayList<Place> places, ClientManager clientManager){
        this.places = places;
        this.clientManager = clientManager;
    }

    public void update(){
        for(Client client : clientManager.clients){
            client.send("update :D");
        }
    }
}
