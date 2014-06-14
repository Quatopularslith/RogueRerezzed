package input;

import core.GameLoop;
import core.Rogue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import util.Direction;

/**
 * This gets and keeps information from the keyboard
 *
 * @author Torri
 */
public class KeyboardInput implements KeyListener {

    private final boolean[] keys = new boolean[1000];
    private int[] keyn = new int[6];
    private final Direction[] dirs = {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT, Direction.STOP, Direction.STOP};
    public boolean[] keyBind = new boolean[6];
    public boolean turn = true;
    public boolean pause = false;
    public boolean connected = false;

    /**
     * Sets up a keyboard input listener
     *
     * @param keybinds
     */
    public KeyboardInput(int[] keybinds) {
        keyn = keybinds;
        for (boolean k : keys) {
            k = false;
        }
    }

    /**
     * changes settings w/o making new instance
     *
     * @param keybinds
     */
    public void updateKB(int[] keybinds) {
        keyn = keybinds;
    }

    /**
     * changes settings w/o making new instance
     *
     * @param kb
     */
    public void checkSettings(String[] kb) {
        for (int i = 0; i < kb.length; i++) {
            keyn[i] = Integer.parseInt(kb[i]);
        }
    }

    /**
     * makes the game tick
     */
    private void turn() {
        boolean b;
        b = false;
        for (int i = 0; i < keyBind.length; i++) {
            if (keyBind[i]) {
                b = true;
                Rogue.getCurrentLevel().getPlayer().move(dirs[i]);
                break;
            }
        }
        if (turn && !connected) {
            if (b && !Rogue.getCurrentLevel().getPlayer().dead) {
                Rogue.mm.gp.update();
            }
        } else if (connected) {

        }
    }

    /**
     * Receives when key is typed
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        turn();
    }

    /**
     * Receives when key is pressed
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == 32 && pause && !turn) {
            GameLoop.start();
            pause = false;
        } else if (e.getKeyCode() == 32 && !pause && !turn) {
            pause = true;
            GameLoop.pause();
        }
        for (int i = 0; i < keyBind.length; i++) {
            keyBind[i] = keys[keyn[i]];
        }
    }

    /**
     * Receives when key is released
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        for (int i = 0; i < keyBind.length; i++) {
            keyBind[i] = keys[keyn[i]];
        }
    }
}
