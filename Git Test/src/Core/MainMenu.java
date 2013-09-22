package Core;

import Render.TextRender;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame implements ActionListener {
    
    JPanel mainMenu = new JPanel();
    JPanel optionsMenu = new JPanel();
    JPanel textRender = new TextRender();
    
  public MainMenu(int x,int y) {
      super("Rogue Rerezzed");
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

    JTextField fwdKB = new JTextField(4);
    JTextField backKB = new JTextField(4);
    JTextField rightKB = new JTextField(4);
    JTextField leftKB = new JTextField(4);
    JTextField spellKB = new JTextField(4);
    JTextField eatKB = new JTextField(4);
    JButton back = new JButton("Back");
    optionsMenu.setVisible(false);
    optionsMenu.setSize(x,y);

    optionsMenu.add(fwdKB);
    optionsMenu.add(backKB);
    optionsMenu.add(rightKB);
    optionsMenu.add(leftKB);
    optionsMenu.add(spellKB);
    optionsMenu.add(eatKB);
    optionsMenu.add(back);

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
            mainMenu.setVisible(false);
            optionsMenu.setVisible(false);
            textRender.setVisible(true);
        }
    }
}
