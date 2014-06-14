package entity.npc;

import dungeon.Level;
import entity.RogueEntity;
import entity.mob.RogueHostileEntity;
import entity.player.Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import render.Sprite;
import render.SpriteSheet;
import util.AI;
import util.Node;

/**
 *
 * @author Torri
 */
public class Warrior extends RogueNPC {

    RogueEntity follow;
    private final String[] dialogue = {"Need a hand?", "I'll help you. For a price.", "DEATH, DEATH EVERYWHERE"};
    public BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
    public int cost;
    public int buttons[] = new int[2];
    public boolean hireD = false;
    public boolean hire = false;
    int numturns = 0;
    List<Node> path;

    public Warrior(Level l1) {
        super(l1);
        lvl = l1.lvl;
        maxAtt = lvl * 5;
        maxhealth = lvl;
        health = lvl;
        name = "Warrior";
        cost = lvl * rand.nextInt(15) + 1;
        refreshHire();
    }

    @Override
    public void turn() {
        numturns++;
        for (RogueEntity re : l.getEntities()) {
            if (re == null || re.equals(this) || !(re instanceof RogueHostileEntity)) {
                continue;
            }
            if (follow == null) {
                follow = re;
            }
            if (follow.health <= 0) {
                follow = re;
            }
            if (distTo(re) < distTo(follow)) {
                follow = re;
            }
        }
        if (distTo(l.getPlayer()) > 1) {
            hireD = false;
        }
        if (hire) {
            follow = l.getPlayer();
        }
        if (numturns % 2 == 0) {
            ai = new AI(this, 100);
            move(ai.pathFind(follow));
        }
        if (!(follow instanceof Player) && distTo(follow) <= 1) {
            follow.damage(this);
        } else {
            for (RogueEntity re : l.getEntities()) {
                if (re instanceof RogueHostileEntity && distTo(re) <= 1) {
                    re.damage(this);
                }
            }
        }
    }

    @Override
    public void action() {
        hireD = true;
    }

    public void refreshHire() {
        if (hire) {
            cost = 0;
        }
        int say = rand.nextInt(dialogue.length);
        img = (new Sprite(SpriteSheet.DIALOGUE, 256)).i;
        Graphics2D g = img.createGraphics();
        int width = g.getFontMetrics().stringWidth(dialogue[say]);
        g.setColor(Color.BLACK);
        g.drawString(dialogue[say], 256 / 2 - width / 2, 40);
        g.drawString("Hire me for: " + cost + " Gold", 0, 60);
        buttons[0] = 2;
        buttons[1] = 100;
        g.dispose();
    }
}
