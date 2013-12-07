package entity.player;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.Direction;
import entity.RogueEntity;
import entity.item.Item;
import entity.mob.RogueHostileEntity;
import render.RenderPanel;
import render.Sprite;
import ui.GamePlay;

/**
 * Hello! This is you!
 * @author Torri
 */
public class Player extends RogueEntity{
    public static boolean dead=false;
    public int maxMana;//mana=magic points
    public double mana;
    public static int kills;
    boolean attack = false;
    public static int currinv = 0;
    public static Item[] pinv;
    public static int xp;
    public static int xplevels;
    public static int rep;
    public static int gold;
    private Item[] pinv1;
    public Player(Level l1){
        super(l1);
        if(pinv==null){
            pinv = new Item[10];
            for(int i=0;i<pinv.length;i++){
                pinv[i]=new Item(0,this,l);
            }
        }
        for(int k=0;k<pinv.length;k++){
            if(pinv[k].name.equalsIgnoreCase("empty")){
                Player.currinv=k;
                break;
            }
        }
        maxDefence=(xplevels);
        maxAtt=2*xplevels;
        maxMana=100+(xplevels);
        mana=(int) (maxMana/1.5);
        maxhealth=100+(xplevels);
        health=(float) (maxhealth/1.5);
        for (Item inv : pinv) {
            inv.update();
            if (inv.equip == true) {
                this.maxhealth += inv.stats[3];
                this.maxMana += inv.stats[2];
                this.maxDefence += inv.stats[1];
                this.maxAtt += inv.stats[0];
            }
        }
        pinv1=pinv;
        this.sp = new Sprite("Player");
        Room r = l1.getRoom(0);
        spawn(r);
    }
    @Override
    public void death(){
        dead=true;
    }
    @Override
    public void turn(){
        l=Rogue.getLevel();
        if(health<=0){
            dead=true;
        }else if(health<maxhealth){
            health+=0.1;
        }
        if(xplevels==0) xplevels = 1;
        if(xp%(10*xplevels)==0 && xp>1){
            xp=0;
            xplevels++;
            health=maxhealth;
            mana=maxMana;
        }
        if(pinv1!=pinv){
            maxAtt=2*xplevels;
            maxMana=100+(xplevels);
            maxhealth=100+(xplevels);
            maxDefence=(xplevels);
            boolean curr=false;
            for (int i=0;i<pinv.length;i++) {
                if(pinv[i]!=null){
                    if(pinv[i].name.equalsIgnoreCase("EMPTY") && !curr){
                        currinv=i;
                        curr=true;
                    }else if(pinv[i].equip==true){
                        this.maxhealth+=pinv[i].stats[3];
                        this.maxMana+=pinv[i].stats[2];
                        this.maxDefence+=pinv[i].stats[1];
                        this.maxAtt+=pinv[i].stats[0];
                    }
                }
            }
            pinv1=pinv;
        }
        for (int j=0;j<l.getItems().size();j++) {
            Item i = l.getItems().get(j);
            if(i.x==this.x && i.y==this.y && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                GamePlay.pickup = i;
            }
        }
        attack=false;
        if(Rogue.mm.ki.keyBind[0]){//up
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y-1){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x && i.y==this.y-1 && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.UP);
            }
        }else if(Rogue.mm.ki.keyBind[1]){//down
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y+1){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x && i.y==this.y+1 && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.DOWN);
            }
        }else if(Rogue.mm.ki.keyBind[2]){//right
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x+1 && re.y==this.y){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x+1 && i.y==this.y && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.RIGHT);
            }
        }else if(Rogue.mm.ki.keyBind[3]){//left
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x-1 && re.y==this.y){
                    re.damage(this);
                    if(re.health<=0){
                        mana++;
                        kills++;
                        xp++;
                        re.death();
                    }
                    attack=true;
                }
            }
            if(!attack){
                for (int j=0;j<l.getItems().size();j++) {
                    Item i = l.getItems().get(j);
                    if(i.x==this.x-1 && i.y==this.y && !pinv[currinv].equals(i) && pinv[currinv]!=null){
                        GamePlay.pickup = i;
                    }
                }
                this.move(Direction.LEFT);
            }
        }
    }
}
