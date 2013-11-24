/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import render.Sprite;

/**
 *
 * @author 1003749
 */
public class Map extends JPanel{
    int offx=0,offy=0;
    public static Sprite img=new Sprite("DungeonFloor1",8);
    public static Sprite pimg=new Sprite("Player",8);
    public static Sprite stimg=new Sprite("Stairway",8);
    Level l = Rogue.getLevel();
    public void update(){
        l = Rogue.getLevel();
        repaint();
    }
    void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2)-e.x*8;
        offy=(getHeight()/2)-e.y*8;
    }
    @Override
    public void paint(Graphics g){
        setReletiveTo(l.getPlayer());
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        Room[] room = l.getRooms();
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    g2.drawImage(img.i, area1[0]*8+offx,area1[1]*8+offy, this);
                }
            }
        }
        g2.drawImage(stimg.i, l.getStairWay().x*8+offx,l.getStairWay().y*8+offy, this);
        g2.drawImage(pimg.i,l.getPlayer().x*8+offx,l.getPlayer().y*8+offy,this);
    }
}
