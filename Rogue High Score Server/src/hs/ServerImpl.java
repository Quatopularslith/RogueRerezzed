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
    double avg = 0;
    ServerProps sp;
    String[] props = {"n","d","hs"};
    public ServerImpl(){
        sp = new ServerProps("Stats.txt",props);
        n=Integer.parseInt(sp.getData()[0]);
        d=Integer.parseInt(sp.getData()[1]);
        highscore=Integer.parseInt(sp.getData()[2]);
    }
    @Override
    public void highScore(int numLevel) {
        if(numLevel==0) return;
        n+=numLevel;
        d++;
        avg=(n/d);
        System.out.println("Average: "+avg);
        if(numLevel>highscore){
            highscore=numLevel;
            System.out.println("HIGHSCORE: "+highscore);
        }
        System.out.println("-------------");
    }
    @Override
    public int getHighScore() {
        System.out.println("Getting Highscore");
        dispStats();
        System.out.println("-------------");
        return highscore;
    }
    @Override
    public void addStats(int n, int d, int hs) {
        this.n+=n;
        this.d+=d;
        avg=(n/d);
        if(hs>highscore){
            highscore=hs;
            System.out.println("HIGHSCORE: "+highscore);
        }
        dispStats();
        saveStats();
        System.out.println("-------------");
    }
    private void dispStats(){
        System.out.println("Average Level: "+highscore);
        System.out.println("Number of Levels played: "+d);
    }
    private void saveStats(){
        String[] data = {n+"",d+"",highscore+""};
        sp.setData(data);
    }
}