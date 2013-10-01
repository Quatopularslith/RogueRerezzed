package Core;

import Level.Level;
import Render.TextRender;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class Game extends JFrame implements ActionListener,Runnable {
    
    Keyboard key = new Keyboard();
    Level l;
    JPanel mainMenu = new JPanel();
    JPanel optionsMenu = new JPanel();
    JPanel textRender = new TextRender();
    int[] keys = new int[6];
    
    File configFile = new File("config.dat");
    FileInputStream inStream;
    Properties config = new Properties();
    
    public Game(){
        this(750,500);
    }
    public Game(int x, int y){
        super("Rogue Rerezzed");
        try {
            inStream = new FileInputStream(configFile);
            config.load(inStream);
            config.setProperty("fwdKB", "w");
            config.setProperty("backKB", "s");
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.add(mainMenu);
        this.add(optionsMenu);
        this.add(textRender);
        this.setSize(x,y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        start();
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

        JTextField fwdKB = new JTextField("w", 4);
        JTextField backKB = new JTextField("s", 4);
        JTextField rightKB = new JTextField("d", 4);
        JTextField leftKB = new JTextField("a", 4);
        JTextField spellKB = new JTextField("k", 4);
        JTextField eatKB = new JTextField("l", 4);
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
    public synchronized void start(){
        running = true;
        gt = new Thread(this,"Game");
        gt.start();
    }
    public synchronized void stop(){
        running = false;
        try {
            gt.join();
        } catch (InterruptedException ex) {
            System.err.println("LOLFISHES");
        }
    }
    @Override
    public void run() {
        long lt = System.nanoTime(),now;
        final double ns = 1000000000.0/60.0;
        double delta = 0;
        while(running==true){
            now = System.nanoTime();
            delta += (now-lt)/ns;
            while(delta >= 1){
                tick();
                delta--;
            }
        }
        stop();
    }
}
