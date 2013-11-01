package Core;

import java.io.*;

public class WorldSave{
    private File worldfile;
    private FileInputStream inStream;
    private FileOutputStream outStream;
    public WorldSave(String dir){
        worldfile = new File(dir);
        
    }
}
