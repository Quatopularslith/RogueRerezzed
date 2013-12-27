
package server;

import core.Rogue;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Torri
 */
public class ClientImpl implements Client{
    Server s1;
    public ClientImpl(String ip,String port){
        try {
            URL url = new URL("http://"+ip+":"+port+"/RogueRerezzed?wsdl");
            QName qname = new QName("http://hs/","ServerImplService");
            Service s = Service.create(url, qname);
            s1 = s.getPort(Server.class);
            s1.joinGame();
        } catch (Exception ex) {
            System.out.println("Unnable to join game: "+ex.toString());
        }
    }
    @Override
    public void startGame() {
        Rogue.setLevel(s1.getLevel());
    }
    @Override
    public void update() {
        Rogue.mm.gp.update();
    }
}
