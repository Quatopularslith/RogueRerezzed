
package entity.mob;

import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Item;
import render.Sprite;
import util.Operation;

/**
 * Parent class for all monsters
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    public int followdist;
    private int last=0;
    private int now=0;
    /**
     * creates new entity
     * @param lvl
     * @param r
     * @param l1 
     * @param name 
     * @param op0 operation for health
     * @param attmod attack modifier
     * @param op1 operation for attack
     * @param hmod health modifier
     */
    public RogueHostileEntity(int lvl,Room r,Level l1,String name,Operation op0,int hmod,Operation op1,int attmod,int followdist1) {
        super(l1);
        followdist=followdist1;
        l=l1;
        maxDefence=0;
        switch(op0){
            case ADD:
                health=hmod+(lvl);
                break;
            case SUB:
                health=hmod-(lvl);
                break;
            case MULT:
                health=hmod*(lvl);
                break;
            case DIV:
                health=hmod/(lvl);
                break;
        }
        switch(op1){
            case ADD:
                maxAtt=attmod+(lvl);
                break;
            case SUB:
                maxAtt=attmod-(lvl);
                break;
            case MULT:
                maxAtt=attmod*(lvl);
                break;
            case DIV:
                maxAtt=attmod/(lvl);
        }
        this.maxhealth=(int) this.health;
        sp = new Sprite(name);
        inv = new Item[1];
        if(lvl>Item.numid/3){
            lvl=Item.numid/3;
        }
        inv[0] = new Item(rand.nextInt((lvl*3)+1),this,l);
        spawn(r);
        for (Item inv1 : inv) {
            inv1.update();
        }
    }
    @Override
    public void turn(){
        for (Item inv1 : inv) {
            inv1.update();
        }
        this.move(pointTowards(this.l.getPlayer()));
        if(doatt(l.getPlayer()) && maxAtt>0){
            l.getPlayer().damage(rand.nextInt(Math.abs((int) maxAtt)));
        }
    }
    /**
     * basic tracer AI
     * @param e
     * @return 
     */
    public int pointTowards(RogueEntity e){
        double pdir=720;
        if(distTo(e)<followdist){
//            if(e.x==x){
//                pdir=720;
//            }else{
//                pdir=Math.toDegrees(Math.atan((e.y-y)/(e.x-x)));
//                pdir=Math.abs(pdir)+90;
//                System.out.println(pdir+":"+this.getClass());
//            }
            if(e.x==x){
                if(e.y>y){
                    pdir=180;
                }else{
                    pdir=0;
                }
            }else if(e.y==y){
                if(e.x>x){
                    pdir=90;
                }else{
                    pdir=270;
                }
            }else{
                if(e.x>x && e.y>=y){//Quad 1
                    pdir=Math.toDegrees(Math.atan(x));
                }else if(e.x>x && e.y<y){//Quad 2
                    pdir=360+Math.toDegrees(Math.atan(x));
                }else if(e.x<x && e.y<y){//Quad 3
                    pdir=180+Math.toDegrees(Math.atan(x));
                }else if(e.x < x && e.y>=y){//Quad 4
                    pdir=180+Math.toDegrees(Math.atan(x));
                }
            }
        }else{
            now=rand.nextInt(359)+1;
            if(now!=last){
                pdir=now;
                last=now;
            }else{
                pdir=720;
            }
        }
        return (int) pdir;
    }
    /**
     * asks if it can attack
     * @param e
     * @return 
     */
    public boolean doatt(RogueEntity e){
        int dx = Math.abs(x-e.x);
        int dy = Math.abs(y-e.y);
        boolean bx = dx==1 || dx==0;
        boolean by = dy==1 || dy==0;
        boolean out = (bx && by);
        return out;
    }
    @Override
    public void death(){
        if(inv!=null){
            for (Item inv1 : inv) {
                inv1.drop();
            }
        }
        l.removeEntity(this);
    }
}
