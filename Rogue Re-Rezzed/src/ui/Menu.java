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
    public DebugMPassword dmp;
//    public DebugMenu dm;
//    public MenuBackgroundTest mbt;
    
    public KeyboardInput ki;
    public ButtonInput bi;
    
    public static RogueProperties rp;
    
    private final String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB"};
    private int[] propn;
    /**
     * Creates and handles the menus of the game
     */
    public Menu(){
        super("Rogue Re-Rezzed (Proof of Concept v1.2.8)");
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
        dmp = new DebugMPassword();
//        mbt = new MenuBackgroundTest();
        
        dmp.setVisible(false);
        omp.setVisible(false);
        mmp.setVisible(true);
//        mbt.setVisible(false);
        
        dmp.setSize(750, 500);
        omp.setSize(750, 500);
        mmp.setSize(750, 500);
//        mbt.setSize(750, 500);
        
        this.add(dmp);
        this.add(omp);
        this.add(mmp);
        
        this.addKeyListener(ki);
        
//        mmp.menuBackgroundTesting.addActionListener(bi);
        mmp.loadGame.addActionListener(bi);
        mmp.newGame.addActionListener(bi);
        mmp.options.addActionListener(bi);
        mmp.quit.addActionListener(bi);
        
        omp.apply.addActionListener(bi);
        omp.back.addActionListener(bi);
        omp.defaultKB.addActionListener(bi);
        omp.debug.addActionListener(bi);
        
        dmp.debugPass.addActionListener(bi);
        
        int[] j = new int[rp.getSettings().length];
        char[] c = new char[j.length];
        for(int i=0;i<j.length;i++){
            j[i]=Integer.valueOf(rp.getSettings()[i]);
            c[i]=(char) j[i];
        }
        
        omp.fwdKB.setText(String.valueOf(c[0]));
        omp.backKB.setText(String.valueOf(c[1]));
        omp.rightKB.setText(String.valueOf(c[2]));
        omp.leftKB.setText(String.valueOf(c[3]));
        omp.spellKB.setText(String.valueOf(c[4]));
        omp.eatKB.setText(String.valueOf(c[5]));
        
        this.invalidate();
        this.validate();
        this.repaint();
    }
}
