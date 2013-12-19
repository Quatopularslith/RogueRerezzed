package input;

import core.Rogue;
import dungeon.Level;
import entity.RogueEntity;
import entity.item.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        if(command.equalsIgnoreCase("Debug Menu")){
            Rogue.mm.omp.setVisible(false);
            Rogue.mm.dmp.setVisible(true);
        }
        if(command.equalsIgnoreCase("Enter")){
            String pass = ui.DebugMPassword.dmpass.getText();
            if(pass.equalsIgnoreCase("JigglyMuffin")){
                Rogue.mm.dm = new DebugMenu();
                Rogue.mm.dm.chooseEntitySpawnBtn.addActionListener(this);
                Rogue.mm.dm.chooseItemSpawnBtn.addActionListener(this);
                Rogue.mm.dm.chooseLvlBtn.addActionListener(this);
                Rogue.mm.dmp.setVisible(false);
                Rogue.mm.omp.setVisible(true);
            }else{
                Rogue.mm.dmp.setVisible(false);
                Rogue.mm.omp.setVisible(true);
            }
        }
        if(command.equalsIgnoreCase("Spawn Entity")){
            int item;
            item = Rogue.mm.dm.chooseEntitySpawn.getSelectedIndex();

            RogueEntity e1 = new RogueEntity(Rogue.getCurrentLevel());
            switch(item){
                case 0:
                    e1 = new entity.mob.Snake(1, Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
                case 1:
                    e1 = new entity.mob.Bandit(1, Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
                case 2:
                    e1 = new entity.mob.Fish(1, Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
                case 3:
                    e1 = new entity.mob.MortuusTrabajos(1, Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
                case 4:                    
                    e1 = new entity.mob.Quatopularslith(1, Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
                case 5:
                    e1 = new entity.mob.Goblin(1, Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
                case 6:
                    e1 = new entity.npc.Trader(Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
                case 7:
                    e1 = new entity.npc.Warrior(Rogue.getCurrentLevel().getRoom(0), Rogue.getCurrentLevel());
                    e1.x=Rogue.getCurrentLevel().getPlayer().x+1;
                    e1.y=Rogue.getCurrentLevel().getPlayer().y+1;
                    break;
            }
            Rogue.getCurrentLevel().addEntity(e1);
            Rogue.mm.gp.update();
        }
        if(command.equalsIgnoreCase("Spawn Item")){
            int item;
            item = Rogue.mm.dm.chooseItemSpawn.getSelectedIndex();
            switch(item){
                case 0:
                    Rogue.getCurrentLevel().getPlayer().inv[Rogue.getCurrentLevel().getPlayer().currinv]=new Item(3,Rogue.getCurrentLevel().getPlayer(),10,Rogue.getCurrentLevel());
                    break;
                case 1:
                    Rogue.getCurrentLevel().getPlayer().inv[Rogue.getCurrentLevel().getPlayer().currinv]=new Item(1,Rogue.getCurrentLevel().getPlayer(),10,Rogue.getCurrentLevel());
                    break;
                case 2:
                    Rogue.getCurrentLevel().getPlayer().inv[Rogue.getCurrentLevel().getPlayer().currinv]=new Item(2,Rogue.getCurrentLevel().getPlayer(),10,Rogue.getCurrentLevel());
                    break;
                case 3:
                    Rogue.getCurrentLevel().getPlayer().gold++;
                    break;
            }
            Rogue.getCurrentLevel().getPlayer().updateStats();
            Rogue.mm.gp.update();
        }
        if(command.equalsIgnoreCase("Generate")){
            Rogue.setLevel(Rogue.getCurrentLevel().getMode(),Rogue.getCurrentLevel().getType(),Level.renderlevel);
            Rogue.mm.gp.update();
        }
    }
}
