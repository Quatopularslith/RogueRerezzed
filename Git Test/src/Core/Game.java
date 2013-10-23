package Core;

import Assets.LoadArt;
import Level.Level;
import Render.TextRender;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class Game extends JFrame implements ActionListener {
    
    KeyboardInput key;
    Level l;
    JPanel mainMenu = new JPanel();
    JPanel optionsMenu = new JPanel();
    JPanel textRender = new TextRender();
    
    int[] keys = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_S,KeyEvent.VK_A};
    String[] keyprop = new String[6];
    String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB"};
    
    JTextField fwdKB;
    JTextField backKB;
    JTextField rightKB;
    JTextField leftKB;
    JTextField spellKB;
    JTextField eatKB;
    
    File configFile = new File("RougueConfig.dat");
    FileInputStream inStream;
    Properties config = new Properties();
    
    public Game(int x, int y){
        super("Rogue Rerezzed");
        try {
            configFile.createNewFile();
            inStream = new FileInputStream(configFile);
            config.load(inStream);
            for(int i=0;i<props.length;i++){
                if(config.getProperty(props[i])==null){
                    config.setProperty(props[i], Cast.inttoChararr(keys[i]).toString());
                }
                keyprop[i]=config.getProperty(props[i]);
            }
        } catch (IOException ex) {
            System.err.println("LAKJDNFDKLANLKBDKLABF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
        }
        for(int i=0;i<keyprop.length;i++){
            if(keyprop[i]!=null){
                keys[i]=Cast.stringtoInt(keyprop[i]);
            }
        }
        fwdKB = new JTextField(keyprop[0], 4);
        backKB = new JTextField(keyprop[1], 4);
        rightKB = new JTextField(keyprop[2], 4);
        leftKB = new JTextField(keyprop[3], 4);
        spellKB = new JTextField(keyprop[4], 4);
        eatKB = new JTextField(keyprop[5], 4);
        key=new KeyboardInput(keys);
        this.add(mainMenu);
        this.add(optionsMenu);
        this.add(textRender);
        this.setSize(x,y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        textRender.setVisible(false);
        
        LoadArt l = new LoadArt();
        ImageIcon icon = l.createImageIcon("RogueLogo.png","LOGO YOLO");
        this.setIconImage(l.createImageIcon("Quotopularslith.png", "Favicon").getImage());
        
        JLabel title = new JLabel("");
        title.setIcon(icon);
        JButton newGame = new JButton("New Game");
        JButton loadGame = new JButton("Load Game");
        JButton options = new JButton("Options");
        mainMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainMenu.setVisible(true);
        mainMenu.setSize(x,y);

        mainMenu.add(title);
        mainMenu.add(newGame);
        mainMenu.add(loadGame);
        mainMenu.add(options);

        JLabel fwdKBL = new JLabel("Forward Directional Key Bind");
        JLabel backKBL = new JLabel("Backwards Directional Key Bind");
        JLabel rightKBL = new JLabel("Right Directioanl Key Bind");
        JLabel leftKBL = new JLabel("Left Directional Key Bind");
        JLabel spellKBL = new JLabel("Use Spell Key Bind");
        JLabel eatKBL = new JLabel("Eat Food Key Bind");
        JButton apply = new JButton("Apply");
        JButton back = new JButton("Back");
        optionsMenu.setVisible(false);
        optionsMenu.setSize(x,y);

        optionsMenu.add(fwdKBL);
        optionsMenu.add(fwdKB);
        optionsMenu.add(backKBL);
        optionsMenu.add(backKB);
        optionsMenu.add(rightKBL);
        optionsMenu.add(rightKB);
        optionsMenu.add(leftKBL);
        optionsMenu.add(leftKB);
        optionsMenu.add(spellKBL);
        optionsMenu.add(spellKB);
        optionsMenu.add(eatKBL);
        optionsMenu.add(eatKB);
        optionsMenu.add(apply);
        optionsMenu.add(back);
        
        addKeyListener(key);
        newGame.addActionListener(this);
        options.addActionListener(this);
        back.addActionListener(this);
    }
    //Set what happens when JButton "options" is clicked
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if(command.equalsIgnoreCase("Options")){
            mainMenu.setVisible(false);
            optionsMenu.setVisible(true);
        }
        if(command.equalsIgnoreCase("Back")){
            mainMenu.setVisible(true);
            optionsMenu.setVisible(false);
        }
        if(command.equalsIgnoreCase("New Game")){
            l=new Level(1);
            mainMenu.setVisible(false);
            optionsMenu.setVisible(false);
            textRender.setVisible(true);
        }
        if(command.equalsIgnoreCase("Apply")){
            config.setProperty("fwdKB", fwdKB.getText());
            config.setProperty("backKB", backKB.getText());
            config.setProperty("rightKB", rightKB.getText());
            config.setProperty("leftKB", leftKB.getText());
            config.setProperty("spellKB", spellKB.getText());
            config.setProperty("eatKB", eatKB.getText());
        }
    }
    public Level getCurrentLevel(){
        return l;
    }
    public KeyboardInput getKey(){
        return key;
    }
}
