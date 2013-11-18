/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package input;

import core.Rogue;
import dungeon.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.Menu;

/**
 *
 * @author Torri
 */
public class ButtonInput implements ActionListener{
    private boolean backtogame;
    private final String[] defkeys = {};
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        if(command.equalsIgnoreCase("Options")){
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            backtogame=false;
        }
        if(command.equalsIgnoreCase("Back")){
            if(backtogame==false){
                Rogue.mm.mmp.setVisible(true);
                Rogue.mm.omp.setVisible(false);
            }else{
                Rogue.mm.mmp.setVisible(false);
                Rogue.mm.omp.setVisible(false);
                Rogue.mm.d.setVisible(true);
            }
        }
        if(command.equalsIgnoreCase("New Game")){
            Rogue.setLevel(new Level(20));
            Rogue.mm.d=new ui.Display();
            Rogue.mm.add(Rogue.mm.d);
            Rogue.mm.d.setSize(750, 500);
            Rogue.mm.d.optionsD.addActionListener(this);
            Rogue.mm.d.save.addActionListener(this);
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(false);
            Rogue.mm.d.setVisible(true);
        }
        if(command.equalsIgnoreCase("Apply")){
            int[] in = {Rogue.mm.omp.fwdKB.getText().toLowerCase().toCharArray()[0],Rogue.mm.omp.backKB.getText().toLowerCase().toCharArray()[0],Rogue.mm.omp.rightKB.getText().toLowerCase().toCharArray()[0],Rogue.mm.omp.leftKB.getText().toLowerCase().toCharArray()[0],Rogue.mm.omp.spellKB.getText().toLowerCase().toCharArray()[0],Rogue.mm.omp.eatKB.getText().toLowerCase().toCharArray()[0]};
            String[] s = new String[in.length];
            for(int i=0;i<in.length;i++){
                s[i]=Integer.toString(in[i]);
            }
            Menu.rp.setData(s);
            Rogue.mm.ki.checkSettings(Menu.rp.getSettings());
        }
        if(command.equalsIgnoreCase("Default Keybinds")){
            Menu.rp.setData(defkeys);
            Rogue.mm.omp.fwdKB.setText("w");
            Rogue.mm.omp.backKB.setText("s");
            Rogue.mm.omp.rightKB.setText("d");
            Rogue.mm.omp.leftKB.setText("a");
            Rogue.mm.omp.spellKB.setText("q");
            Rogue.mm.omp.eatKB.setText("e");
            Rogue.mm.ki.checkSettings(defkeys);
        }
        if(command.equalsIgnoreCase("save and quit")){
            //TODO some save code
            Rogue.mm.dispose();
        }
        if(command.equalsIgnoreCase("settings")){
            backtogame=true;
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            Rogue.mm.d.setVisible(false);
        }
        if(command.equalsIgnoreCase("quit")){
            Rogue.mm.dispose();
        }
    }
}