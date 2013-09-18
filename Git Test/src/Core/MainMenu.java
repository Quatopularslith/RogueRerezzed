package Core;

import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
    
    boolean mainMenuVis = true;
    boolean optMenuVis = false;
 
  public MainMenu() {
      
    //Initialize JPanels, Button, and Stuff
    
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
