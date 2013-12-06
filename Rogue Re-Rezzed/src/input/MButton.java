
package input;

import art.LoadArt;
import core.Rogue;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Mouse Button
 * @author Torri
 */
public class MButton {
    public BufferedImage img;
    public int x;
    public int y;
    private boolean isListened = false;
    private final LoadArt la = new LoadArt();
    private MButtonInput mbi;
    private int sx;
    private int sy;
    private final String name;
    private final Component parent;
    /**
     * Makes a MButton
     * @param x1 top left corner
     * @param y1 top left corner
     * @param sx1 width
     * @param sy1 height
     * @param name1 name
     * @param parent1
     */
    public MButton(int x1,int y1,int sx1,int sy1, String name1, Component parent1){
        x=x1;
        y=y1;
        sx=sx1;
        sy=sy1;
        name=name1;
        img = la.createBufferedImage("Button.png", sx, sy);
        parent=parent1;
        isListened=false;
        Graphics2D g = img.createGraphics();
        g.setColor(Color.BLUE);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,sy/2));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int width = fm.stringWidth(name);
        g.drawString(name, sx/2-width/2, sy/2+sy/4);
        g.dispose();
    }
    public void addListener(MButtonInput mbi1){
        if(!isListened){
            mbi=mbi1;
            if(Rogue.mm!=null){
                Rogue.mm.mi.mb.add(this);
            }
            isListened=true;
        }
    }
    public void update(int mx,int my){
        if(mx>x && mx<x+sx && my>y+30 && my<y+sy+30 && parent.isVisible()){
            mbi.clicked(name);
        }
    }
    public void setPos(int x1,int y1,int sx1,int sy1){
        x=x1;
        y=y1;
        sx=sx1;
        sy=sy1;
        img = new BufferedImage(sx,sy, BufferedImage.TYPE_INT_ARGB);
        img = la.createBufferedImage("Button.png", sx, sy);
        isListened=false;
        Graphics2D g = img.createGraphics();
        g.setColor(Color.BLUE);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,sy/2));
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int width = fm.stringWidth(name);
        g.drawString(name, sx/2-width/2, sy/2+sy/4);
        g.dispose();
    }
}
