package Core;

import Level.Level;
import Render.TextRender;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements ActionListener,KeyListener,Runnable {
    
    Level l;
    JPanel mainMenu = new JPanel();
    JPanel optionsMenu = new JPanel();
    JPanel textRender = new TextRender();
    public Game(){
        this(750,500);
    }
    public Game(int x, int y){
        super("Rogue Rerezzed");
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

        this.addKeyListener(this);
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
    private static int turnnum = 0;
    private int tick = 0;
    private Thread gt;
    private boolean running=false,turn=false;
    public void tick(){
        turn=nextTurn();
        if(turn==true){
            System.out.println(tick);
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
            System.out.println("LOLFISHES");
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
    public int upn=KeyEvent.VK_UP,downn=KeyEvent.VK_DOWN,leftn=KeyEvent.VK_LEFT,rightn=KeyEvent.VK_RIGHT,spelln=KeyEvent.VK_Q,eatn=KeyEvent.VK_E,out=0;// 38 = up, 37 = left, 39 = right, 40 = down
    public boolean up=false,down=false,left=false,right=false,spell=false,eat=false;
    public boolean[] keys = new boolean[150];
    private boolean g=true;
    public boolean nextTurn() {
        try{
            up = keys[upn];
            down = keys[downn];
            left = keys[leftn];
            right = keys[rightn];
            spell = keys[spelln];
            eat = keys[eatn];
            if(up==true || down==true || left==true || right==true || spell==true || eat==true){
                g=true;
            }else{
                g=false;
            }
        }catch(Exception e){
            System.out.println("YOU GOT AN ERROR! HAHAHAHHAHAHHAHA!"+e.getMessage());
        }
        return g;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        System.err.println(e.getKeyCode());
    }
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        System.out.println(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
