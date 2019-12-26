package world;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import game.Path;
import game.Place;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WorldLoader {

    public ArrayList<Place> load(){
        ArrayList<Place> places = new ArrayList<>();
        Gson gson = new Gson();
        try{
            JsonObject json = gson.fromJson(new FileReader("world.json"), JsonObject.class);
            placesCreator(places, (JsonArray) json.get("places"));
            pathCreator(places, (JsonArray) json.get("paths"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return places;
    }

    private void placesCreator(ArrayList<Place> places, JsonArray json_places){
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

    private void pathCreator(ArrayList<Place> places, JsonArray json_paths){
        for(Object json_path: json_paths){
            JsonObject json = (JsonObject) json_path;
            Path path = new Path(json.get("e").getAsString(), json.get("p").getAsInt());
            Place place = findPlace(places, path.id);
            if(place != null){
                place.addPath(path);
            }
        }
    }

    private Place findPlace(ArrayList<Place> places, int id){
        for(Place place : places){
            if(place.id == id) {
                return place;
            }
        }
        return null;
    }
}
