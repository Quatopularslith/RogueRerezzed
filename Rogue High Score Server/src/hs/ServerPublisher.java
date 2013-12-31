package hs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.ws.Endpoint;
/**
 * @author Torri
 */
public class ServerPublisher {
    public static void go(){
        try{
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            String ip = in.readLine();
            ServerImpl si = new ServerImpl();
            System.out.println(ip);
            Endpoint.publish("http://127.0.0.1/HighScore", si);
        } catch(Exception e){
            System.err.println(e.toString());
        }
    }
}
