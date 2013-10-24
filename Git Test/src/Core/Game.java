package Core;

import Assets.LoadArt;
import Level.Level;
import Render.TextRender;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Game extends JFrame implements ActionListener {
    
    KeyboardInput key;
    Level l;
    JPanel textRender = new TextRender();
    
    int[] keys = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_S,KeyEvent.VK_A};
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
            FileInputStream inStream = new FileInputStream(configFile);
            config = new Properties();
            config.load(inStream);
            for(int i=0;i<props.length;i++){
                if(config.getProperty(props[i])==null){
                    config.setProperty(props[i], Cast.inttoString(keys[i]));
                }
                keyprop[i]=config.getProperty(props[i]);
            }
            inStream.close();
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
            config.setProperty("fwdKB", optionMenu.fwdKB.getText());
            config.setProperty("backKB", optionMenu.backKB.getText());
            config.setProperty("rightKB", optionMenu.rightKB.getText());
            config.setProperty("leftKB", optionMenu.leftKB.getText());
            config.setProperty("spellKB", optionMenu.spellKB.getText());
            config.setProperty("eatKB", optionMenu.eatKB.getText());
            saveConfig();
        }
    }
    public Level getCurrentLevel(){
        return l;
    }
    public KeyboardInput getKey(){
        return key;
    }
    public void saveConfig(){
        try {
            FileOutputStream out = new FileOutputStream(configFile);
            config.store(out,"Properties settings");
            out.close();
            config.list(System.out);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
}
