import java.net.Socket;
import java.util.ArrayList;

public class ClientManager {
    public ArrayList<Client> clients;

    public ClientManager(){
        clients = new ArrayList<>();
    }

    public void addClient(Socket socket){
        Client client = new Client(socket, this);
        client.start();
        clients.add(client);
    }

    public void removeClient(Client client){
        clients.remove(client);
        System.out.println("Client disconnected!");
    }

    public int count(){
        return clients.size();
    }
}
