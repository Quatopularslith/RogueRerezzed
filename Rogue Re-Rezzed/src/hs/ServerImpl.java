package hs;
import javax.jws.WebService;
/**
 * 
 * @author Torri
 */
@WebService(endpointInterface="hs.Server")
public class ServerImpl implements Server{
    int highscore = 0;
    @Override
    public void highScore(int numLevel) {
        if(numLevel>highscore) highscore=numLevel;
        System.out.println("Comparing "+numLevel+" and "+highscore);
    }
    @Override
    public int getHighScore() {
        System.out.println("Getting Highscore");
        return highscore;
    }
}