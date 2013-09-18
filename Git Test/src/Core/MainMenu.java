package Core;

import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
    boolean mainMenuVis = true;
    boolean optMenuVis = false;
  public MainMenu() {
    
    JPanel mainMenu = new JPanel();
    JLabel title = new JLabel("Rogue Rerezzed");
    JButton newGame = new JButton("New Game");
    JButton loadGame = new JButton("Load Game");
    JButton options = new JButton("Options");
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        if(command.equals("Options")){
            optMenuVis=true;
        }
    }
}
