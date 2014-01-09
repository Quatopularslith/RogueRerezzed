/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import dungeon.Level;
import dungeon.LevelMode;
import dungeon.LevelType;
import entity.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Mnenmenth
 */
public class RogueSave {
    private File rs;
    private File r;
    private Properties p;
    private final String sep = File.separator;
    private final String paths;
    private final String[] playerprops = {"x", "y", "xp", "lvl", "mana", "kills", "health", "gold","numd","lvl0","id0","modid0","lvl1","id1","modid1","lvl2","id2","modid2","lvl3","id3","modid3","lvl4","id4","modid4","lvl5","id5","modid5","lvl6","id6","modid6","lvl7","id7","modid7","lvl8","id8","modid8","lvl9","id9","modid9"};
    public RogueSave(int savenum){
        paths="RogueRerezzed"+sep+"Saves"+sep+"save"+savenum;
    }
    public void saveLevel(final Level l){
        System.out.println("saving...");
        new Thread(){
            @Override
            public void run(){
                File path = new File(paths);
                path.mkdirs();
                rs=new File(paths+sep+"Level.txt");
                r=new File(paths+sep+"World.txt");
                String[] s = new String[10];
                for(int i=0;i<s.length;i++){
                    s[i]="inv"+i+"x";
                }
                String[] playersets = {l.getPlayer().x+"",l.getPlayer().y+"",l.getPlayer().xp+"",l.getPlayer().lvl+"",l.getPlayer().mana+"",l.getPlayer().kills+"",l.getPlayer().health+"",l.getPlayer().gold+"",l.lvl+"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
                int num=0;
                for(int i=9;i<playersets.length;i+=3){
                    playersets[i]=l.getPlayer().inv[num].lvl+"";
                    playersets[i+1]=l.getPlayer().inv[num].id+"";
                    playersets[i+2]=l.getPlayer().inv[num].modifierid+"";
                    num++;
                }
                try {
                    rs.createNewFile();
                    p = new Properties();
                    for (int i=0;i<playerprops.length;i++) {
                        p.setProperty(playerprops[i], playersets[i]);
                    }
                    save(rs);
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
                try {
                    r.createNewFile();
                    try (FileOutputStream outStream = new FileOutputStream(r)) {
                        String data = "";
                        for (boolean[] board : l.board) {
                            for (boolean bo : board) {
                                data += bo ? 1 : 0;
                                data += " ";
                            }
                            data+="\n";
                            outStream.write(data.getBytes());
                            data="";
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
                System.out.println("saved");
            }
        }.start();
    }
    public Level loadLevel(){
        System.out.println("Loading Save "+paths);
        rs=new File(paths+sep+"Level.txt");
        r=new File(paths+sep+"World.txt");
        Level out = new Level();
        boolean[][] b;
        List<String> arr=new ArrayList<>();
        String[] split;
        try {
            rs.createNewFile();
            r.createNewFile();
            arr=Files.readAllLines(r.toPath(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return null;
        }
        b = new boolean[out.board.length][out.board[0].length];
        for(boolean[] ba:b){
            for(boolean bb:ba){
                bb=true;
            }
        }
        for(int i=0;i<out.board.length;i++){
            split=arr.get(i).split(" ");
            for(int j=0;j<out.board[0].length;j++){
                b[i][j]=split[j].equals("1");
            }
        }
        String[] s = new String[playerprops.length];
        try(FileInputStream inStream = new FileInputStream(rs)){
            p = new Properties();
            p.load(inStream);
            for(int k=0;k<playerprops.length;k++){
                s[k]=p.getProperty(playerprops[k]);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        out.lvl=Integer.parseInt(s[8]);
        out.generateLevel(100, 100, out.lvl, LevelMode.STORY, LevelType.TURN, 0);
        Player play = new Player(out);
        play.x=Integer.parseInt(s[0]);
        play.y=Integer.parseInt(s[1]);
        play.xp=Double.parseDouble(s[2]);
        play.lvl=Integer.parseInt(s[3]);
        play.mana=Double.parseDouble(s[4]);
        play.kills=Integer.parseInt(s[5]);
        play.health=Float.parseFloat(s[6]);
        play.gold=Integer.parseInt(s[7]);
        int num=0;
        for(int i=9;i<s.length;i+=3){
            play.inv[num].makeItem(Integer.parseInt(s[i]), Integer.parseInt(s[i+1]), Integer.parseInt(s[i+2]), play);
        }
        play.updateStats();
        out.setPlayer(play);
        out.board=b;
        out.rePopulate();
        System.out.println("Done");
        return out;
    }
    private void save(File s){
        try {
            try (FileOutputStream outs = new FileOutputStream(s)) {
                p.store(outs,"Properties settings");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}