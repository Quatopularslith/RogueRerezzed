package Level;

public class Spawner{
    Random r = new Random();
    int lvl=0;
    public Spawner(int x,int y,int am, int min, int max, Level l){
        for(int i=0;i<am;i++){
            lvl=min+(r.nextGaussian()*(max-min));
            l.addEntity();
        }
    }
}
