
package ui;

import core.Rogue;
import dungeon.Level;
import entity.player.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class Status extends JPanel{
    public void update(){
        repaint();
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.WHITE);
        g2.drawString("You are on level: "+Level.numLevels, 10, 20);
        g2.drawString("Your Health: "+Rogue.getLevel().getPlayer().health, 10, 40);
        g2.drawString("Your Mana: "+Rogue.getLevel().getPlayer().mana, 10, 60);
        g2.drawString("Your Max Attack: "+Rogue.getLevel().getPlayer().maxAtt, 10, 80);
        g2.drawString("Your Defence: "+Rogue.getLevel().getPlayer().defence, 10, 100);
        g2.drawString("You have killed "+Player.kills+" Enemies",10,120);
    }
}
