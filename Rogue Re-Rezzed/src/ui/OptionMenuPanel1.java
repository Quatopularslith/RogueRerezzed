package ui;

import art.LoadArt;
import core.Rogue;
import input.MButton;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class OptionMenuPanel1 extends JPanel {

    double[] bypos = {0.2, 0.32, 0.44, 0.56, 0.68, 0.8};
    double[] size = {0.26666666666666667, 0.1};
    LoadArt la = new LoadArt();
    int sx = 0, sy = 0;
    Image img = la.createBufferedImage("MainMenu.png", 750, 500);
    MButton fmb = new MButton(getWidth() / 2 - 100, 150, 200, 50, "Forward keybind", this);
    MButton bmb = new MButton(getWidth() / 2 - 100, 210, 200, 50, "Backward keybind", this);
    MButton lmb = new MButton(getWidth() / 2 - 100, 270, 200, 50, "Left Keybind", this);
    MButton rmb = new MButton(getWidth() / 2 - 100, 330, 200, 50, "Right Keybind", this);
    MButton smb = new MButton(getWidth() / 2 - 100, 390, 200, 50, "Skip Turn Keybind (Turn-based only)", this);
    MButton pmb = new MButton(getWidth() / 2 - 100, 450, 200, 50, "Pause Keybind (Evolved only)", this);

    public OptionMenuPanel1() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (sy != Rogue.mm.getHeight() && sx != Rogue.mm.getWidth()) {
            fmb.setParent(this);
            bmb.setParent(this);
            lmb.setParent(this);
            sy = Rogue.mm.getHeight();
            sx = Rogue.mm.getWidth();
            img = la.createBufferedImage("MainMenu.png", Rogue.mm.getWidth(), Rogue.mm.getHeight());
            this.setSize(Rogue.mm.getWidth(), Rogue.mm.getHeight());
            fmb.setPos((int) (getWidth() / 2 - (size[0] * getWidth()) / 2), (int) (bypos[0] * getHeight()), (int) (size[0] * getWidth()), (int) (size[1] * getHeight()));
            bmb.setPos((int) (getWidth() / 2 - (size[0] * getWidth()) / 2), (int) (bypos[1] * getHeight()), (int) (size[0] * getWidth()), (int) (size[1] * getHeight()));
            lmb.setPos((int) (getWidth() / 2 - (size[0] * getWidth()) / 2), (int) (bypos[2] * getHeight()), (int) (size[0] * getWidth()), (int) (size[1] * getHeight()));
            rmb.setPos((int) (getWidth() / 2 - (size[0] * getWidth()) / 2), (int) (bypos[3] * getHeight()), (int) (size[0] * getWidth()), (int) (size[1] * getHeight()));
            smb.setPos((int) (getWidth() / 2 - (size[0] * getWidth()) / 2), (int) (bypos[4] * getHeight()), (int) (size[0] * getWidth()), (int) (size[1] * getHeight()));
            pmb.setPos((int) (getWidth() / 2 - (size[0] * getWidth()) / 2), (int) (bypos[5] * getHeight()), (int) (size[0] * getWidth()), (int) (size[1] * getHeight()));
        }
        g2.drawImage(img, 0, 0, this);
        g2.drawImage(fmb.img, fmb.x, fmb.y, this);
        g2.drawImage(bmb.img, bmb.x, bmb.y, this);
        g2.drawImage(lmb.img, lmb.x, lmb.y, this);
        g2.drawImage(rmb.img, rmb.x, rmb.y, this);
        g2.drawImage(smb.img, smb.x, smb.y, this);
        g2.drawImage(pmb.img, pmb.x, pmb.y, this);
        g2.dispose();
    }
}
