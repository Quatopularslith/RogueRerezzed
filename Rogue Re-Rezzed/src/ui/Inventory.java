
package ui;

import entity.item.Item;
import entity.player.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class Inventory extends JPanel{
    int ind = 0;
    int inde =0;
    JButton[] drop = new JButton[Player.pinv.length];
    public Inventory() {
        this.setVisible(true);
        this.setLayout(null);
    }
    public void update(){
        for(int i=0;i<Player.pinv.length;i++){
            if(!Player.pinv[i].name.equalsIgnoreCase("Empty")){
                drop[i] = new JButton("Drop "+i);
                drop[i].setBounds(130, (i*25)+40-25, 70, 15);
                drop[i].setVisible(true);
            }
        }
        repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        ind = 0;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.WHITE);
        g2.drawString("INVENTORY", (getWidth()/4)-45, 20);
        for(Item i:Player.pinv){
            g2.drawString(i.name, 10, (ind*20)+40);
            ind++;
        }
        g2.dispose();
        for(int i=0;i<Player.pinv.length;i++){
            if(!Player.pinv[i].name.equalsIgnoreCase("Empty")){
                this.add(drop[i]);
            }
        }
    }
}
