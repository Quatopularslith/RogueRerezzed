
package ui;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import input.ButtonInput;
import input.KeyboardInput;
import input.MButtonInput;
import input.MouseInput;
import java.awt.Image;
import javax.swing.JFrame;
import loading.Loading;
import util.RogueProperties;

/**
 * 
 * @author Torri
 */
public class Menu extends JFrame{
    public LoadArt la = new LoadArt();
    
    public MainMenuPanel mmp;
    public OptionMenuPanel omp;
//    public Display d;
    public DebugMPassword dmp;
    public StatMenu sm;
    public DebugMenu dm;
    public Loading load;
    
    public KeyboardInput ki;
    public ButtonInput bi;
    public MouseInput mi;
    public MButtonInput mbi;
    
    public GamePlay gp;
    
    public static RogueProperties rp;
    public static int x,y,sx,sy;
    
    private final String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB"};
    private final String[] defkeys = {"87","83","68","65","81","69"};
    private final int[] propn;
    /**
     * Creates and handles the menus of the game
     */
    public Menu(){
        super("Rogue Re-Rezzed (Proof of Concept v1.6.5)");
        this.setSize(750, 750);
        this.setFocusable(true);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Image ico = la.createBufferedImage("Quatopularslith16.png", 16, 16);
        this.setIconImage(ico);
        
        x=getX();
        y=getY();
        sx=getWidth();
        sy=getHeight();
        
        rp = new RogueProperties("RogueConfig.properties",props);
        if(rp.getSettings()==null){
            Menu.rp.setData(defkeys);
            omp.fwdKB.setText("W");
            omp.backKB.setText("S");
            omp.rightKB.setText("D");
            omp.leftKB.setText("A");
            omp.spellKB.setText("Q");
            omp.eatKB.setText("E");
            ki.checkSettings(bi.defkeys);
        }
        propn = new int[rp.getSettings().length];
        
        for(int i=0;i<rp.getSettings().length;i++){
            propn[i]=Integer.parseInt(rp.getSettings()[i]);
            if(propn==null){
                rp.addData(props[i], defkeys[i]);
            }
        }
        
        bi=new ButtonInput();
        ki=new KeyboardInput(propn);
        mi=new MouseInput();
        mbi=new MButtonInput();
        
        omp = new OptionMenuPanel();
        mmp = new MainMenuPanel();
        dmp = new DebugMPassword();
        sm = new StatMenu();
        load = new Loading();
        
        dmp.setVisible(false);
        omp.setVisible(false);
        mmp.setVisible(false);
        sm.setVisible(false);
        load.setVisible(true);
        
        gp = new GamePlay();
        gp.setVisible(false);
        gp.setSize(750,500);
        
        load.setSize(getWidth(), getWidth());
        dmp.setSize(750, 500);
        omp.setSize(750, 500);
        mmp.setSize(750, 500);
        sm.setSize(750, 500);
        
        this.add(dmp);
        this.add(omp);
        this.add(mmp);
        this.add(sm);
        this.add(load);
        this.add(gp);
        
        this.addMouseListener(mi);
        this.addKeyListener(ki);
        
        omp.apply.addActionListener(bi);
        omp.back.addActionListener(bi);
        omp.defaultKB.addActionListener(bi);
        omp.debug.addActionListener(bi);
        omp.autoequip.addActionListener(bi);
        
        dmp.debugPass.addActionListener(bi);
        
        sm.jb.addActionListener(bi);
        
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
        
        refresh();
    }
    /**
     * makes everything repaint
     */
    public void refresh(){
        this.invalidate();
        this.validate();
        this.repaint();
    }
}
