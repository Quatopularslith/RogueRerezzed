/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
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
    public JLabel bg = new JLabel(new ImageIcon("RogueLogo.png"));
    public MenuBackgroundTest(){
        super("TEST");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(750, 500);
        setLayout(new BorderLayout());
        setContentPane(bg);
        bg.setLayout(new FlowLayout());
        bg.add(title1);
        bg.add(newGame1);
        bg.add(loadGame1);
        bg.add(quit1);
    }
//    @Override
//    public void paint(Graphics g){
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setBackground(Color.yellow);
//    }
}
