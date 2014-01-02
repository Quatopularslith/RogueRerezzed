package util;

import dungeon.Level;
import entity.RogueEntity;

/**
 * Part of A*
 * @author Torri
 */
public class Vector2i {
    private int x,y;
    public Vector2i(){
        set(0,0);
    }
    public Vector2i(int x1,int y1){
        set(x1,y1);
    }
    public Vector2i(Vector2i v2){
        set(v2.x,v2.y);
    }
    public int getX(){
        return x;
    }
    public Vector2i setX(int x1){
        x=x1;
        return this;
    }
    public int getY(){
        return y;
    }
    public Vector2i setY(int y1){
        y=y1;
        return this;
    }
    public Vector2i set(int x1, int y1){
        x=x1;
        y=y1;
        return this;
    }
    public Vector2i add(Vector2i v1){
        x+=v1.x;
        y+=v1.y;
        return this;
    }
    public Vector2i subtract(Vector2i v1){
        x-=v1.x;
        y-=v1.y;
        return this;
    }
    @Override
    public boolean equals(Object object){
        if(!(object instanceof Vector2i)) return false;
        Vector2i vec = (Vector2i) object;
        return vec.getX()==getX() && vec.getY()==getY();
    }
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}
