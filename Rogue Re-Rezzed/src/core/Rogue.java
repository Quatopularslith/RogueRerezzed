package core;

import ui.Menu;
import dungeon.Level;
import dungeon.LevelMode;
import dungeon.LevelType;
import java.util.ArrayList;
//http://semver.org/
/**
 * This makes it all go!
 * @author Torri
 */
public class Rogue {
    public static Menu mm;
    private static final ArrayList<Level> levels = new ArrayList<>();
    private static Level l;
    public static int numLevels=0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mm = new Menu();
    }
    public static Level setLevel(LevelMode mode1,LevelType type1,int render1){
        numLevels++;
        l=new Level(numLevels,mode1,type1,render1);
        levels.add(l);
        return l;
    }
    public static Level getCurrentLevel(){
        return l;
    }
    public static Level getLastLevel(){
        Level l1;
        try{
            l1=levels.get(levels.size()-1);
        }catch(Exception ex){
            return null;
        }
        return l1;
    }
    public static void resetLevels(){
        levels.clear();
        numLevels=0;
    }
}
