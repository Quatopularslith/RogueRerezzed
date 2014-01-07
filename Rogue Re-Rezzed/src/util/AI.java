
package util;

import entity.RogueEntity;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Torri
 */
public class AI {
    private final RogueEntity parent;
    private final int followdist;
    private final int x,y;
    private final Random rand = new Random();
    public AI(RogueEntity body,int follow){
        parent=body;
        x=body.x;
        y=body.y;
        if(follow>150){
            followdist=150;
        }else{
            followdist=follow;
        }
    }
    /**
     * basic tracer AI
     * @param e
     * @return 
     */
    public Direction pointTowards(RogueEntity e){
        Direction pdir=Direction.STOP;
        if(parent.distTo(e)<followdist){
            if(x<e.x)pdir = Direction.RIGHT;
            if(x>e.x)pdir = Direction.LEFT;
            if(y<e.y)pdir = Direction.DOWN;
            if(y>e.y)pdir =Direction.UP;
        }else{
            boolean b = rand.nextBoolean();
            int d = rand.nextInt(3);
            if(b){
                switch (d){
                    case 0:
                        pdir=Direction.UP;
                        break;
                    case 1:
                        pdir=Direction.RIGHT;
                        break;
                    case 2:
                        pdir=Direction.LEFT;
                        break;
                    case 3:
                        pdir=Direction.DOWN;
                        break;
                    default:
                        pdir=Direction.STOP;
                        break;
                }
            }else{
                pdir=Direction.STOP;
            }
        }
        return pdir;
    }
    /**
     * FINALY A*
     * @param e 
     * @return  
     */
    public Direction pathFind(RogueEntity e){
        Direction out;
        if(parent.distTo(e)<=followdist){
            Astar a = new Astar();
            List<Node> path = a.findPath(new Vector2i(x,y), new Vector2i(e.x,e.y),parent.l,followdist);
            if(path==null){
                boolean b = rand.nextBoolean();
                int d = rand.nextInt(3);
                if(b){
                    switch (d){
                        case 0:
                            out=Direction.UP;
                            break;
                        case 1:
                            out=Direction.DOWN;
                            break;
                        case 2:
                            out=Direction.LEFT;
                            break;
                        case 3:
                            out=Direction.RIGHT;
                            break;
                        default:
                            out=Direction.STOP;
                            break;
                    }
                }else{
                    out=Direction.STOP;
                }
            }else{
                out = pointTowards(path.get(path.size()-2));
            }
        }else{
            boolean b = rand.nextBoolean();
            int d = rand.nextInt(3);
            if(b){
                switch (d){
                    case 0:
                        out=Direction.UP;
                        break;
                    case 1:
                        out=Direction.DOWN;
                        break;
                    case 2:
                        out=Direction.LEFT;
                        break;
                    case 3:
                        out=Direction.RIGHT;
                        break;
                    default:
                        out=Direction.STOP;
                        break;
                }
            }else{
                out=Direction.STOP;
            }
        }
        return out;
    }
    /**
     * Helps with A*
     * @param e
     * @return 
     */
    private Direction pointTowards(Node e){
        Direction pdir = Direction.STOP;
        if(x<e.tile.getX()){
            pdir = Direction.RIGHT;
        }else if(x>e.tile.getX()){
            pdir = Direction.LEFT;
        }
        if(y<e.tile.getY()){
            pdir = Direction.DOWN;
        }else if(y>e.tile.getY()){
            pdir =Direction.UP;
        }
        return pdir;
    }
}
