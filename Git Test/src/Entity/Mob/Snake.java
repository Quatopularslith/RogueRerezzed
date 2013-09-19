package Entity.Mob;
/**
 * @author Torri
 */
public class Snake extends RogueHostileMob{
    public Snake(int spawnX, int spawnY){
        health=10;
        armour=2;
        x = spawnX;
        y = spawnY;
    }
    @Override
    public int attack() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void turn() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void damage(int att) {
        att -= armour;
        armour -= att;
        health -= att;
    }
}
