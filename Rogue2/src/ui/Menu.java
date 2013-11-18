/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import input.ButtonInput;
import input.KeyboardInput;
import javax.swing.JFrame;
import util.RogueProperties;

/**
 *
 * @author Torri
 */
public class Menu extends JFrame{
    public MainMenuPanel mmp;
    public OptionMenuPanel omp;
    public Display d;
    
    public KeyboardInput ki;
    public ButtonInput bi;
    
    public static RogueProperties rp;
    
    private final String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB","invKB"};
    private int[] propn;
    /**
     * Creates and handles the menus of the game
     */
    public Menu(){
        super("Rogue Re-Rezzed");
        this.setSize(750, 500);
        this.setFocusable(true);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        rp = new RogueProperties("RogueConfig.properties",props);
        propn = new int[rp.getSettings().length];
        
        for(int i=0;i<rp.getSettings().length;i++){
            propn[i]=Integer.parseInt(rp.getSettings()[i]);
        }
        
        bi=new ButtonInput();
        ki=new KeyboardInput(propn);
        
        omp = new OptionMenuPanel();
        mmp = new MainMenuPanel();
        
        omp.setVisible(false);
        mmp.setVisible(true);
        
        omp.setSize(750, 500);
        mmp.setSize(750, 500);
        
        this.add(omp);
        this.add(mmp);
        
        this.addKeyListener(ki);
        
        mmp.loadGame.addActionListener(bi);
        mmp.newGame.addActionListener(bi);
        mmp.options.addActionListener(bi);
        mmp.quit.addActionListener(bi);
        
        omp.apply.addActionListener(bi);
        omp.back.addActionListener(bi);
        omp.defaultKB.addActionListener(bi);
        
        this.invalidate();
        this.validate();
        this.repaint();
        
        omp.eatKB.setText(rp.getSettings()[0]);
        omp.leftKB.setText(rp.getSettings()[1]);
        omp.rightKB.setText(rp.getSettings()[2]);
        omp.fwdKB.setText(rp.getSettings()[3]);
        omp.spellKB.setText(rp.getSettings()[4]);
        omp.backKB.setText(rp.getSettings()[5]);
        // eat, left, right, fwd, spell, back
    }
}
