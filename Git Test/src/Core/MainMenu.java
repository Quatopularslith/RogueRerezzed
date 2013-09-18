package Core;

import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
    
    boolean mainMenuVis = true;
    boolean optMenuVis = false;
 
  public MainMenu() {
      MainMenu.setSize(750,500);
      MainMenu.add(mainMenu);
      MainMenu.add(optionsMenu);
      MainMenu.setLookAndFeel();
      MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Initialize JPanels, Button, and Stuff
    
    JPanel mainMenu = new JPanel();
    JLabel title = new JLabel();
    JButton newGame = new JButton();
    JButton loadGame = new JButton();
    JButton options = new JButton();
    mainMenu.setVisible(mainMenuVis);
    mainMenu.setSize(750,500);
    mainMenu.
    
    mainMenu.add()
    
    
    JPanel optionsMenu = new JPanel();
    JTextField fwdKB = new JTextField();
    JTextField backKB = new JTextField();
    JTextField rightKB = new JTextField();
    JTextField leftKB = new JTextField();
    JTextField spellKB = new JTextField();
    JTextField eatKB = new JTextField();
    optionsMenu.setVisible(optMenuVis);
    optionsMenu.setSize(750,500);
    
  }
    
    //Set what happens when JButton "options" is clicked
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if(command.equals("Options")){
            optMenuVis=true;
            mainMenuVis=false;
        }
    }
}
