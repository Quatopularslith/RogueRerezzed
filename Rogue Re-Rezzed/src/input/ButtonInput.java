package input;

import core.Rogue;
import dungeon.Level;
import entity.item.Item;
import entity.player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import render.RenderPanel;
import ui.DebugMenu;
import ui.Menu;

/**
 * Handles the buttons of the UIs
 * @author Torri &  Mnenmenth
 */
public class ButtonInput implements ActionListener{
    public final String[] defkeys = {"87","83","68","65","81","69"};//wsdaqe
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Back")){
            if(MButtonInput.backtogame==false){
                Rogue.mm.mmp.setVisible(true);
                Rogue.mm.omp.setVisible(false);
            }else{
                Rogue.mm.mmp.setVisible(false);
                Rogue.mm.omp.setVisible(false);
                Rogue.mm.gp.setVisible(true);
            }
        }
        if(command.equalsIgnoreCase("Apply")){
            char[] inc = {Rogue.mm.omp.fwdKB.getText().toUpperCase().toCharArray()[0],
                Rogue.mm.omp.backKB.getText().toUpperCase().toCharArray()[0],
                Rogue.mm.omp.rightKB.getText().toUpperCase().toCharArray()[0],
                Rogue.mm.omp.leftKB.getText().toUpperCase().toCharArray()[0],
                Rogue.mm.omp.spellKB.getText().toUpperCase().toCharArray()[0],
                Rogue.mm.omp.eatKB.getText().toUpperCase().toCharArray()[0]
            };
            int[] in = new int[inc.length];
            for(int i=0;i<in.length;i++){
                in[i]=(int) inc[i];
            }
            String[] s = new String[in.length];
            for(int i=0;i<in.length;i++){
                s[i]=Integer.toString(in[i]);
            }
            Menu.rp.setData(s);
            Rogue.mm.ki.checkSettings(Menu.rp.getSettings());
        }
        if(command.equalsIgnoreCase("Default Keybinds")){
            Menu.rp.setData(defkeys);
            Rogue.mm.omp.fwdKB.setText("W");
            Rogue.mm.omp.backKB.setText("S");
            Rogue.mm.omp.rightKB.setText("D");
            Rogue.mm.omp.leftKB.setText("A");
            Rogue.mm.omp.spellKB.setText("Q");
            Rogue.mm.omp.eatKB.setText("E");
            Rogue.mm.ki.checkSettings(defkeys);
        }
        if(command.equalsIgnoreCase("save and quit")){
            //TODO some save code
            Rogue.mm.gp.setVisible(false);
            Rogue.mm.mmp.setVisible(true);
        }
        if(command.equalsIgnoreCase("settings")){
            MButtonInput.backtogame=true;
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            Rogue.mm.gp.setVisible(false);
        }
        if(command.equalsIgnoreCase("Debug Menu")){
            Rogue.mm.omp.setVisible(false);
            Rogue.mm.dmp.setVisible(true);
        }
        if(command.equalsIgnoreCase("Enter")){
            String pass = ui.DebugMPassword.dmpass.getText();
            if(pass.equalsIgnoreCase("JigglyMuffin")){
                Rogue.mm.dm = new DebugMenu();
                Rogue.mm.dmp.setVisible(false);
                Rogue.mm.omp.setVisible(true);
            }else{
                Rogue.mm.dmp.setVisible(false);
                Rogue.mm.omp.setVisible(true);
            }
        }
        if(command.equalsIgnoreCase("Spawn Entity")){
            Rogue.mm.dm.chooseEntitySpawn.getSelectedItem();
        }
        if(command.equalsIgnoreCase("Spawn Item")){
            Rogue.mm.dm.chooseItemSpawn.getSelectedItem();
        }
        if(command.equalsIgnoreCase("Generate")){
            Rogue.setLevel(new Level());
            Rogue.mm.gp.update();
        }
        if(command.equalsIgnoreCase("Return")){
            Rogue.mm.mmp.setVisible(true);
            Rogue.mm.sm.setVisible(false);
        }
//        if(Rogue.mm.gp!=null){
//            for(int i=0;i<Rogue.mm.d.invp.drop.length;i++){
//                if(command.equalsIgnoreCase("Drop "+i)){
//                    Player.pinv[i]=new Item(0,Rogue.getLevel().getPlayer(),Rogue.getLevel());
//                    Player.pinv[i].drop();
//                    refresh(Rogue.mm.d.invp);
//                    Rogue.mm.d.gp.update();
//                }
//            }
//            for(int i=0;i<Rogue.mm.d.invp.equip.length;i++){
//                if(command.equalsIgnoreCase("Equip "+i)){
//                    Player.pinv[i].equip=true;
//                    Rogue.mm.d.invp.update();
//                    refresh(Rogue.mm.d.invp);
//                    Rogue.mm.d.gp.update();
//                }
//            }
//        }
//        if(command.equalsIgnoreCase("pick up")){
//            if(RenderPanel.pickup!=null){
//                RenderPanel.pickup.setParent(Rogue.getLevel().getPlayer());
//                Player.pinv[Player.currinv]=RenderPanel.pickup;
//                RenderPanel.pickup.death();
//                Rogue.mm.d.gp.update();
//            }
//            refresh(Rogue.mm.d);
//        }
//        if(command.equalsIgnoreCase("leave it")){
//            RenderPanel.pickup=null;
//            Rogue.mm.d.gp.update();
//            refresh(Rogue.mm.d);
//        }
    }
    private void refresh(JPanel p){
        p.setVisible(false);
        p.setVisible(true);
    }
}
