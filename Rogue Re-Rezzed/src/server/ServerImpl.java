package server;
import dungeon.Level;
import javax.jws.WebService;
/**
 * 
 * @author Torri
 */
@WebService(endpointInterface="Server.Server")
public class ServerImpl implements Server{
    public static Level l = new Level();
    int currp;
    int maxp;
    public ServerImpl(int maxp){
        currp=0;
        System.out.println("Started Server with "+maxp+" Maximum Players");
        this.maxp=maxp;
    }
    @Override
    public Level getLevel() {
        return l;
    }
    @Override
    public void keyPressed(boolean[] keybinds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int joinGame() throws ServerFullException{
        if(currp+1>maxp){
            throw new ServerFullException();
        }else{
            currp++;
        }
        return maxp;
    }
}