import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager extends Thread{
    private static final int PORT = 4000;
    private ServerSocket serverSocket;
    public ClientManager clientManager;

    public ConnectionManager(){
        clientManager = new ClientManager();
        try{
            serverSocket = new ServerSocket(PORT);
        }catch (IOException e){
            e.printStackTrace();
            Server.RUNNING = false;
        }

    }

    @Override
    public void run(){
        while(Server.RUNNING){
            try{
                Socket socket = serverSocket.accept();
                clientManager.addClient(socket);
                System.out.println("Clients connected: " + clientManager.count());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
