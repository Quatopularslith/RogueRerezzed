package Core;

import javax.swing.*;

public class MainMenu extends JFrame {
  public MainMenu() {
  	
    boolean mainMenuVis = true;
    boolean optMenuVis = false;
    
    JPanel mainMenu = new JPanel();
    JLabel title = new JLabel("Rogue Rerezzed");
    JButton newGame = new JButton("New Game");
    JButton loadGame = new JButton("Load Game");
    JButton options = new JButton("Options");
    mainMenu.setVisible(mainMenuVis);
    
    JPanel optionsMenu = new JPanel();
    JTextField fwdKB = new JTextField("Forward KeyBind");
    JTextField backKB = new JTextField("Backwards KeyBind");
    JTextField rightKB = new JTextField("Right KeyBind");
    JTextField leftKB = new JTextField("Left KeyBind");
    JTextField spellKB = new JTextField("Spell KeyBind");
    JTextField eatKB = new JTextField("Eating KeyBind");
    mainMenu.setVisible(optMenuVis);
    
  }
}
