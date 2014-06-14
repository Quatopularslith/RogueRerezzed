/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.npc;

import dungeon.Level;
import java.awt.Image;
import render.Sprite;
import render.SpriteSheet;
import util.AI;

/**
 *
 * @author Torri
 */
public class QuestMan extends RogueNPC {

    public boolean quest = false;
    public Image img;

    public QuestMan(Level l1) {
        super(l1);
        sp = new Sprite(SpriteSheet.QUESTMAN);
        img = (new Sprite(SpriteSheet.DIALOGUE, 256)).i;
        maxhealth = 2;
        health = maxhealth;
        name = "Questman";
        refreshQuests();
    }

    @Override
    public void action() {
        quest = true;
    }

    @Override
    public void turn() {
        ai = new AI(this, -1);
        move(ai.pointTowards(null));
    }

    public void refreshQuests() {

    }
}
