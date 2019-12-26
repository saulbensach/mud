import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread{

    Socket socket;
    DataOutputStream out;
    BufferedReader reader;

    String server_payload = "";

    public Client(){
        try{
            socket = new Socket("localhost", 4000);
            out = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while(true){
            try {
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeBytes("this is a call to the server :D \n");
                out.flush();
                Thread.sleep(1000);
            }catch (InterruptedException | IOException e2){
                e2.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new Client().start();
    }
}
