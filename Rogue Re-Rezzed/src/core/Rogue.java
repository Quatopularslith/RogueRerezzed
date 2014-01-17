package core;

import dungeon.Level;
import dungeon.LevelMode;
import dungeon.LevelType;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import ui.Menu;
//http://semver.org/
/**
 * This makes it all go!
 * @author Torri
 */
public class Rogue {
    public static Menu mm;
    private static final ArrayList<Level> levels = new ArrayList<>();
    private static Level l;
    public static int numLevels=1;
    public static int renderlevel=0;
    private static int hs=0;
    private static int n=0;
    private static int d=0;
    private static boolean loaded=false;
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
    public static void setLevel(Level l1){
        loaded=true;
        numLevels=l1.lvl;
        l=l1;
        levels.add(l);
    }
    public static void resetLevels(){
        if(loaded){
            if(numLevels>hs) hs=numLevels;
            if(numLevels>0) n+=numLevels;
        }else{
            if(numLevels>hs) hs=numLevels;
            if(numLevels>0) n+=numLevels;
            if(numLevels>0) d+=1;
        }
        loaded=false;
        levels.clear();
        numLevels=0;
    }
    public static void sendStats(){
        resetLevels();
        new Thread("Sending Stats"){
            @Override
            public void run(){
                try {
                    DatagramSocket socket = new DatagramSocket(3000);
                    InetAddress ip = InetAddress.getByName("localhost");
                    String send = n+" "+d+" "+hs;
                    byte[] data = send.getBytes();
                    DatagramPacket packet = new DatagramPacket(data,data.length,ip,3000);
                    socket.send(packet);
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
            }
        }.start();
    }//10.1.122.233
}
