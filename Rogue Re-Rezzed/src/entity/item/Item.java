package entity.item;

import core.Rogue;
import dungeon.Level;
import entity.RogueEntity;
import render.Sprite;
import render.SpriteSheet;

/**
 * Items!
 *
 * @author Torri
 */
public class Item extends RogueEntity {

    public static final String[] modifiers = {"", "Broken ", "Ordinary ", "Shattered ", "Old ", "Healthy ", "Magical ", "Strong "};
    public static final String[] type = {"Empty", "Sword", "Axe", "Shield"};
    public static final String[] materials = {"Wood ", "Stone ", "Copper ", "Bronze ", "Iron ", "Steel ", "Unknown "};
    public static int numid = type.length * materials.length;
    public ItemType tyname;
    public int ofx = rand.nextInt(42), ofy = rand.nextInt(42);
    public int modifierid;
    public boolean cursed;
    public int id;
    public boolean equip = false;
    /**
     * In form of {Attack,Defense,Mana,Health}
     */
    public double[] stats = {0.0, 0.0, 0.0, 0.0};
    private RogueEntity parent;
    private int mod;

    public Item(int id, RogueEntity parent1, int lvl, Level l1) {
        super(l1);
        this.id = id;
        health = 1;
        maxhealth = 1;
        parent = parent1;
        sp = new Sprite(SpriteSheet.BAG, 16);
        modifierid = rand.nextInt(modifiers.length);
        this.lvl = lvl;
        int matid = id / materials.length;
        if (matid > materials.length) {
            matid = id % (materials.length - 1);
        }
        int tyid = (id % 3) + 1;
        if (tyid > type.length || matid > materials.length) {
            return;
        }
        if (this instanceof Gold) {
            return;
        }
        if (id != 0) {
            name = "Lvl " + lvl + " " + modifiers[modifierid] + materials[matid] + type[tyid];
            cursed = rand.nextBoolean();
            switch (tyid) {
                case 1:
                    tyname = ItemType.SWORD;
                    stats[0] = 1;
                    mod = 0;
                    break;
                case 2:
                    tyname = ItemType.AXE;
                    stats[0] = 0.5;
                    stats[1] = 0.5;
                    mod = 0;
                    break;
                case 3:
                    tyname = ItemType.SHEILD;
                    stats[1] = 1;
                    mod = 1;
                    break;
                default:
                    tyname = ItemType.EMPTY;
                    mod = 3;
                    break;
            }
            stats[0] *= (matid + 1);
            stats[1] *= (matid + 1);
            stats[2] *= (matid + 1);
            stats[3] *= (matid + 1);
            switch (modifierid) {
                case 1:
                    stats[mod] -= (0.4 * lvl);
                    break;
                case 3:
                    stats[1] -= (0.4 * lvl);
                    break;
                case 5:
                    stats[3] += (0.4 * lvl);
                    break;
                case 6:
                    stats[2] += (0.4 * lvl);
                    break;
                case 7:
                    stats[0] += (0.4 * lvl);
                    stats[1] += (0.4 * lvl);
                    break;
            }
        } else {
            name = type[0];
            tyname = ItemType.EMPTY;
            for (double ind : stats) {
                ind = 0;
            }
        }
        if (parent == null) {
            parent = new RogueEntity(Rogue.getCurrentLevel());
        }
        this.x = parent.x;
        this.y = parent.y;
    }

    public void makeItem(String name1, RogueEntity parent1) {
        if (name1 == null) {
            return;
        }
        health = 1;
        maxhealth = 1;
        parent = parent1;
        sp = new Sprite(SpriteSheet.BAG, 16);
        name = name1;
        if (name1.equalsIgnoreCase("Empty")) {
            name = type[0];
            return;
        }
        String[] props = name1.split(" ");
        lvl = Integer.parseInt(props[1]);
        modifierid = 0;
        int matid = 0;
        int tyid = 0;
        for (int i = 0; i < modifiers.length; i++) {
            if (props[2].equals(modifiers[i].split(" ")[0])) {
                modifierid = i;
                break;
            }
        }
        if (modifierid != 0) {
            for (int i = 0; i < materials.length; i++) {
                if (props[3].equals(materials[i].split(" ")[0])) {
                    matid = i;
                }
            }
            for (int i = 0; i < type.length; i++) {
                if (props[4].equals(type[i])) {
                    tyid = i;
                }
            }
        } else {
            for (int i = 0; i < materials.length; i++) {
                if (props[2].equals(materials[i].split(" ")[0])) {
                    matid = i;
                }
            }
            for (int i = 0; i < type.length; i++) {
                if (props[3].equals(type[i])) {
                    tyid = i;
                }
            }
        }
        id = 1;
        cursed = rand.nextBoolean();
        switch (tyid) {
            case 1:
                tyname = ItemType.SWORD;
                stats[0] = 1;
                mod = 0;
                break;
            case 2:
                tyname = ItemType.AXE;
                stats[0] = 0.5;
                stats[1] = 0.5;
                mod = 0;
                break;
            case 3:
                tyname = ItemType.SHEILD;
                stats[1] = 1;
                mod = 1;
                break;
            default:
                tyname = ItemType.EMPTY;
                mod = 3;
                break;
        }
        stats[0] *= (matid + 1);
        stats[1] *= (matid + 1);
        stats[2] *= (matid + 1);
        stats[3] *= (matid + 1);
        switch (modifierid) {
            case 1:
                stats[mod] -= (0.4 * lvl);
                break;
            case 3:
                stats[1] -= (0.4 * lvl);
                break;
            case 5:
                stats[3] += (0.4 * lvl);
                break;
            case 6:
                stats[2] += (0.4 * lvl);
                break;
            case 7:
                stats[0] += (0.4 * lvl);
                stats[1] += (0.4 * lvl);
                break;
        }
        if (parent == null) {
            parent = new RogueEntity(Rogue.getCurrentLevel());
        }
        this.x = parent.x;
        this.y = parent.y;
    }

    public void drop() {
        this.x = parent.x;
        this.y = parent.y;
        if (!name.equalsIgnoreCase("Empty")) {
            l.addItem(this);
        }
    }

    public RogueEntity getParent() {
        return parent;
    }

    public void setParent(RogueEntity e) {
        parent = e;
        l = e.l;
        sp = new Sprite(SpriteSheet.BAG, 16);
    }

    @Override
    public void death() {
        l.removeItem(this);
    }

    @Override
    public void turn() {
    }
}
