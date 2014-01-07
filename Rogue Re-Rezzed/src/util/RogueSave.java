/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Mnenmenth
 */
public class RogueSave {
    private final File rs;
    private final File r;
    private Properties p;
    private final String sep = File.separator;
    public RogueSave(dungeon.Level l){
        File path = new File("RogueRerezzed");
        path.mkdirs();
        rs=new File("RogueRerezzed"+sep+"Player.txt");
        r=new File("RogueRerezzed"+sep+"Rooms.txt");
        String[] s = new String[10];
        for(int i=0;i<s.length;i++){
            s[i]="inv"+i+"x";
        }
        String[] playerprops = {"x", "y", "xp", "lvl", "mana", "kills", "health", "gold", "rep"};
        String[] playersets = {l.getPlayer().x,l.getPlayer().y,l.getPlayer().xp,l.getPlayer().lvl,l.getPlayer().mana,l.getPlayer().kills,l.getPlayer().gold};
        try {
            if(rs.createNewFile()){
                for (String prop : playerprops) {
                    if(prop==null){
                        p.setProperty(prop, "0");
                    }
                }
            }
            try (FileInputStream inStream = new FileInputStream(rs)) {
                p = new Properties();
                p.load(inStream);
                for (String prop : playerprops) {
                    if (p.getProperty(prop) == null) {
                        p.setProperty(prop, "0");
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}