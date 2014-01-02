package core;

import dungeon.Level;
import dungeon.LevelMode;
import dungeon.LevelType;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
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
    public static int numLevels=0;
    private static int hs=0;
    private static int n=0;
    private static int d=0;
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
        l=l1;
        levels.add(l);
    }
    public static void resetLevels(){
        if(numLevels>hs) hs=numLevels;
        if(numLevels>0) n+=numLevels;
        if(numLevels>0) d+=1;
        levels.clear();
        numLevels=0;
    }
    public static void sendStats(){
        resetLevels();
//        System.out.println("Stats:");
//        System.out.println("n="+n);
//        System.out.println("d="+d);
//        System.out.println("hs="+hs);
//        try {
//            URL url = new URL("http://eyeris.zapto.org:3000/HighScore?wsdl");
//            QName qname = new QName("http://hs/","ServerImplService");
//            Service s = Service.create(url, qname);
//            hs.Server s1 = s.getPort(hs.Server.class);
//            s1.addStats(n,d,hs);
//        } catch (Exception ex) {
//            try {
//                System.out.println("not by inet");
//                URL url = new URL("http://192.168.1.35:3000/HighScore?wsdl");
//                QName qname = new QName("http://hs/","ServerImplService");
//                Service s = Service.create(url, qname);
//                hs.Server s1 = s.getPort(hs.Server.class);
//                s1.addStats(n,d,hs);
//            } catch (Exception ex1) {
//                try {
//                    System.out.println("not by lan");
//                    URL url = new URL("http://127.0.0.1:3000/HighScore?wsdl");
//                    QName qname = new QName("http://hs/","ServerImplService");
//                    Service s = Service.create(url, qname);
//                    hs.Server s1 = s.getPort(hs.Server.class);
//                    s1.addStats(n,d,hs);
//                } catch (Exception ex2) {
//                    System.out.println("not by localhost");
//                }
//            }
//        }
    }
}
