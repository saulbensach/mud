public class Server {

    public static boolean RUNNING = true;

    private Server(){
        ConnectionManager connectionManager = new ConnectionManager();
        WorldLoader worldLoader = new WorldLoader(connectionManager.clientManager);
        new FixedUpdate(worldLoader.world).start();
        connectionManager.start();
    }

    public static void main(String[] args){
        new Server();
    }
}
