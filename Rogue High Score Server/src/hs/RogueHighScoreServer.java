
package hs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

/**
 *
 * @author Torri
 */
public class RogueHighScoreServer {
    static boolean running = true;
    static int n;
    static int d;
    static int hs;
    static double avg;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int port;
        String[] s1 = {"n","d","hs"};
        final ServerProps sp = new ServerProps("ServerLog.txt",s1);
        n+=Integer.parseInt(sp.getData()[0]);
        d+=Integer.parseInt(sp.getData()[1]);
        hs+=Integer.parseInt(sp.getData()[2]);
        if(Integer.parseInt(args[0])==0){
            System.out.println("WRONG USAGE NEEDS PORT");
            return;
        }else{
            port=Integer.parseInt(args[0]);
        }
        new Thread("Server"){
            @Override
            public void run(){
                receive(port);
                try (Scanner s = new Scanner(System.in)) {
                    while(running){
                        String text = s.nextLine();
                        if(!text.startsWith("/")){
                            System.out.println("That is not a command. Type /help to list commands");
                        }else{
                            System.out.println("================================");
                            switch(text){
                                case "/help":
                                    System.out.println("/stop \n -Saves and stops server");
                                    System.out.println("/stats \n -Shows stats");
                                    System.out.println("/stats \n -Saves server info");
                                    break;
                                case "/stop":
                                    running=false;
                                    String[] setting = {n+"",d+"",hs+""};
                                    sp.setData(setting);
                                    System.out.println("Sever Info Saved.");
                                    return;
                                case "/stats":
                                    avg=(n/d);
                                    System.out.println("Stats \n "+d+" rounds played \n The average level is "+avg+" \n The highscore is "+hs);
                                    break;
                                case "/save":
                                    running=false;
                                    String[] setting1 = {n+"",d+"",hs+""};
                                    sp.setData(setting1);
                                    System.out.println("Sever Info Saved.");
                                    break;
                                default:
                                    System.out.println("That is not a command. Type /help to list commands");
                                    break;
                            }
                        }
                    }
                }
            }
        }.start();
    }
    private static void receive(final int port){
        new Thread("Receiving"){
            @Override
            public void run(){
                String out = "";
                while(running){
                    try {
                        byte[] data = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        DatagramSocket socket = new DatagramSocket(port);
                        socket.receive(packet);
                        out=new String(packet.getData());
                        System.out.println("Received Packet");
                    } catch (IOException | NumberFormatException ex) {
                        System.out.println(ex.toString());
                    }
                    if("".equals(out)) continue;
                    String[] props = out.split(" ");
                    if(Integer.parseInt(props[0])==0) continue;
                    n+=Integer.parseInt(props[0]);
                    d+=Integer.parseInt(props[1]);
                    if(hs<Integer.parseInt(props[2])) hs=Integer.parseInt(props[2]);
                    avg=(n/d);
                }
            }
        }.start();
    }
}
