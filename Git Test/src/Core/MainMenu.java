package Core;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
  public MainMenu() {
  	
    boolean mainMenuVis = true;
    boolean optMenuVis = false;
    
    public JPanel mainMenu = new JPanel("Rogue Rerezzed");
    private JLabel title = new JLabel("Rogue Rerezzed");
    private JButton newGame = new JButton("New Game");
    private JButton loadGame = new JButton("Load Game");
    private JButton options = new JButton("Options");
    mainMenu.setVisible(mainMenuVis);
    
    public JPanel optionsMenu = new JPanel("Controls");
    private JTextField fwdKB = new JTextField("Forward KeyBind");
    private JTextField backKB = new JTextField("Backwards KeyBind");
    private JTextField rightKB = new JTextField("Right KeyBind");
    private JTextField leftKB = new JTextField("Left KeyBind");
    private JTextField spellKB = new JTextField("Spell KeyBind");
    private JTextField eatKB = new JTextField("Eating KeyBind");
    mainMenu.setVisible(optMenuVis);
    
    
  }
}
