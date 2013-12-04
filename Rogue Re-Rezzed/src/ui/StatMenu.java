

package ui;
import core.Rogue;
import javax.swing.JPanel;
import dungeon.Level;
import entity.player.Player;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
/**
 *
 * @author Quatopularslith
 */
public class StatMenu extends JPanel{
    JButton jb = new JButton("Return");
    public StatMenu(){
        this.setLayout(new FlowLayout());
    }
    @Override
    public void paint(Graphics g){
        Rogue.mm.refresh();
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, 200, getHeight());
        g2.setColor(Color.red);
        g2.drawString("YOU HAVE DIED", 10, 20);
        g2.setColor(Color.WHITE);
        g2.drawString("You in dungeon: "+(Level.numLevels+1), 10, 40);
        g2.drawString("You killed "+Player.kills+" Enemies",10,60);
        g2.drawString("You are Level: "+Player.xplevels, 10, 80);
        g2.drawString("Your Items were: ",10,100);
        g2.drawString("------------------------", 10, 110);
        for(int i=0;i<Player.pinv.length;i++){
            g2.drawString(Player.pinv[i].name, 20, (i*20)+120);
        }
        g2.dispose();
        jb.setVisible(true);
        this.add(jb);
    }
}

