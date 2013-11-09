package Util;
/**
 *
 * @author Torri
 */
public class Vector2i {
    private int x,y;//Origin co-ordinates (absolute)
    public Vector2i(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Vector2i add(Vector2i v){
        x+=v.x;
        y+=v.y;
        return this;
    }
    public Vector2i subtract(Vector2i v){
        x-=v.x;
        y-=v.y;
        return this;
    }
    public int getX(){
        return x;
    }
    public Vector2i setX(int x){
        this.x=x;
        return this;
    }
    public int getY(){
        return y;
    }
    public Vector2i setY(int y){
        this.y=y;
        return this;
    }
}
