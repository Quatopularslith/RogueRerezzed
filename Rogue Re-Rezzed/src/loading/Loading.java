package loading;

import core.Rogue;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Loading extends JPanel {

    double n = .875;
    int r, arrs = 201;
    double newTheta, theta = 2 * Math.asin(1 / (n));
    double cX, cY;

    public Loading() {
        int w = 750;
        this.setSize(w, w);
        this.setVisible(true);
        LoadLoop.start();
        this.repaint();
    }

    public void update() {
        this.repaint();
        if (Load.getTickNum() >= arrs) {
            this.setVisible(false);
            if (Rogue.mm != null) {
                LoadLoop.pause();
                Rogue.mm.mmp.setVisible(true);
                if (Rogue.mm.getWidth() == 750 && Rogue.mm.getHeight() == 750) {
                    Rogue.mm.setSize(750, 500);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (Rogue.mm != null) {
            setSize(Rogue.mm.getSize());
        }
        r = getWidth() / 4;
        cX = (getWidth() / 2) - (r / 1.63);
        cY = (getHeight() / 2) - (r / 1.63);
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        long currtick = Load.getTickNum();
        double nr = r / n;
        int scx = (int) (cX + (getWidth() / 11));
        int scy = (int) (cY + (getHeight() / (6.6666666667)));
        g2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, ((17 * getWidth()) / 1000)));
        for (int i = 0; i < currtick; i++) {
            newTheta = theta * i;
            g2.setColor(Color.BLUE);
            g2.draw(new Ellipse2D.Double(((0 - r) * Math.sin(newTheta)) + cX, (r * Math.cos(newTheta)) + cY, nr, nr));
        }
        g2.setColor(Color.RED);
        g2.drawString("Quatopulularslith", scx - 10, scy);
        g2.drawString("Studios", scx + 15, scy + 15);
        g2.dispose();
    }
}
