/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
/**
 *
 * @author Razim
 */
//http://java-demos.blogspot.com/2012/09/setting-background-image-in-jframe.html
public class MenuBackgroundTest extends JFrame{
    public JButton newGame1;
    public JButton loadGame1;
    public JButton options1;
    public JButton quit1;
    public JLabel title1;       
    public JLabel bg = new JLabel(new ImageIcon("RogueLogo.png"));
    public MenuBackgroundTest(){
        super("Rogue Rerezzed");
        this.setSize(750, 500);
        setLayout(new BorderLayout());
        add(bg);
        bg.setLayout(new FlowLayout());
        bg.add(title1);
        bg.add(newGame1);
        bg.add(loadGame1);
        bg.add(quit1);
    }
}
