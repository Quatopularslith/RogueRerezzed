package server;
/**
 * @author Torri
 */
public class ServerFullException extends Exception{
    public ServerFullException(){
        super("This server is full");
    }
    public ServerFullException(int maxPlayers){
        super("Only "+maxPlayers+" players allowed on this server!");
    }
}
