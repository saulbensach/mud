public class Path {
    public String exit;
    public int id;

    public Path(String exit, int id){
        this.exit = exit;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Path{" +
                "exit='" + exit + '\'' +
                ", id=" + id +
                '}';
    }
}
