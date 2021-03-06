
package hs;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
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
    static DatagramSocket socket;
    static Thread receive = new Thread("Receiving"){
        @Override
        public void run(){
            String out;
            while(running){
                byte[] data = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                try {
                    socket.receive(packet);
                } catch (IOException ex) {
                    System.err.println("You got errors! "+ex.toString());
                }
                out = new String(packet.getData());
                System.out.println("Received Packet");
                if("".equals(out)) continue;
                String[] props = out.split(" ");
                if(Integer.parseInt(props[0])==0) continue;
                n+=Integer.parseInt(props[0]);
                d+=Integer.parseInt(props[1]);
                if(hs<Integer.parseInt(props[2])) hs=Integer.parseInt(props[2]);
                if(d!=0) avg=(n/d);
                System.out.println("Stats: \n "+d+" rounds played \n The average level is "+avg+" \n The highscore is "+hs);
                System.out.println("================================");
            }
        }
    };
    static Thread server;
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
        if(args[0]==null){
            System.out.println("WRONG USAGE: NEEDS PORT");
            return;
        } 
        port=Integer.parseInt(args[0]);
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            System.out.println("You got errors! "+ex.toString());
        }
        System.out.println("SERVER STARTED");
        server = new Thread("Server"){
            @Override
            public void run(){
                receive();
                try (Scanner s = new Scanner(System.in)) {
                    while(running){
                        String text = s.nextLine();
                        if(!text.startsWith("/")){
                            System.out.println("That is not a command. Type /help to list commands");
                            System.out.println("================================");
                        }else{
                            switch(text){
                                case "/help":
                                    System.out.println("/stop \n -Saves and stops server");
                                    System.out.println("/stats \n -Shows stats");
                                    System.out.println("/save \n -Saves server info");
                                    break;
                                case "/stop":
                                    running=false;
                                    String[] setting = {n+"",d+"",hs+""};
                                    sp.setData(setting);
                                    System.out.println("Sever Info Saved.");
                                    serverstop();
                                    System.out.println("Server Closed");
                                    break;
                                case "/stats":
                                    if(d!=0) avg=(n/d);
                                    System.out.println("Stats: \n "+d+" rounds played \n The average level is "+avg+" \n The highscore is "+hs);
                                    break;
                                case "/save":
                                    String[] setting1 = {n+"",d+"",hs+""};
                                    sp.setData(setting1);
                                    System.out.println("Sever Info Saved.");
                                    break;
                                default:
                                    System.out.println("That is not a command. Type /help to list commands");
                                    break;
                            }
                        }
                        System.out.println("================================");
                    }
                }
            }
        };
        server.start();
    }
    private static void receive(){
        receive.start();
    }
    private static void serverstop(){
        try {
            server.interrupt();
            receive.interrupt();
            server.join();
            receive.join();
        } catch (InterruptedException ex) {}
    }
}
