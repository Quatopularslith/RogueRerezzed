
package ui;

import art.LoadArt;
import core.Rogue;
import input.MButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class NewGamePanel extends JPanel{
    double[] bypos = {0.3,0.42,0.54};
    double[] size = {0.4375,0.1};
    LoadArt la = new LoadArt();
    Image img = la.createBufferedImage("MainMenu.png", 750, 500);
    MButton turnStory = new MButton(getWidth()/2-100,150,200,50,"Turn-Based Story mode",this);
    MButton evolvedStory = new MButton(getWidth()/2-100,210,200,50,"Evolved Story mode",this);
    MButton back = new MButton(getWidth()/2-100,270,200,50,"Back",this);
    public NewGamePanel(){
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        refresh();
        g2.drawImage(img, 0,0, this);
        g2.drawImage(turnStory.img, turnStory.x, turnStory.y, this);
        g2.drawImage(evolvedStory.img, evolvedStory.x, evolvedStory.y, this);
        g2.drawImage(back.img, back.x, back.y, this);
        g2.dispose();
    }
    public void refresh(){
        if(Rogue.mm==null) return;
        turnStory.setParent(this);
        evolvedStory.setParent(this);
        back.setParent(this);
        img = la.createBufferedImage("MainMenu.png",Rogue.mm.getWidth(), Rogue.mm.getHeight());
        this.setSize(Rogue.mm.getWidth(), Rogue.mm.getHeight());
        turnStory.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[0]*getHeight())+20, (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
        evolvedStory.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[1]*getHeight())+20, (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
        back.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[2]*getHeight())+20, (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
        turnStory.addListener(Rogue.mm.mbi,this);
        evolvedStory.addListener(Rogue.mm.mbi,this);
        back.addListener(Rogue.mm.mbi,this);
        this.repaint();
    }
}
