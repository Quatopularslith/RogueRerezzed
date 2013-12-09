
package input;

import core.Rogue;
import dungeon.Level;
import entity.item.Item;
import entity.player.Player;
import java.awt.Component;
import javax.swing.JPanel;
import ui.GamePlay;

/**
 * listener for MButtons
 * @author Torri
 */
public class MButtonInput {
    public static boolean backtogame;
    public void clicked(String command,Component parent){
        //Main Menu Code
        if(command.equalsIgnoreCase("New Game") && parent==Rogue.mm.mmp){
            Player.pinv = null;
            Player.xplevels = 1;
            Player.xp = 0;
            Player.kills = 0;
            Level.numLevels=0;
            Rogue.setLevel(new Level(1));
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(false);
            Rogue.mm.gp.setVisible(true);
            Rogue.mm.gp.update();
        }
        if(command.equalsIgnoreCase("Options") && parent==Rogue.mm.mmp){
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            backtogame=false;
        }
        if(command.equalsIgnoreCase("Quit") && parent==Rogue.mm.mmp){
            Rogue.mm.dispose();
        }
        //Navigation Buttons
        if(command.equalsIgnoreCase("quit") && parent==Rogue.mm.gp){
            //TODO some save code
            Rogue.mm.gp.setVisible(false);
            Rogue.mm.mmp.setVisible(true);
        }
        if(command.equalsIgnoreCase("settings") && parent==Rogue.mm.gp){
            backtogame=true;
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            Rogue.mm.gp.setVisible(false);
        }
        //Pickup Dialogue
        if(command.equalsIgnoreCase("pick up") && parent==Rogue.mm.gp){
            if(GamePlay.pickup!=null){
                GamePlay.pickup.setParent(Rogue.getLevel().getPlayer());
                Player.pinv[Player.currinv]=GamePlay.pickup;
                GamePlay.pickup.death();
                Rogue.mm.gp.update();
            }
            refresh(Rogue.mm.gp);
        }
        if(command.equalsIgnoreCase("leave it") && parent==Rogue.mm.gp){
            GamePlay.pickup=null;
            Rogue.mm.gp.update();
            refresh(Rogue.mm.gp);
        }
        //Inventory
        for(int i=0;i<Rogue.mm.gp.equip.length;i++){
            if(command.equalsIgnoreCase("Drop"+i) && parent==Rogue.mm.gp){
                Player.pinv[i].drop();
                Player.pinv[i]=new Item(0,Rogue.getLevel().getPlayer(),0,Rogue.getLevel());
                Rogue.getLevel().getPlayer().updateStats();
                Rogue.mm.gp.update();
            }
            if(command.equalsIgnoreCase("Equip"+i) && parent==Rogue.mm.gp){
                Player.pinv[i].equip=true;
                Rogue.getLevel().getPlayer().updateStats();
                Rogue.mm.gp.update();
            }
        }
        //Stat Menu
        if(command.equalsIgnoreCase("Return") && parent==Rogue.mm.sm){
            Rogue.mm.mmp.setVisible(true);
            Rogue.mm.sm.setVisible(false);
        }
    }
    private void refresh(JPanel p){
        p.setVisible(false);
        p.setVisible(true);
    }
}
