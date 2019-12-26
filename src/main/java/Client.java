import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.UUID;

public class Client extends Thread{

    public ClientManager clientManager;
    public Socket socket;
    public UUID uuid;

    private BufferedReader reader;
    private DataOutputStream out;
    private String payload = "";

    public Client(Socket socket, ClientManager clientManager){
        this.clientManager = clientManager;
        this.socket = socket;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        uuid = UUID.randomUUID();
    }

    @Override
    public void run(){
        try {
            out.writeUTF(World.places.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (socket.isConnected()){
            try{
                payload = reader.readLine();
            }catch (IOException e){
                e.printStackTrace();
                break;
            }

            if(payload == null){
                break;
            }
            System.out.println("Payload: " + payload);
        }
        clientManager.removeClient(this);
    }

    public void send(String payload){
        try{
            out.writeUTF(payload);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof Client) {
            Client c = (Client) object;
            return c.uuid == this.uuid;
        }
        return false;
    }
}
