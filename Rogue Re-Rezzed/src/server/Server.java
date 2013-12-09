package server;
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
    @WebMethod void getLevel();
}
