package hs;
import javax.jws.WebService;
/**
 * 
 * @author Torri
 */
@WebService(endpointInterface="hs.Server")
public class ServerImpl implements Server{
    int highscore = 0;
    int n = 0;
    int d = 0;
    @Override
    public void highScore(int numLevel) {
        n+=numLevel;
        d++;
        if(numLevel>highscore) highscore=numLevel;
        System.out.println("Average: "+(n/d));
    }
    @Override
    public int getHighScore() {
        System.out.println("Getting Highscore");
        return highscore;
    }
    @Override
    public boolean connect() {
        return true;
    }
}