
package hs;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Torri
 */
public class RogueHighScoreServer {
    static boolean running = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Thread("Server"){
            @Override
            public void run(){
                while(running){
                    
                }
            }
        }.start();
    }
    private void receive(){
        new Thread("Receiving"){
            @Override
            public void run(){
                while(running){
                    try {
                        byte[] data = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(data, data.length);
                        DatagramSocket socket = new DatagramSocket(3000);
                        socket.receive(packet);
                        
                    } catch (Exception ex) {
                        ex.printStackTrace(System.err);
                    }
                }
            }
        }.start();
    }
}
