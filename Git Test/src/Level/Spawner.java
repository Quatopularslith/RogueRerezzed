package Level;

import Entity.Mob.*;
import java.util.Random;

public class Spawner{
    Random r = new Random();
    int lvl=0;
    String s;
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
    public Spawner(int x, int y, int am, int min, int max, Level l, int t){
        if(t==0){
            s="Snake";
        }else if(t==1){
            s="Bandit";
        }else{
            System.err.prinln("WRONG TYPE AKLHNLKFHADKLSHFKLNASDKLFHNSLAKNF");
        }
        this(x,y,am,min,max,l,s);
    }
}
