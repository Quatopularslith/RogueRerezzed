
package client;

import dungeon.Level;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import server.Server;

/**
 *
 * @author Torri
 */
public class Client {
    public static Level l;
    public Client(String ip,String port){
        try {
            URL url = new URL("http://"+ip+":"+port+"/RogueRerezzed?wsdl");
            QName qname = new QName("http://server/","ServerImpl");
            Service service = Service.create(url, qname);
            Server s = service.getPort(Server.class);
            l = s.getLevel();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
}
