package server;
import dungeon.Level;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**
 * @author Torri
 */
@WebService
@SOAPBinding(style=Style.RPC)
public interface Server {
    @WebMethod Level getLevel();
    @WebMethod void keyPressed(boolean[] keybinds);
    @WebMethod int joinGame() throws ServerFullException;
}
