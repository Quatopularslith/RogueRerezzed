package Level;

import Entity.RogueEntity;

/**
 * @author Torri
 */
public class Level {
    private int sizeX,sizeY;
    private RogueEntity[] re = new RogueEntity[100];
    public RogueEntity[] getEntities(){
        return re;
    }
}
