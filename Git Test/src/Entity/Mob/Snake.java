package Entity.Mob;
/**
 * @author Torri
 */
public class Snake extends RogueHostileMob{
    public Snake(){
        health=10;
        armour=2;
    }
    @Override
    public int attack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void getX() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void getY() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void getDir() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void turn() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void getHealth() {
        throw new UnsupportedOperationException("Not supported yet.");
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
