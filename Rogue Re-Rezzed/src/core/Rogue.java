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
    private static ArrayList<Level> levels = new ArrayList<>();
    public static int numLevels=1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mm = new Menu();
    }
    public static Level setLevel(LevelMode mode1,LevelType type1,int render1){
        levels.add(new Level(numLevels,mode1,type1,render1));
        numLevels++;
        System.out.println(numLevels);
        return levels.get(levels.size());
    }
    public static Level getCurrentLevel(){
        return levels.get(levels.size());
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
        numLevels=1;
    }
}
