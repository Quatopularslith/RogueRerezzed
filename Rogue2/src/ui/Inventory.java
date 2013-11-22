/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import dungeon.Level;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class Inventory extends JPanel{
    public Inventory() {
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.drawString("This is level: "+Level.numLevels, 40, 40);
    }
}
