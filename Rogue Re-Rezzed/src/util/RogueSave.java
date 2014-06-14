package util;

import core.Rogue;
import dungeon.Level;
import dungeon.LevelMode;
import dungeon.LevelType;
import entity.Stairway;
import entity.player.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Mnenmenth & Torri
 */
public class RogueSave {

    private File rs;
    private File r;
    private Properties p;
    private final String sep = File.separator;
    private final String paths;
    private final String[] playerprops = {"x", "y", "xp", "lvl", "mana", "kills", "health", "gold", "numd", "stx", "sty", "name0", "name1", "name2", "name3", "name4", "name5", "name6", "name7", "name8", "name9", "levelMode"};

    public RogueSave(int savenum) {
        paths = "RogueRerezzed" + sep + "Saves" + sep + "save" + savenum;
    }

    public void saveLevel(final Level l) {
        System.out.println("saving...");
        new Thread("Saving") {
            @Override
            public void run() {
                File path = new File(paths);
                path.mkdirs();
                rs = new File(paths + sep + "Level.txt");
                r = new File(paths + sep + "World.txt");
                String[] playersets = {l.getPlayer().x + "", l.getPlayer().y + "", l.getPlayer().xp + "", l.getPlayer().lvl + "", l.getPlayer().mana + "", l.getPlayer().kills + "", l.getPlayer().health + "", l.getPlayer().gold + "", l.lvl + "", l.getStairWay().x + "", l.getStairWay().y + "", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", l.getMode().toString()};
                for (int i = 11; i < playersets.length - 1; i++) {
                    playersets[i] = l.getPlayer().inv[i - 11].name;
                }
                try {
                    rs.createNewFile();
                    p = new Properties();
                    for (int i = 0; i < playerprops.length; i++) {
                        p.setProperty(playerprops[i], playersets[i]);
                    }
                    save(rs);
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
                try {
                    r.createNewFile();
                    try (FileOutputStream outStream = new FileOutputStream(r)) {
                        String data = "";
                        for (boolean[] board : l.board) {
                            for (boolean bo : board) {
                                data += bo ? 1 : 0;
                                data += " ";
                            }
                            data += "\n";
                            outStream.write(data.getBytes());
                            data = "";
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(System.err);
                }
                System.out.println("saved");
            }
        }.start();
    }

    public Level loadLevel() {
        System.out.println("Loading Save " + paths);
        rs = new File(paths + sep + "Level.txt");
        r = new File(paths + sep + "World.txt");
        Level out = new Level();
        boolean[][] b;
        List<String> arr;
        String[] split;
        try {
            rs.createNewFile();
            r.createNewFile();
            arr = Files.readAllLines(r.toPath(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return null;
        }
        b = new boolean[out.board.length][out.board[0].length];
        for (boolean[] ba : b) {
            for (boolean bb : ba) {
                bb = true;
            }
        }
        for (int i = 0; i < out.board.length; i++) {
            split = arr.get(i).split(" ");
            for (int j = 0; j < out.board[0].length; j++) {
                b[i][j] = split[j].equals("1");
            }
        }
        String[] s = new String[playerprops.length];
        try (FileInputStream inStream = new FileInputStream(rs)) {
            p = new Properties();
            p.load(inStream);
            for (int k = 0; k < playerprops.length; k++) {
                s[k] = p.getProperty(playerprops[k]);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        out.lvl = Integer.parseInt(s[8]);
        out.generateLevel(100, 100, out.lvl, LevelMode.valueOf(s[21]), LevelType.TURN, 0);
        Player play = new Player(out);
        play.x = Integer.parseInt(s[0]);
        play.y = Integer.parseInt(s[1]);
        play.xp = Double.parseDouble(s[2]);
        play.lvl = Integer.parseInt(s[3]);
        play.mana = Double.parseDouble(s[4]);
        play.kills = Integer.parseInt(s[5]);
        play.health = Float.parseFloat(s[6]);
        play.gold = Integer.parseInt(s[7]);
        for (int i = 11; i < s.length - 1; i++) {
            play.inv[i - 11].makeItem(s[i], play);
        }
        play.updateStats();
        out.setPlayer(play);
        Stairway st = new Stairway(out);
        st.x = Integer.parseInt(s[9]);
        st.y = Integer.parseInt(s[10]);
        out.setStairWay(st);
        out.board = b;
        out.rePopulate(out.board.length, out.board[0].length);
        Rogue.numLevels = out.lvl;
        System.out.println("Done");
        return out;
    }

    private void save(File s) {
        try {
            try (FileOutputStream outs = new FileOutputStream(s)) {
                p.store(outs, "Properties settings");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
