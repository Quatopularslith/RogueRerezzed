/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.mob;

import dungeon.Level;
import entity.RogueEntity;
import java.util.Random;

/**
 *
 * @author Torri
 */
public class RogueHostileEntity extends RogueEntity{
    public int maxAtt;
    private Random rand;

    public RogueHostileEntity(Level l1) {
        super(l1);
    }
    public void att(RogueEntity e){
        e.damage(rand.nextInt(maxAtt));
    }
}
