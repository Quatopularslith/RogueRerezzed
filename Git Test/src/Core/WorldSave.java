package Core;

import Level.Level;
import java.io.*;
import java.util.Properties;

public class WorldSave{
    private final File worldfile;
    private final Level l;
    private Properties config;
    public WorldSave(String dir, Level l1){
        worldfile = new File(dir);
        l = l1;
    }
    public void addData(){
        try {
            worldfile.createNewFile();
            try (FileInputStream inStream = new FileInputStream(worldfile)) {
                config = new Properties();
                config.load(inStream);
                config.setProperty("Level",l.toString());
            }
        } catch (IOException ex) {
            System.err.println("LAKJDNFDKLANLKBDKLABF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
        }
    }
    public void saveData(){
        try {
            try (FileOutputStream out = new FileOutputStream(worldfile)) {
                config.store(out,"Properties settings");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
