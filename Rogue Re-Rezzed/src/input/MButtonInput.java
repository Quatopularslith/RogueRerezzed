/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package input;

import core.Rogue;
import dungeon.Level;
import entity.player.Player;
import javax.swing.JPanel;
import render.RenderPanel;

/**
 * listener for MButtons
 * @author Torri
 */
public class MButtonInput {
    public static boolean backtogame;
    public void clicked(String command){
        System.out.println(command);
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
        }else if(command.equalsIgnoreCase("pick up")){
            if(RenderPanel.pickup!=null){
                RenderPanel.pickup.setParent(Rogue.getLevel().getPlayer());
                Player.pinv[Player.currinv]=RenderPanel.pickup;
                RenderPanel.pickup.death();
                Rogue.mm.d.gp.update();
            }
            refresh(Rogue.mm.d);
        }else if(command.equalsIgnoreCase("leave it")){
            RenderPanel.pickup=null;
            Rogue.mm.d.gp.update();
            refresh(Rogue.mm.d);
        }
    }
    private void refresh(JPanel p){
        p.setVisible(false);
        p.setVisible(true);
    }
}
