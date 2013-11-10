package Core;

import Assets.LoadArt;
import Entity.Player;
import Level.Level;
import Render.Display;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
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
    String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB","invKB"};
    
    File configFile = new File("RogueConfig.dat");
    File worldSave;
    String worlddir;
    Properties config;
    RogueConfig rc = new RogueConfig(props);
    
    OptionMenuPanel optionMenu;
    MainMenuPanel mainMenuPanel;
    
    public MainMenu(int x, int y){
        super("Rogue Rerezzed");
        this.setFocusable(true);
        
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
        display.jButton1.addActionListener(this);
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
            l.addPlayer(new Player(50,50));
            mainMenuPanel.setVisible(false);
            optionMenu.setVisible(false);
            display.setVisible(true);
            go=true;
        }
        if(command.equalsIgnoreCase("Apply")){
            String[] s = {Cast.inttoString((int) optionMenu.fwdKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.backKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.rightKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.leftKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.spellKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.eatKB.getText().toCharArray()[0]),Cast.inttoString((int) optionMenu.invKB.getText().toCharArray()[0])};
            rc.addData(s);
//            config.setProperty("fwdKB", Cast.inttoString((int) optionMenu.fwdKB.getText().toCharArray()[0]));
//            config.setProperty("backKB", Cast.inttoString((int) optionMenu.backKB.getText().toCharArray()[0]));
//            config.setProperty("rightKB", Cast.inttoString((int) optionMenu.rightKB.getText().toCharArray()[0]));
//            config.setProperty("leftKB", Cast.inttoString((int) optionMenu.leftKB.getText().toCharArray()[0]));
//            config.setProperty("spellKB", Cast.inttoString((int) optionMenu.spellKB.getText().toCharArray()[0]));
//            config.setProperty("eatKB", Cast.inttoString((int) optionMenu.eatKB.getText().toCharArray()[0]));
//            config.setProperty("invKB", Cast.inttoString((int) optionMenu.invKB.getText().toCharArray()[0]));
            for(int i=0;i<keyprop.length;i++){
                keyprop[i]=rc.getSettings()[i];
                if(keyprop[i]!=null){
                    keys[i]=Cast.stringtoInt(keyprop[i]);
                }
            }
            key.checkSettings(keys);
            saveConfig();
        }
        if(command.equalsIgnoreCase("Default Keybinds")){
//            try (FileInputStream inStream = new FileInputStream(configFile)) {
//                config = new Properties();
//                config.load(inStream);
//                for(int i=0;i<props.length;i++){
//                    if(config.getProperty(props[i])==null){
//                        config.setProperty(props[i], Cast.inttoString(defkeys[i]));
//                    }
//                    keyprop[i]=config.getProperty(props[i]);
//                }
//            } catch (FileNotFoundException ex) {
//                System.err.println("NOOOOOJAFL DHFKL AJKLHDFLKJASLDHAJKLSHDLKFJ EWORLAKDFNLSKHJ NO. "+ex.toString());
//            } catch (IOException ex) {
//                System.err.println("NOOOOOJAFL DHFKL AJKLHDFLKJASLDHAJKLSHDLKFJ EWORLAKDFNLSKHJ NO. "+ex.toString());
//            }
            optionMenu.fwdKB.setText("W");
            optionMenu.backKB.setText("S");
            optionMenu.rightKB.setText("D");
            optionMenu.leftKB.setText("A");
            optionMenu.spellKB.setText("K");
            optionMenu.eatKB.setText("L");
            optionMenu.invKB.setText("I");
            optionMenu.potionKB.setText("J");
            key.checkSettings(defkeys);
            saveConfig();
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
        if(command.equalsIgnoreCase("jbutton1")){
            display.gp.update();
        }
        if(command.equalsIgnoreCase("quit")){
            this.dispose();
        }
    }
    private void saveConfig(){
        try {
            try (FileOutputStream out = new FileOutputStream(configFile)) {
                config.store(out,"Properties settings");
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
}
