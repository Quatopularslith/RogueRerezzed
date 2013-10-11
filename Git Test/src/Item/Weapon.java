package Item;

import java.util.Random;

/**
 * @author Torri
 */
public class Weapon extends Item{
    private Random rand = new Random();
    public int mod;
    public String modName;
    public int type;//0=sword
    public Weapon(){
        mod=rand.nextInt(10)-5;
        if(mod<0){
            modName = "Weak";
        }else if(mod==0){
            modName = null;
        }else if(mod>0){
            if(type==0){
                modName="Sharp";
            }else{
                modName="Strong";
            }
        }
    }
}
