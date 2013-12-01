/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

/**
 * Not our code; This is something we tried (and failed to use)
 * @author Java42
 */
public class IPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image imageOrg= null;
    private Image image= null;
    {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int w = IPanel.this.getWidth();
                final int h = IPanel.this.getHeight();
                image = w > 0 && h > 0 ? imageOrg.getScaledInstance(w, h, Image.SCALE_SMOOTH) : imageOrg;
                IPanel.this.repaint();
            }
        });
    }
    public IPanel(final Image i) {
        imageOrg = i;
        image = i;
        this.repaint();
    }
    @Override
    public void paintComponent(final Graphics g) {
        super.paint(g);
        super.paintComponent(g);
        if (image != null){
            g.drawImage(image, 0, 0, null);
        }
        g.dispose();
    }
}