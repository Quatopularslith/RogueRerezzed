
package entity.item;

import dungeon.Level;
import entity.RogueEntity;
import render.Sprite;


/**
 * Items!
 * @author Torri
 */
public class Item extends RogueEntity{
    public ItemType tyname;
    public int ofx=rand.nextInt(42),ofy=rand.nextInt(42);
    private RogueEntity parent;
    public int modifierid;
    public boolean cursed;
    public int id;
    public static final String[] modifiers = {"","Broken ","Ordinary ","Shattered ","Old ","Healthy ","Magical ","Strong "};
    public static final String[] type = {"Empty","Sword","Axe","Shield"};
    public static final String[] materials = {"Wood ","Stone ","Copper ","Bronze ","Iron ","Steel ","Unknown "};
    public String name;
    public boolean equip = false;
    public static int numid = type.length*materials.length;
    /**
     * In form of
     * {Attack,Defense,Mana,Health}
     */
    public double[] stats = {0.0,0.0,0.0,0.0};
    public Item(int id1,RogueEntity parent1,Level l1) {
        super(l1);
        health=1;
        maxhealth=1;
        parent=parent1;
        id=id1;
        sp=new Sprite("Bag",16);
        modifierid=rand.nextInt(modifiers.length);
        int matid = (int) id/materials.length;
        int tyid = (id%3)+1;
        if(id!=0){
            name = "Lvl "+Level.numLevels+" "+modifiers[modifierid]+materials[matid]+type[tyid];
            cursed = rand.nextBoolean();
            switch(tyid){
                case 1:
                    tyname=ItemType.SWORD;
                    stats[0]=1;
                    break;
                case 2:
                    tyname=ItemType.AXE;
                    stats[0]=0.5;
                    stats[1]=0.5;
                    break;
                case 3:
                    tyname=ItemType.SHEILD;
                    stats[1]=1;
                    break;
                default:
                    tyname=ItemType.EMPTY;
                    break;
            }
            stats[0]*=matid+1;
            stats[1]*=matid+1;
            stats[2]*=matid+1;
            stats[3]*=matid+1;
            switch(modifierid){
                case 1:
                    stats[0]-=(0.4*(Level.numLevels+1));
                    break;
                case 3:
                    stats[1]-=(0.4*(Level.numLevels+1));
                    break;
                case 5:
                    stats[3]+=(0.4*(Level.numLevels+1));
                    break;
                case 6:
                    stats[2]+=(0.4*(Level.numLevels+1));
                    break;
                case 7:
                    stats[0]+=(0.4*(Level.numLevels+1));
                    break;
            }
        }else{
            name = type[0];
            tyname=ItemType.EMPTY;
            for(double ind:stats){
                ind=0;
            }
        }
        this.x=parent.x;
        this.y=parent.y;
    }
    public void drop(){
        this.x=parent.x;
        this.y=parent.y;
        if(this.id!=0){
//            System.out.println(name+" was dropped at ("+x+","+y+") by "+parent.getClass().toString());
            l.addItem(this);
        }
    }
    public void update(){
        this.x=parent.x;
        this.y=parent.y;
    }
    public RogueEntity getParent(){
        return parent;
    }
    public void setParent(RogueEntity e){
        parent=e;
    }
    @Override
    public void death(){
        l.removeItem(this);
    }
    @Override
    public void turn(){}
}
