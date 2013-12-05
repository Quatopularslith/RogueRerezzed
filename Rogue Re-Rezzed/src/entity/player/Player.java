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
        maxDefence=0;
        maxAtt=2;
        mana=(int) (50);
        maxMana=(int) (100);
        health=(float) (100);
        maxhealth=(int) (100);
        for (Item pinv1 : pinv) {
            pinv1.update();
            if (pinv1.equip == true) {
                this.maxhealth += pinv1.stats[3];
                this.maxMana += pinv1.stats[2];
                this.maxDefence += pinv1.stats[1];
                this.maxAtt += pinv1.stats[0];
            }
        }
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
        if(xp%(10*xplevels)==0 && xp>1){
            xp=0;
            xplevels++;
            health=maxhealth;
            mana=maxMana;
        }
        maxAtt=2;
        maxMana=100;
        maxhealth=100;
        maxDefence=0;
        boolean curr=false;
        for (int i=0;i<pinv.length;i++) {
            if(pinv[i]!=null){
                pinv[i].update();
                if(pinv[i].equip==true){
                    this.maxhealth+=pinv[i].stats[3];
                    this.maxMana+=pinv[i].stats[2];
                    this.maxDefence+=pinv[i].stats[1];
                    this.maxAtt+=pinv[i].stats[0];
                }
                if(pinv[i].name.equalsIgnoreCase("EMPTY") && !curr){
                    currinv=i;
                    curr=true;
                }
            }
        }
        if(maxDefence<0){
            maxDefence=0;
        }
        l=Rogue.getLevel();
        for (int j=0;j<l.getItems().size();j++) {
            Item i = l.getItems().get(j);
            if(i.x==this.x && i.y==this.y && pinv[currinv]!=i && pinv[currinv]!=null){
                RenderPanel.pickup = i;
            }
        }
        attack=false;
        if(Rogue.mm.ki.keyBind[0]){//up
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y-1){
                    re.damage(rand.nextInt((int) maxAtt));
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
                this.move(Direction.UP);
            }
        }else if(Rogue.mm.ki.keyBind[1]){//down
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x && re.y==this.y+1){
                    if((int) maxAtt<1){
                        maxAtt=1;
                    }
                    re.damage(rand.nextInt((int) maxAtt));
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
                this.move(Direction.DOWN);
            }
        }else if(Rogue.mm.ki.keyBind[2]){//right
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x+1 && re.y==this.y){
                    re.damage(rand.nextInt((int) maxAtt));
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
                this.move(Direction.RIGHT);
            }
        }else if(Rogue.mm.ki.keyBind[3]){//left
            for(int i=0;i<l.getEntities().size();i++){
                RogueEntity re = l.getEntities().get(i);
                if(re instanceof RogueHostileEntity && re.x==this.x-1 && re.y==this.y){
                    re.damage(rand.nextInt((int) maxAtt));
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
                this.move(Direction.LEFT);
            }
        }
    }
}
