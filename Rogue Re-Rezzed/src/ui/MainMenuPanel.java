
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
    double[] bypos = {0.3,0.42,0.54};
    double[] size = {0.26666666666666667,0.1};
    LoadArt la = new LoadArt();
    public int numlevels=1;
    int offx,offy,moffx,moffy;
    int sx,sy;
    BufferedImage img;
    MButton newGame = new MButton(getWidth()/2-100,150,200,50,"New Game",this);
    MButton options = new MButton(getWidth()/2-100,210,200,50,"Options",this);
    MButton quit = new MButton(getWidth()/2-100,270,200,50,"Quit",this);
    public MainMenuPanel(){
        this.repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if(sy!=Rogue.mm.getHeight() && sx!=Rogue.mm.getWidth()){
            newGame.setParent(this);
            options.setParent(this);
            quit.setParent(this);
            sy=Rogue.mm.getHeight();
            sx=Rogue.mm.getWidth();
            img = la.createBufferedImage("MainMenu.png",Rogue.mm.getWidth(), Rogue.mm.getHeight());
            this.setSize(Rogue.mm.getWidth(), Rogue.mm.getHeight());
            newGame.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[0]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
            options.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[1]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
            quit.setPos((int) (getWidth()/2-(size[0]*getWidth())/2), (int) (bypos[2]*getHeight()), (int) (size[0]*getWidth()), (int) (size[1]*getHeight()));
            newGame.addListener(Rogue.mm.mbi,this);
            options.addListener(Rogue.mm.mbi,this);
            quit.addListener(Rogue.mm.mbi,this);
        }
        g2.drawImage(img, 0,0, this);
        g2.drawImage(newGame.img, newGame.x, newGame.y, this);
        g2.drawImage(options.img, options.x, options.y, this);
        g2.drawImage(quit.img, quit.x, quit.y, this);
        g2.dispose();
    }
}
