package ui;

import core.Rogue;
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
    int ind=0;
    int inde=0;
    public JButton[] drop = new JButton[Player.pinv.length];
    public JButton[] equip = new JButton[Player.pinv.length];
    String dropn;
    public Inventory() {
        this.setVisible(true);
        this.setLayout(null);
        for(int i=0;i<Player.pinv.length;i++){
            dropn = "Drop"+i;
            drop[i] = new JButton(dropn);
            drop[i].setBounds(135, (i*25)+40-25, 75, 15);
            drop[i].setVisible(true);
            dropn = "Equip"+i;
            equip[i] = new JButton(dropn);
            equip[i].setBounds(55, (i*25)+15, 75, 15);
            equip[i].setVisible(true);
        }
    }
    public void update(){
        for(int i=0;i<Player.pinv.length;i++){
            if(Player.pinv[i]!=null && !Player.pinv[i].name.equalsIgnoreCase("EMPTY")){
                drop[i].setSelected(false);
                drop[i] = new JButton("Drop "+i);
                drop[i].setBounds(130, (i*25)+40, 70, 15);
                drop[i].setVisible(true);
                equip[i].setSelected(false);
                equip[i] = new JButton("Equip "+i);
                equip[i].setBounds(56, (i*25)+40, 74, 15);
                equip[i].setVisible(true);
            }
            if(Player.pinv[i].name.equalsIgnoreCase("EMPTY")){
                this.remove(drop[i]);
                this.remove(equip[i]);
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
        g2.drawString("INVENTORY", (getWidth()/4)-50, 20);
        for(Item i:Player.pinv){
            if(i!=null){
                g2.drawString(i.name, 10, (ind*20)+40);
                ind++;
            }
        }
        g2.dispose();
        for(int i=0;i<Player.pinv.length;i++){
            if(Player.pinv[i]!=null){
                if(!Player.pinv[i].name.equalsIgnoreCase("Empty")){
                    drop[i] = new JButton("Drop "+i);
                    drop[i].setBounds(130, (i*20)+15, 70, 20);
                    drop[i].setVisible(true);
                    equip[i] = new JButton("Equip "+i);
                    equip[i].setBounds(56, (i*20)+15, 74, 20);
                    equip[i].setVisible(true);
                    this.add(drop[i]);
                    drop[i].addActionListener(Rogue.mm.bi);
                    this.add(equip[i]);
                    equip[i].addActionListener(Rogue.mm.bi);
                }
            }
        }
    }
}
