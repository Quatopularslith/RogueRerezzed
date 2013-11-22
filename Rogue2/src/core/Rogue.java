package core;

import ui.Menu;
import dungeon.Level;
import loading.Loading;

/**
 * @version 0.1
 * @author Torri
 */
public class Rogue {
    public static Menu mm;
    private static Level l;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Loading l = new Loading(750);
    }
    public static Level setLevel(Level l1){
        System.out.println("New World "+Level.numLevels);
        l=l1;
        return l;
    }
    public static Level getLevel(){
        return l;
    }
}
