package hs;
import javax.xml.ws.Endpoint;
/**
 * @author Torri
 */
public class ServerPublisher {
    public static void go(){
        ServerImpl si = new ServerImpl();
        System.out.println("Started on 127.0.0.1:5335");
        Endpoint.publish("http://127.0.0.1:5335/HighScore", si);
    }
}
