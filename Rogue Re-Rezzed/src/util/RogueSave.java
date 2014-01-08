/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import dungeon.Level;
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
    private final String[] playerprops = {"x", "y", "xp", "lvl", "mana", "kills", "health", "gold","numd"};
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
                rs=new File(paths+sep+"Player.txt");
                r=new File(paths+sep+"Rooms.txt");
                String[] s = new String[10];
                for(int i=0;i<s.length;i++){
                    s[i]="inv"+i+"x";
                }
                String[] playersets = {l.getPlayer().x+"",l.getPlayer().y+"",l.getPlayer().xp+"",l.getPlayer().lvl+"",l.getPlayer().mana+"",l.getPlayer().kills+"",l.getPlayer().health+"",l.getPlayer().gold+"",l.lvl+""};
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
        System.out.println("Loading....");
        rs=new File(paths+sep+"Player.txt");
        r=new File(paths+sep+"Rooms.txt");
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
        split=arr.get(0).split(" ");
        System.out.println("Split done");
        b = new boolean[out.board.length][out.board[0].length];
        for(boolean[] ba:b){
            for(boolean bb:ba){
                bb=true;
            }
        }
        for(int i=0;i<out.board.length;i++){
            for(int j=0;j<out.board[0].length;j++){
                b[i][j]=split[j].equals("1");
            }
        }
        System.out.println("World Loaded");
        out.board=b;
        Player play = new Player(out);
        String[] s = new String[playerprops.length];
        try(FileInputStream inStream = new FileInputStream(rs)){
            p = new Properties();
            p.load(inStream);
            for(int k=0;k<playerprops.length;k++){
                if (p.getProperty(playerprops[k]) == null) {
                    p.setProperty(playerprops[k], "0");
                }
                s[k]=p.getProperty(playerprops[k]);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        play.x=Integer.parseInt(s[0]);
        play.y=Integer.parseInt(s[1]);
        play.xp=Double.parseDouble(s[2]);
        play.lvl=Integer.parseInt(s[3]);
        play.mana=Double.parseDouble(s[2]);
        play.kills=Integer.parseInt(s[5]);
        play.health=Float.parseFloat(s[6]);
        play.gold=Integer.parseInt(s[7]);
        out.lvl=Integer.parseInt(s[8]);
        System.out.println("Player Played");
        out.setPlayer(play);
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