
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
            System.out.println("WRONG USAGE needs port");
            return;
        }else{
            port=Integer.parseInt(args[0]);
        }
        new Thread("Server"){
            @Override
            public void run(){
                receive(port);
                Scanner s = new Scanner(System.in);
                while(running){
                    String text = s.nextLine();
                    if(!text.startsWith("/")){
                        System.out.println("That is not a command. Type /help to list commands");
                    }else{
                        System.out.println("================================");
                        switch(text){
                            case "/help":
                                System.out.println("/stop \n -Stops server");
                                System.out.println("/stats \n -Shows stats");
                                break;
                            case "/stop":
                                running=false;
                                String[] setting = {n+"",d+"",hs+""};
                                sp.setData(setting);
                                return;
                            case "/stats":
                                avg=(n/d);
                                System.out.println("Stats \n "+d+" rounds played \n The average level is "+avg+" \n The highscore is "+hs+" \n n="+n+" \n avg="+(n/d));
                                break;
                            default:
                                System.out.println("That is not a command. Type /help to list commands");
                                break;
                        }
                    }
                }
                s.close();
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
                        ex.printStackTrace(System.err);
                    }
                    if("".equals(out) || " ".equals(out)) continue;
                    String[] props = out.split(" ");
                    n+=Integer.parseInt(props[0]);
                    d+=Integer.parseInt(props[1]);
                    if(hs<Integer.parseInt(props[2])) hs=Integer.parseInt(props[2]);
                    avg=(n/d);
                }
            }
        }.start();
    }
}
