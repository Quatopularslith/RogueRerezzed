/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;
import art.LoadArt;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.*;
import util.IPanel;
/**
 *
 * @author Razim
 */
//http://java-demos.blogspot.com/2012/09/setting-background-image-in-jframe.html
public class MenuBackgroundTest extends JFrame{
    public JButton newGame1=new JButton("NEW LEVEL");
    public JButton loadGame1=new JButton("NEW LEVEL");
    public JButton options1=new JButton("NEW LEVEL");
    public JButton quit1=new JButton("NEW LEVEL");
    public JLabel title1=new JLabel("NEW LEVEL");
//    public JLabel bg = new JLabel(new ImageIcon("RogueLogo.png"));
    LoadArt la = new LoadArt();
    Image img = la.createImage("RogueLogo.png", "Hello", 750, 500);
    public MenuBackgroundTest(){
        super("TEST");
        setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(750, 500);
        JPanel up = new IPanel(img);
        up.setLayout(new FlowLayout());
        up.add(title1);
        up.add(newGame1);
        up.add(loadGame1);
        up.add(quit1);
        this.add(up);
        this.setSize(399,399);
        this.setSize(750, 500);
    }
}
