
package input;

import core.GameLoop;
import core.Rogue;
import dungeon.LevelMode;
import dungeon.LevelType;
import entity.item.Item;
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
//        System.out.println(command+" @ "+parent.getClass().toString());
        //Main Menu Code
        if(command.equalsIgnoreCase("New Game") && parent==Rogue.mm.mmp){
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.ngp.setVisible(true);
            return;
        }
        if(command.equalsIgnoreCase("Options") && parent==Rogue.mm.mmp){
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            backtogame=false;
            return;
        }
        if(command.equalsIgnoreCase("Quit") && parent==Rogue.mm.mmp){
            GameLoop.pause();
            if(Rogue.mm.dm!=null) Rogue.mm.dm.dispose();
            Rogue.mm.dispose();
            return;
        }
        //New Game Panel
        if(command.equalsIgnoreCase("Turn-Based Story mode") && parent==Rogue.mm.ngp){
            Rogue.resetLevels();
            Rogue.setLevel(LevelMode.STORY, LevelType.TURN, 0);
            Rogue.mm.ngp.setVisible(false);
            Rogue.mm.gp.setVisible(true);
            Rogue.mm.gp.update();
            return;
        }
        if(command.equalsIgnoreCase("Evolved Story mode") && parent==Rogue.mm.ngp){
            Rogue.resetLevels();
            Rogue.setLevel(LevelMode.STORY, LevelType.EVOLVED, 0);
            Rogue.mm.ngp.setVisible(false);
            Rogue.mm.gp.setVisible(true);
            Rogue.mm.gp.update();
            return;
        }
        if(command.equalsIgnoreCase("Back") && parent==Rogue.mm.ngp){
            Rogue.mm.ngp.setVisible(false);
            Rogue.mm.mmp.setVisible(true);
        }
        //Navigation Buttons
        if(command.equalsIgnoreCase("quit") && parent==Rogue.mm.gp){
            //TODO some save code
            Rogue.mm.gp.setVisible(false);
            Rogue.mm.mmp.setVisible(true);
            return;
        }
        if(command.equalsIgnoreCase("settings") && parent==Rogue.mm.gp){
            backtogame=true;
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.gp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            return;
        }
        //Pickup Dialogue
        if(command.equalsIgnoreCase("pick up") && parent==Rogue.mm.gp){
            if(GamePlay.pickup!=null && Rogue.getCurrentLevel().getPlayer().currinv!=Rogue.getCurrentLevel().getPlayer().pinv.length){
                GamePlay.pickup.setParent(Rogue.getCurrentLevel().getPlayer());
                Rogue.getCurrentLevel().getPlayer().pinv[Rogue.getCurrentLevel().getPlayer().currinv]=GamePlay.pickup;
                Rogue.getCurrentLevel().getPlayer().updateStats();
                GamePlay.pickup.death();
                Rogue.mm.gp.update();
            }
            refresh(Rogue.mm.gp);
            return;
        }
        if(command.equalsIgnoreCase("leave it") && parent==Rogue.mm.gp){
            GamePlay.pickup=null;
            Rogue.mm.gp.update();
            refresh(Rogue.mm.gp);
            return;
        }
        //Inventory
        for(int i=0;i<Rogue.mm.gp.equip.length;i++){
            if(command.equalsIgnoreCase("Drop"+i) && parent==Rogue.mm.gp){
                Rogue.getCurrentLevel().getPlayer().pinv[i].drop();
                Rogue.getCurrentLevel().getPlayer().pinv[i]=new Item(0,Rogue.getCurrentLevel().getPlayer(),0,Rogue.getCurrentLevel());
                Rogue.getCurrentLevel().getPlayer().updateStats();
                Rogue.mm.gp.update();
                return;
            }
            if(command.equalsIgnoreCase("Equip"+i) && parent==Rogue.mm.gp){
                if(Rogue.getCurrentLevel().getPlayer().pinv[i].equip) continue;
                Rogue.getCurrentLevel().getPlayer().pinv[i].equip=true;
                Rogue.getCurrentLevel().getPlayer().updateStats();
                Rogue.mm.gp.update();
                return;
            }
        }
        //Stat Menu
        if(command.equalsIgnoreCase("Return") && parent==Rogue.mm.sm){
            Rogue.resetLevels();
            if(!Rogue.mm.sm.isVisible()) return;
            Rogue.mm.sm.setVisible(false);
            Rogue.mm.mmp.setVisible(true);
            return;
        }
        //Trading
        for(int i=0;i<Rogue.mm.gp.tradeMB.length;i++){
            if(command.equalsIgnoreCase("Trade"+i) && parent==Rogue.mm.gp){
                if(Rogue.getCurrentLevel().getPlayer().gold>=Rogue.mm.gp.currTrade.prices[i] && Rogue.getCurrentLevel().getPlayer().currinv!=Rogue.getCurrentLevel().getPlayer().pinv.length){
                    Rogue.getCurrentLevel().getPlayer().gold-=Rogue.mm.gp.currTrade.prices[i];
                    Rogue.getCurrentLevel().getPlayer().rep++;
                    Rogue.getCurrentLevel().getPlayer().pinv[Rogue.getCurrentLevel().getPlayer().currinv]=Rogue.mm.gp.currTrade.inv[i];
                    Rogue.mm.gp.currTrade.inv[i]=new Item(0,null,0,Rogue.getCurrentLevel());
                    Rogue.getCurrentLevel().getPlayer().updateStats();
                    Rogue.mm.gp.update();
                }
                Rogue.mm.gp.currTrade.refreshTrade();
                return;
            }
        }
    }
    private void refresh(JPanel p){
        p.setVisible(false);
        p.setVisible(true);
    }
}
