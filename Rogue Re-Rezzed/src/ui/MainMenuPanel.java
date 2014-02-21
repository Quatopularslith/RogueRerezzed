
package ui;

import art.LoadArt;
import core.Rogue;
import input.MButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Mnenmenth
 */
public class MainMenuPanel extends javax.swing.JPanel{
    double[] bypos = {0.3,0.42,0.54,0.66};
    double[] size = {0.26666666666666667,0.1};
    LoadArt la = new LoadArt();
    public int numlevels=1;
    int offx,offy,moffx,moffy;
    int sx=750,sy=500;
    BufferedImage img = la.createBufferedImage("MainMenu.png",750, 500);
    MButton newGame = new MButton(getWidth()/2-100,150,200,50,"New Game",this);
    MButton loadGame = new MButton(getWidth()/2-100,210,200,50,"Load Game",this);
    MButton options = new MButton(getWidth()/2-100,270,200,50,"Options",this);
    MButton quit = new MButton(getWidth()/2-100,330,200,50,"Quit",this);
    public MainMenuPanel(){
        refresh();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        refresh();
        g2.drawImage(img, 0,0, this);
        g2.drawImage(newGame.img, newGame.x, newGame.y, this);
        g2.drawImage(loadGame.img, loadGame.x, loadGame.y, this);
        g2.drawImage(options.img, options.x, options.y, this);
        g2.drawImage(quit.img, quit.x, quit.y, this);
        g2.dispose();
    }
    public void refresh(){
        if(Rogue.mm==null) return;
        newGame.setParent(this);
        loadGame.setParent(this);
        options.setParent(this);
        quit.setParent(this);
        img = la.createBufferedImage("MainMenu.png",Rogue.mm.getWidth(), Rogue.mm.getHeight());
        this.setSize(Rogue.mm.getWidth(), Rogue.mm.getHeight());
        newGame.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[0]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
        loadGame.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[1]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
        options.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[2]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
        quit.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[3]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
        repaint();
    }
}
