import java.util.ArrayList;

public class Place {
    public int id, area, x, y, lvlMin;
    private String name;
    private ArrayList<Path> paths;

    public Place(int id, int area, int x, int y, int lvlMin, String name) {
        this.id = id;
        this.area = area;
        this.x = x;
        this.y = y;
        this.lvlMin = lvlMin;
        this.name = name;
        this.paths = new ArrayList<>();
    }

    public void addPath(Path path){
        this.paths.add(path);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Place) {
            Place p = (Place) object;
            return p.id == this.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", area=" + area +
                ", x=" + x +
                ", y=" + y +
                ", lvlMin=" + lvlMin +
                ", name='" + name + '\'' +
                ", paths=" + paths +
                '}';
    }
}
