
package ui;

import core.Rogue;
import input.MButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *h
 * @author Mnenmenth
 */
public class MainMenuPanel extends javax.swing.JPanel{
    MButton newGame = new MButton(10,10,200,50,"New Game");
    public MainMenuPanel(){
        this.setVisible(true);
        this.repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        System.out.println(getWidth()+" "+getHeight());
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.drawImage(newGame.img, newGame.x, newGame.y, this);
        newGame.addListener(Rogue.mm.mbi);
        g2.dispose();
    }
}
