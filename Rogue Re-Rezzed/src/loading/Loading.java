package loading;
import core.Rogue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
public class Loading extends JPanel{
    double n = .875;
    int r, width, arrs = 201;
    double newTheta, theta = 2*Math.asin(1/(2*n));
    double[] x=new double[arrs], y=new double[arrs];
    double cX, cY;
    public Loading(){
        int w = 750;
        this.setSize(w,w);
        this.setVisible(true);
        width=w;
        r=w/4;
        cX=(w/2)-r/2;
        cY=(w/2)-r/2;
        for(int i=0;i<(arrs-1);i++){
            newTheta=i*theta;
            x[i]=((0-r)*Math.sin(newTheta));
            y[i]=(r*Math.cos(newTheta));
            x[i]+=cX;
            y[i]+=cY;
        }
        LoadLoop.start();
        this.repaint();
    }
    public void update(){
        this.repaint();
        if(Load.getTickNum()>=x.length-1){
            this.setVisible(false);
            if(Rogue.mm!=null){
                LoadLoop.pause();
                Rogue.mm.mmp.setVisible(true);
                Rogue.mm.setSize(750, 500);
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        long currtick=Load.getTickNum();
        double nr = r/n;
        int scx = (int) (cX+(width/11));
        int scy = (int) (cY+(width/(6.666666666666666666666666666666666666666666666666666666666666666667)));
        g2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,((17*width)/1000)));
        for(int i=0;i<currtick;i++){
            g2.setColor(Color.BLUE);
            g2.draw(new Ellipse2D.Double(x[i], y[i], nr, nr));
        }
        g2.setColor(Color.RED);
        g2.drawString("Quotopulularslith", scx-10, scy);
        g2.drawString("Studios",scx+15,scy+15);
        g2.dispose();
    }
}
