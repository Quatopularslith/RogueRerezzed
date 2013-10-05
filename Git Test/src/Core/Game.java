package Core;

import Level.Level;
import Render.TextRender;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Game extends JFrame implements ActionListener {
    
    Keyboard key = new Keyboard();
    Level l;
    JPanel mainMenu = new JPanel();
    JPanel optionsMenu = new JPanel();
    JPanel textRender = new TextRender();
    int[] keys = new int[6];
    
    File configFile = new File("config.dat");
    FileInputStream inStream;
    Properties config = new Properties();
    
     JTextField fwdKB = new JTextField("W", 4);
     JTextField backKB = new JTextField("S", 4);
     JTextField rightKB = new JTextField("D", 4);
     JTextField leftKB = new JTextField("A", 4);
     JTextField spellKB = new JTextField("K", 4);
     JTextField eatKB = new JTextField("L", 4);
    
    public Game(){
        this(750,500);
    }
    public Game(int x, int y){
        super("Rogue Rerezzed");
        try {
            inStream = new FileInputStream(configFile);
            config.load(inStream);
            config.setProperty("fwdKB", "W");
            config.setProperty("backKB", "S");
            config.setProperty("rightKB", "D");
            config.setProperty("leftKB", "A");
            config.setProperty("spellKB", "K");
            config.setProperty("eatKB", "L");
        } catch (Exception ex) {
            System.err.println("LAKJDNFDKLANLKBDKLABF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
        }
        this.add(mainMenu);
        this.add(optionsMenu);
        this.add(textRender);
        this.setSize(x,y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //Initialize JPanels, Button, and Stuff
        textRender.setVisible(false);

        JLabel title = new JLabel("Rogue Rerezzed");
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
    public Keyboard getKey(){
        return getKey();
    }
    private static int turnnum = 0;
    private int tick = 0;
    private Thread gt;
    private boolean running=false;
    public void tick(){
        key.update();
        if(key.turn()==true){
            System.out.println(tick);
            turn();
        }
    }
    public void turn(){
        turnnum++;
        System.out.println(turnnum);
    }
}
