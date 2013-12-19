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
    public RogueSave(dungeon.Level l){
        rs=new File("Player.txt");
        r=new File("Rooms.txt");
        String[] s = new String[10];
        for(int i=0;i<s.length;i++){
            s[i]="inv"+i+"x";
        }
        String[] playerprops = {"x", "y", "xp", "xplevels", "mana", "kills", "health", "gold", "rep"};
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
    

