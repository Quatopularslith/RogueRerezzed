package loading;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
public class Loading extends JFrame{
    double n = .875;
    int r, width, arrs = 201;
    boolean go=false;
    double newTheta, theta = 2*Math.asin(1/(2*n));
    double[] x=new double[arrs], y=new double[arrs];
    double cX, cY;
    long now, timeTaken;
    public Loading(int w){
        super("Eyeris Studios");
        this.setSize(w,w);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        while(go=false){
            Core.Game g = new Core.Game(750,500);
            this.dispose();
        }
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GameLoop.start();
        long starttick= Game.getTickNum();
        long currtick = (Game.getTickNum()-starttick);
        double nr = r/n;
        int scx = (int) (cX+(width/20));
        int scy = (int) (cY+(width/6.666666666666666666666666666666666666666666666666666666666666666666666667));
        g2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,((17*width)/1000)));
        // TODO insert graphic logo
        while(currtick<(arrs-1)){
            g2.setColor(Color.RED);
            g2.drawString("Eyeris Studios", scx, scy);
            g2.setColor(Color.BLUE);
            g2.draw(new Ellipse2D.Double(x[(int) currtick], y[(int) currtick], nr, nr));
            currtick = (Game.getTickNum()-starttick);
        }
        go=true;
        System.out.println("Done");
        GameLoop.pause();
    }
}
