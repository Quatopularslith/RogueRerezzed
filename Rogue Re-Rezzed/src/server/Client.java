
package server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Torri
 */
@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface Client {
    @WebMethod void startGame();
    @WebMethod void update();
}
