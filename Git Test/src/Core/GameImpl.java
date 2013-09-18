package Core;

public class GameImpl extends Game{
  public int turnnum;
  @Override
  public void tick(){
    
  }
    @Override
  public void turn(){
    turnnum++;
  }

    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
