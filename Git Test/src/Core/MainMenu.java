package Core;

import Assets.LoadArt;
import Entity.Player;
import Level.Level;
import Render.Display;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame implements ActionListener {
    
    public static Thread render=new Thread("Render");
    boolean backtogame = false;
    
    public static boolean go;
    public static KeyboardInput key;
    public static Level l = new Level(0);
    public static Display display = new Display();
    
    int[] defkeys = {KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_Q,KeyEvent.VK_E};
    int[] keys = new int[6];
    String[] keyprop = new String[6];
    String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB"};
    
    File worldSave;
    String worlddir;
    RogueConfig rc = new RogueConfig(props);
    
    OptionMenuPanel optionMenu;
    MainMenuPanel mainMenuPanel;
    
    public MainMenu(int x, int y){
        super("Rogue Rerezzed");
        this.setFocusable(true);
        
        keys = Cast.stringstoInts(rc.getSettings());
        
        optionMenu = new OptionMenuPanel();
        mainMenuPanel = new MainMenuPanel();
        optionMenu.setVisible(false);
        mainMenuPanel.setVisible(true);
        this.add(optionMenu);
        this.add(mainMenuPanel);
        
        this.add(display);
        this.setSize(x,y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        display.setVisible(false);
        
        LoadArt load = new LoadArt();
        ImageIcon icon = load.createImageIcon("RogueLogo.png","LOGO YOLO");
        this.setIconImage(load.createImageIcon("Quotopularslith.png", "Favicon").getImage());
        
        JLabel title = new JLabel("");
        title.setIcon(icon);
        
        mainMenuPanel.setSize(x,y);
        optionMenu.setVisible(false);
        optionMenu.setSize(x,y);
        
        key=new KeyboardInput(keys);
        addKeyListener(key);
        display.optionsD.addActionListener(this);
        display.save.addActionListener(this);
        mainMenuPanel.newGame.addActionListener(this);
        mainMenuPanel.options.addActionListener(this);
        optionMenu.back.addActionListener(this); 
        optionMenu.apply.addActionListener(this);
        optionMenu.defaultKB.addActionListener(this);
        mainMenuPanel.quit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if(command.equalsIgnoreCase("Options")){
            mainMenuPanel.setVisible(false);
            optionMenu.setVisible(true);
            display.setVisible(false);
            backtogame=false;
        }
        if(command.equalsIgnoreCase("Back")){
            if(backtogame==false){
                mainMenuPanel.setVisible(true);
                optionMenu.setVisible(false);
                display.setVisible(false);
            }else{
                mainMenuPanel.setVisible(false);
                optionMenu.setVisible(false);
                display.setVisible(true);
            }
        }
        if(command.equalsIgnoreCase("New Game")){
            l=new Level(1);
            mainMenuPanel.setVisible(false);
            optionMenu.setVisible(false);
            display.setVisible(true);
            go=true;
        }
        if(command.equalsIgnoreCase("Apply")){
            String[] s = {Cast.inttoString((int) optionMenu.fwdKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.backKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.rightKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.leftKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.spellKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.eatKB.getText().toCharArray()[0])};
            rc.addData(s);
            key.checkSettings(Cast.stringstoInts(rc.getSettings()));
        }
        if(command.equalsIgnoreCase("Default Keybinds")){
            rc.addData(Cast.intstoStrings(defkeys));
            keyprop = Cast.intstoStrings(defkeys);
            optionMenu.fwdKB.setText("w");
            optionMenu.backKB.setText("s");
            optionMenu.rightKB.setText("d");
            optionMenu.leftKB.setText("a");
            optionMenu.spellKB.setText("q");
            optionMenu.eatKB.setText("e");
            key.checkSettings(defkeys);
        }
        if(command.equalsIgnoreCase("save and quit")){
            worlddir = "world.world";
            worldSave = new File(worlddir);
            //TODO some save code
            mainMenuPanel.setVisible(true);
            optionMenu.setVisible(false);
            display.setVisible(false);
            this.dispose();
        }
        if(command.equalsIgnoreCase("settings")){
            backtogame=true;
            mainMenuPanel.setVisible(false);
            optionMenu.setVisible(true);
            display.setVisible(false);
        }
        if(command.equalsIgnoreCase("quit")){
            this.dispose();
        }
    }
}
