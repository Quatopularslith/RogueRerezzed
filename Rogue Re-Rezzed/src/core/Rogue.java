package core;

import ui.Menu;
import dungeon.Level;
import java.util.ArrayList;
//http://semver.org/
/**
 * This makes it all go!
 * @author Torri
 */
public class Rogue {
    public static Menu mm;
    private static Level l;
    private static ArrayList<Level> levels = new ArrayList<>();
    public static int numLevels=1;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mm = new Menu();
    }
    public static Level setLevel(Level l1){
        l=l1;
        levels.add(l1);
        numLevels=levels.size()+1;
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
        l=null;
    }
}
