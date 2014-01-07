
package input;

import core.GameLoop;
import core.Rogue;
import dungeon.Level;
import dungeon.LevelMode;
import dungeon.LevelType;
import entity.item.Item;
import java.awt.Component;
import javax.swing.JPanel;
import ui.GamePlay;
import util.RogueSave;

/**
 * listener for MButtons
 * @author Torri
 */
public class MButtonInput {
    public static boolean backtogame;
    public void clicked(String command,Component parent){
        //Main Menu Code
        if(parent==Rogue.mm.mmp){
            if(command.equalsIgnoreCase("New Game")){
                Rogue.mm.mmp.setVisible(false);
                Rogue.mm.ngp.setVisible(true);
                return;
            }
            if(command.equalsIgnoreCase("Options")){
                Rogue.mm.mmp.setVisible(false);
                Rogue.mm.omp.setVisible(true);
                backtogame=false;
                return;
            }
            if(command.equalsIgnoreCase("Quit")){
                GameLoop.pause();
                Rogue.sendStats();
                if(Rogue.mm.dm!=null) Rogue.mm.dm.dispose();
                Rogue.mm.dispose();
                return;
            }
        }
        //New Game Panel
        if(parent==Rogue.mm.ngp){
            if(command.equalsIgnoreCase("Turn-Based Story mode")){
                Rogue.resetLevels();
                Rogue.setLevel(LevelMode.STORY, LevelType.TURN, 0);
//                RogueSave rs = new RogueSave(0);
//                Level load = rs.loadLevel();
//                if(load!=null) Rogue.setLevel(load);
                Rogue.mm.ngp.setVisible(false);
                Rogue.mm.gp.setVisible(true);
                Rogue.mm.gp.update();
                return;
            }
            if(command.equalsIgnoreCase("Evolved Story mode")){
                Rogue.resetLevels();
                Rogue.setLevel(LevelMode.STORY, LevelType.EVOLVED, 0);
                Rogue.mm.ngp.setVisible(false);
                Rogue.mm.gp.setVisible(true);
                Rogue.mm.gp.update();
                return;
            }
            if(command.equalsIgnoreCase("Back")){
                Rogue.mm.ngp.setVisible(false);
                Rogue.mm.mmp.setVisible(true);
            }
        }
        //Gameplay buttons
        if(parent==Rogue.mm.gp){
            //Navigation Buttons
            if(command.equalsIgnoreCase("quit")){
                //TODO some save code
                RogueSave rs = new RogueSave(0);
                rs.saveLevel(Rogue.getCurrentLevel());
                Rogue.resetLevels();
                Rogue.mm.gp.setVisible(false);
                Rogue.mm.mmp.setVisible(true);
                return;
            }
            if(command.equalsIgnoreCase("settings")){
                backtogame=true;
                Rogue.mm.mmp.setVisible(false);
                Rogue.mm.gp.setVisible(false);
                Rogue.mm.omp.setVisible(true);
                return;
            }
            //Pickup Dialogue
            if(command.equalsIgnoreCase("pick up") && parent==Rogue.mm.gp){
                if(GamePlay.pickup!=null && Rogue.getCurrentLevel().getPlayer().currinv!=Rogue.getCurrentLevel().getPlayer().inv.length){
                    GamePlay.pickup.setParent(Rogue.getCurrentLevel().getPlayer());
                    Rogue.getCurrentLevel().getPlayer().inv[Rogue.getCurrentLevel().getPlayer().currinv]=GamePlay.pickup;
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
                    Rogue.getCurrentLevel().getPlayer().inv[i].drop();
                    Rogue.getCurrentLevel().getPlayer().inv[i]=new Item(0,Rogue.getCurrentLevel().getPlayer(),0,Rogue.getCurrentLevel());
                    Rogue.getCurrentLevel().getPlayer().updateStats();
                    Rogue.mm.gp.update();
                    return;
                }
                if(command.equalsIgnoreCase("Equip"+i) && parent==Rogue.mm.gp){
                    if(Rogue.getCurrentLevel().getPlayer().inv[i].equip) continue;
                    Rogue.getCurrentLevel().getPlayer().inv[i].equip=true;
                    Rogue.getCurrentLevel().getPlayer().updateStats();
                    Rogue.mm.gp.update();
                    return;
                }
            }
            //Trading
            for(int i=0;i<Rogue.mm.gp.tradeMB.length;i++){
                if(command.equalsIgnoreCase("Trade"+i) && parent==Rogue.mm.gp){
                    if(Rogue.getCurrentLevel().getPlayer().gold>=Rogue.mm.gp.currTrade.prices[i] && Rogue.getCurrentLevel().getPlayer().currinv!=Rogue.getCurrentLevel().getPlayer().inv.length){
                        Rogue.getCurrentLevel().getPlayer().gold-=Rogue.mm.gp.currTrade.prices[i];
                        Rogue.getCurrentLevel().getPlayer().rep++;
                        Rogue.getCurrentLevel().getPlayer().inv[Rogue.getCurrentLevel().getPlayer().currinv]=Rogue.mm.gp.currTrade.inv[i];
                        Rogue.mm.gp.currTrade.inv[i]=new Item(0,null,0,Rogue.getCurrentLevel());
                        Rogue.getCurrentLevel().getPlayer().updateStats();
                        Rogue.mm.gp.update();
                    }
                    Rogue.mm.gp.currTrade.refreshTrade();
                    return;
                }
            }
            //Hire
            if(command.equalsIgnoreCase("Hire")){
                if(Rogue.getCurrentLevel().getPlayer().gold>=Rogue.mm.gp.w.cost){
                    Rogue.mm.gp.w.hire=true;
                    Rogue.mm.gp.w.refreshHire();
                }
                return;
            }
        }
        //Stat Menu
        if(command.equalsIgnoreCase("Return") && parent==Rogue.mm.sm){
            GameLoop.pause();
            if(!Rogue.mm.sm.isVisible()) return;
            Rogue.mm.sm.setVisible(false);
            Rogue.mm.mmp.setVisible(true);
            return;
        }
    }
    private void refresh(JPanel p){
        p.setVisible(false);
        p.setVisible(true);
    }
}
