/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package input;

import art.LoadArt;
import core.Rogue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Mouse Button
 * @author Torri
 */
public class MButton {
    public final BufferedImage img;
    public final int x;
    public final int y;
    private final LoadArt la = new LoadArt();
    private MButtonInput mbi;
    private final int sx;
    private final int sy;
    private final String name;
    /**
     * Makes a MButton
     * @param x1 top left corner
     * @param y1 top left corner
     * @param sx1 width
     * @param sy1 height
     * @param name1 name
     */
    public MButton(int x1,int y1,int sx1,int sy1, String name1){
        x=x1;
        y=y1;
        sx=sx1;
        sy=sy1;
        name=name1;
        img = la.createBufferedImage("Button.png", sx, sy);
        Graphics g = img.getGraphics();
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,sy));
        FontMetrics fm = g.getFontMetrics(new Font(Font.SANS_SERIF,Font.BOLD,sy));
        int width = fm.stringWidth(name);
        g.drawString(name, sx/2-width/2, 0);
        g.dispose();
    }
    public void addListener(MButtonInput mbi1){
        mbi=mbi1;
        Rogue.mm.mi.mb.add(this);
    }
    public void update(int mx,int my){
        if(mx>x && mx<x+sx && my>y && my<y+sy){
            mbi.clicked(name);
        }
    }
}
