package Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener {
  public MainMenu() {
  	
    boolean mainMenuVis = true;
    boolean optMenuVis = false;
    
    JPanel mainMenu = new JPanel();
    JLabel title = new JLabel();
    JButton newGame = new JButton();
    JButton loadGame = new JButton();
    JButton options = new JButton();
    mainMenu.setVisible(mainMenuVis);
    
    JPanel optionsMenu = new JPanel();
    JTextField fwdKB = new JTextField();
    JTextField backKB = new JTextField();
    JTextField rightKB = new JTextField();
    JTextField leftKB = new JTextField();
    JTextField spellKB = new JTextField();
    JTextField eatKB = new JTextField();
    mainMenu.setVisible(optMenuVis);
    
    
    
  }
}
