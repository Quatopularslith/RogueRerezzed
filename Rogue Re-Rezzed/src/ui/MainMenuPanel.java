
package ui;

import core.Rogue;
import input.MButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * 
 * @author Mnenmenth
 */
public class MainMenuPanel extends javax.swing.JPanel{
    MButton newGame = new MButton(getWidth()/2-100,10,200,50,"New Game");
    MButton options = new MButton(getWidth()/2-100,60,200,50,"Options");
    MButton quit = new MButton(getWidth()/2-100,110,200,50,"Quit");
    public MainMenuPanel(){
        this.setVisible(true);
        this.repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.fillRect(0,0,getWidth(),getHeight());
        newGame.setPos(getWidth()/2-100, 10);
        options.setPos(getWidth()/2-100, 60);
        quit.setPos(getWidth()/2-100, 110);
        g2.drawImage(newGame.img, newGame.x, newGame.y, this);
        newGame.addListener(Rogue.mm.mbi);
        g2.drawImage(options.img, options.x, options.y, this);
        options.addListener(Rogue.mm.mbi);
        g2.drawImage(quit.img, quit.x, quit.y, this);
        quit.addListener(Rogue.mm.mbi);
        g2.dispose();
    }
}
