
package ui;

import art.LoadArt;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 *h
 * @author Mnenmenth
 */
public class MainMenuPanel extends javax.swing.JPanel{
    LoadArt la = new LoadArt();
    Image button = la.createImage("Button.png", "button", 200, 50);
    public MainMenuPanel(){
        this.setVisible(true);
        this.repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);
        g2.setFont(new Font("Arial",Font.BOLD,15));
        g2.fillRect(0,0,getHeight(),getWidth());
        g2.drawImage(button, 10,10, this);
        g2.drawString("New Game", 60, 25);
        g2.dispose();
    }
}
