package Entity.Mob;
import java.util.Random;
/**
 * @author Torri
 */
public class Snake extends RogueHostileMob{
    Random r = new Random();
    public Snake(int spawnX, int spawnY){
        health=10;
        maxAtt=2;
        armour=2;
        x = spawnX;
        y = spawnY;
        dir = 0;
    }
    @Override
    public int attack() {
        return r.nextInt(maxAtt);
    }
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
    @Override
    public int getDir() {
        return dir;
    }
    @Override
    public void turn() {
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public void tick() {
    }
    @Override
    public void damage(int att) {
        att -= armour;
        armour -= att;
        health -= att;
    }
}
