package server;
import javax.jws.*;
/**
 * @author Torri
 */
@WebService(endpointInterface="Server.Server")
public class ServerImpl implements Server{
    @Override
    public void getLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}