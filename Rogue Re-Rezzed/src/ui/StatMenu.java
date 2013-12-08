

package ui;
import core.Rogue;
import javax.swing.JPanel;
import dungeon.Level;
import entity.player.Player;
import input.MButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 *
 * @author Quatopularslith
 */
public class StatMenu extends JPanel{
    MButton mb;
    public StatMenu(){
        mb = new MButton(750/2,500/2,(int) (0.266666666666666666666667*750),(int) (0.1*500),"Return",this);
    }
    @Override
    public void paint(Graphics g){
        setSize(Rogue.mm.getSize());
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.RED);
        g2.drawString("YOU HAVE DIED", 10, 20);
        g2.setColor(Color.WHITE);
        g2.drawString("You were in dungeon: "+(Level.numLevels), 10, 40);
        g2.drawString("You killed "+Player.kills+" Enemies",10,60);
        g2.drawString("You are Level: "+Player.xplevels, 10, 80);
        g2.drawString("Your Items were: ",10,100);
        g2.drawString("------------------------", 10, 110);
        for(int i=0;i<Player.pinv.length;i++){
            g2.drawString(Player.pinv[i].name, 20, (i*20)+120);
        }
        mb.setPos(getWidth()/2-(int) (0.266666666666666666666667*getWidth()),getHeight()/2-(int) (0.1*getHeight()),(int) (0.266666666666666666666667*getWidth()),(int) (0.1*getHeight()));
        mb.addListener(Rogue.mm.mbi);
        g2.drawImage(mb.img, mb.x,mb.y, this);
        g2.dispose();
    }
}

