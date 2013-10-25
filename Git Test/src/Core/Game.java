package Core;

import Assets.LoadArt;
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
import javax.swing.JPanel;

public class Game extends JFrame implements ActionListener {
    
    KeyboardInput key;
    Level l;
    JPanel textRender = new Display();
    
    int[] defkeys = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_S,KeyEvent.VK_A};
    int[] keys = new int[6];
    String[] keyprop = new String[6];
    String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB"};
    
    File configFile = new File("RogueConfig.dat");
    Properties config;
    
    OptionMenuPanel optionMenu;
    MainMenuPanel mainMenuPanel;
    
    public Game(int x, int y){
        super("Rogue Rerezzed");
        try {
            configFile.createNewFile();
            try (FileInputStream inStream = new FileInputStream(configFile)) {
                config = new Properties();
                config.load(inStream);
                for(int i=0;i<props.length;i++){
                    if(config.getProperty(props[i])==null){
                        config.setProperty(props[i], Cast.inttoString(defkeys[i]));
                    }
                    keyprop[i]=config.getProperty(props[i]);
                }
            }
            this.saveConfig();
        } catch (IOException ex) {
            System.err.println("LAKJDNFDKLANLKBDKLABF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
        }
        for(int i=0;i<keyprop.length;i++){
            if(keyprop[i]!=null){
                keys[i]=Cast.stringtoInt(keyprop[i]);
            }
        }
        optionMenu = new OptionMenuPanel();
        mainMenuPanel = new MainMenuPanel();
        optionMenu.setVisible(false);
        mainMenuPanel.setVisible(true);
        this.add(optionMenu);
        this.add(mainMenuPanel);
        
        key=new KeyboardInput(keys);
        
        this.add(textRender);
        this.setSize(x,y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        textRender.setVisible(false);
        
        LoadArt load = new LoadArt();
        ImageIcon icon = load.createImageIcon("RogueLogo.png","LOGO YOLO");
        this.setIconImage(load.createImageIcon("Quotopularslith.png", "Favicon").getImage());
        
        JLabel title = new JLabel("");
        title.setIcon(icon);
        
        mainMenuPanel.setSize(x,y);
        optionMenu.setVisible(false);
        optionMenu.setSize(x,y);
        
        this.addKeyListener(key);
        mainMenuPanel.newGame.addActionListener(this);
        mainMenuPanel.options.addActionListener(this);
        optionMenu.back.addActionListener(this); 
        optionMenu.apply.addActionListener(this);
        optionMenu.defaultKB.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if(command.equalsIgnoreCase("Options")){
            mainMenuPanel.setVisible(false);
            optionMenu.setVisible(true);
        }
        if(command.equalsIgnoreCase("Back")){
            mainMenuPanel.setVisible(true);
            optionMenu.setVisible(false);
        }
        if(command.equalsIgnoreCase("New Game")){
            l=new Level(1);
            mainMenuPanel.setVisible(false);
            optionMenu.setVisible(false);
            textRender.setVisible(true);
        }
        if(command.equalsIgnoreCase("Apply")){
            config.setProperty("fwdKB", Cast.inttoString((int) optionMenu.fwdKB.getText().toCharArray()[0]));
            config.setProperty("backKB", Cast.inttoString((int) optionMenu.backKB.getText().toCharArray()[0]));
            config.setProperty("rightKB", Cast.inttoString((int) optionMenu.rightKB.getText().toCharArray()[0]));
            config.setProperty("leftKB", Cast.inttoString((int) optionMenu.leftKB.getText().toCharArray()[0]));
            config.setProperty("spellKB", Cast.inttoString((int) optionMenu.spellKB.getText().toCharArray()[0]));
            config.setProperty("eatKB", Cast.inttoString((int) optionMenu.eatKB.getText().toCharArray()[0]));
            config.setProperty("invKB", Cast.inttoString((int) optionMenu.invKB.getText().toCharArray()[0]));
            saveConfig();
        }
        if(command.equalsIgnoreCase("Default Keybinds")){
            try (FileInputStream inStream = new FileInputStream(configFile)) {
                config = new Properties();
                config.load(inStream);
                for(int i=0;i<props.length;i++){
                    if(config.getProperty(props[i])==null){
                        config.setProperty(props[i], Cast.inttoString(defkeys[i]));
                    }
                    keyprop[i]=config.getProperty(props[i]);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("NOOOOOJAFL DHFKL AJKLHDFLKJASLDHAJKLSHDLKFJ EWORLAKDFNLSKHJ NO. "+ex.toString());
            } catch (IOException ex) {
                System.err.println("NOOOOOJAFL DHFKL AJKLHDFLKJASLDHAJKLSHDLKFJ EWORLAKDFNLSKHJ NO. "+ex.toString());
            }
            optionMenu.fwdKB.setText("W");
            optionMenu.backKB.setText("S");
            optionMenu.rightKB.setText("D");
            optionMenu.leftKB.setText("A");
            optionMenu.spellKB.setText("K");
            optionMenu.eatKB.setText("L");
            optionMenu.invKB.setText("I");
            optionMenu.potionKB.setText("J");
            saveConfig();
        }
    }
    public Level getCurrentLevel(){
        return l;
    }
    public KeyboardInput getKeys(){
        return key;
    }
    private void saveConfig(){
        try {
            try (FileOutputStream out = new FileOutputStream(configFile)) {
                config.store(out,"Properties settings");
            }
            config.list(System.out);
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
}
