/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package input;

import core.Rogue;
import dungeon.Level;
import entity.player.Player;

/**
 * listener for MButtons
 * @author Torri
 */
public class MButtonInput {
    public static boolean backtogame;
    public void clicked(String command){
        if(command.equalsIgnoreCase("New Game")){
            Player.pinv = null;
            Player.xplevels = 1;
            Player.xp = 0;
            Player.kills = 0;
            Level.numLevels=0;
            Rogue.setLevel(new Level(1));
            Rogue.mm.d=new ui.Display();
            Rogue.mm.add(Rogue.mm.d);
            Rogue.mm.d.setSize(750, 500);
            Rogue.mm.d.optionsD.addActionListener(Rogue.mm.bi);
            Rogue.mm.d.save.addActionListener(Rogue.mm.bi);
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(false);
            Rogue.mm.d.setVisible(true);
        }else if(command.equalsIgnoreCase("Options")){
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            backtogame=false;
        }else if(command.equalsIgnoreCase("Quit")){
            Rogue.mm.dispose();
        }
    }
}
