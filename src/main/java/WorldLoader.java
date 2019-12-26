import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WorldLoader {

    public World world;

    private ArrayList<Place> places;

    public WorldLoader(ClientManager clientManager){
        places = new ArrayList<>();
        load();
        world = new World(places, clientManager);
    }

    private void load(){
        Gson gson = new Gson();
        try{
            JsonObject json = gson.fromJson(new FileReader("world.json"), JsonObject.class);
            placesCreator((JsonArray) json.get("places"));
            pathCreator((JsonArray) json.get("paths"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void placesCreator(JsonArray json_places){
        for(Object json_place : json_places){
            JsonObject json = (JsonObject) json_place;
            Place p = new Place(
                    json.get("id").getAsInt()
                    , json.get("a").getAsInt()
                    , json.get("x").getAsInt()
                    , json.get("y").getAsInt()
                    , json.get("lvlMin").getAsInt()
                    , json.get("n").getAsString()
            );
            places.add(p);
        }
    }

    private void pathCreator(JsonArray json_paths){
        for(Object json_path: json_paths){
            JsonObject json = (JsonObject) json_path;
            Path path = new Path(json.get("e").getAsString(), json.get("p").getAsInt());
            Place place = findPlace(path.id);
            if(place != null){
                place.addPath(path);
            }
        }
    }

    private Place findPlace(int id){
        for(Place place : places){
            if(place.id == id) {
                return place;
            }
        }
        return null;
    }
}
