package Level;

import Entity.Mob.*;
import java.util.Random;

public class Spawner{
    Random r = new Random();
    int lvl=0;
    public Spawner(int x,int y,int am, int min, int max, Level l,String type){
        if(type.equalsIgnoreCase("Snake")){
            for(int i=0;i<am;i++){
                lvl=(int) (min+(r.nextGaussian()*(max-min)));
                l.addEntity(new Snake(x,y,lvl));
            }
        }else if(type.equalsIgnoreCase("Bandit")){
            for(int i=0;i<am;i++){
                lvl=(int) (min+(r.nextGaussian()*(max-min)));
                l.addEntity(new Bandit(x,y,lvl));
            }
        }else{
            System.err.println("Ya. Wrong type man.");
        }
    }
}
