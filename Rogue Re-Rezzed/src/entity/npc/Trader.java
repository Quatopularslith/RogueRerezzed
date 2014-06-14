package entity.npc;

import core.Rogue;
import dungeon.Level;
import entity.item.Item;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import render.Sprite;
import render.SpriteSheet;
import util.AI;

/**
 *
 * @author Creatorri
 */
public class Trader extends RogueNPC {

    private final String[] dialogue = {"Hello Adventurer!", "Looking for something?", "Need a hand?", "Hey dawg whaddup?", "Hello Stranger!", "Mortuus Trabajos owning you?", "You saw the crystal? LOL its NYI"};
    public final int[] prices;
    public int[][] buttons = new int[3][2];
    public BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
    public boolean trade = false;

    public Trader(Level l1) {
        super(l1);
        this.prices = new int[3];
        lvl = l1.lvl;
        maxhealth = 10000;
        health = maxhealth;
        inv = new Item[3];
        for (int i = 0; i < inv.length; i++) {
            inv[i] = new Item(rand.nextInt(Item.numid), this, rand.nextInt((lvl) * 10 - 1) + 1, l1);
            prices[i] = inv[i].id + inv[i].lvl;
        }
        int say = rand.nextInt(dialogue.length);
        img = (new Sprite(SpriteSheet.DIALOGUE, 256)).i;
        Graphics2D g = img.createGraphics();
        int width = g.getFontMetrics().stringWidth(dialogue[say]);
        g.setColor(Color.BLACK);
        g.drawString(dialogue[say], 256 / 2 - width / 2, 40);
        width = g.getFontMetrics().stringWidth("Here is what I have:");
        g.drawString("Here is what I have:", 256 / 2 - width / 2, 60);
        for (int i = 0; i < inv.length; i++) {
            g.drawString(inv[i].name + " for " + prices[i] + " gold", 0, (i * 30) + 80);
            buttons[i][0] = 2;
            buttons[i][1] = (i * 30) + 80;
        }
        name = "Trader";
        g.dispose();
    }

    @Override
    public void action() {
        Rogue.mm.gp.checkButtons(true);
        trade = true;
    }

    @Override
    public void turn() {
        ai = new AI(this, -1);
        move(ai.pointTowards(null));
        if (distTo(l.getPlayer()) > 2) {
            trade = false;
        }
    }

    public void refreshTrade() {
        int say = rand.nextInt(dialogue.length);
        img = (new Sprite(SpriteSheet.DIALOGUE, 256)).i;
        Graphics2D g = img.createGraphics();
        int width = g.getFontMetrics().stringWidth(dialogue[say]);
        g.setColor(Color.BLACK);
        g.drawString(dialogue[say], 256 / 2 - width / 2, 40);
        width = g.getFontMetrics().stringWidth("Here is what I have:");
        g.drawString("Here is what I have:", 256 / 2 - width / 2, 60);
        for (int i = 0; i < inv.length; i++) {
            prices[i] = inv[i].id + inv[i].lvl;
            g.drawString(inv[i].name + " for " + prices[i] + " gold", 0, (i * 30) + 80);
            buttons[i][0] = 2;
            buttons[i][1] = (i * 30) + 80;
        }
        g.dispose();
    }
}
