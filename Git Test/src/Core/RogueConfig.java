package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/** 
 * @author Torri
 */
public class RogueConfig {
    File configFile = new File("RogueConfig.dat");
    Properties config;
    String[] props = {"fwdKB","backKB","rightKB","leftKB","spellKB","eatKB","invKB"};
    public RogueConfig(){
        try {
            configFile.createNewFile();
        } catch (IOException ex) {
            System.err.println("ALKADFNLK KBNFLNAKDSLKFNASKNDOF IOBNKND SLKABFIUHJLKMFGNSAB; Poom "+ex.toString());
        }
    }
    public RogueConfig(String[] props){
        this.props=props;
        try {
            configFile.createNewFile();
        } catch (IOException ex) {
            System.err.println("ALKADFNLK KBNFLNAKDSLKFNASKNDOF IOBNKND SLKABFIUHJLKMFGNSAB; Poom "+ex.toString());
        }
    }
    public RogueConfig(String[] props,String[] data){
        this.props=props;
        try {
            configFile.createNewFile();
            try (FileInputStream inStream = new FileInputStream(configFile)) {
                config = new Properties();
                config.load(inStream);
                for (String prop : props) {
                    if (config.getProperty(prop) == null) {
                        config.setProperty(prop, null);
                    }
                }
            }
            save();
        } catch (IOException ex) {
            System.err.println("LAKJDNFDKLANLKBDKLABF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
        }
    }
    public void addData(String prop,String setting){
        try {
            try (FileInputStream inStream = new FileInputStream(configFile)) {
                config = new Properties();
                config.load(inStream);
                config.setProperty(prop,setting);
            }
            save();
        } catch (IOException ex) {
            System.err.println("LAKJDNFDKLANLKBDKLABF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
        }
    }
    public void addData(int propnum,String setting){
        try {
            try (FileInputStream inStream = new FileInputStream(configFile)) {
                config = new Properties();
                config.load(inStream);
                config.setProperty(props[propnum],setting);
            }
            save();
        } catch (IOException ex) {
            System.err.println("LAKJDNFDKLANLKBDKLABF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
        }
    }
    public void addData(String[] setting){
        for(int i=0;i<setting.length;i++){
            try {
                try (FileInputStream inStream = new FileInputStream(configFile)) {
                    config = new Properties();
                    config.load(inStream);
                    config.setProperty(props[i],setting[i]);
                }
                save();
            } catch (IOException ex) {
                System.err.println("LAKJDNFDKLANLKBDKLAyutcBF ERROR ERROR ERROR ERROR; STAIRS!!!!!! "+ex.toString());
            }
        }
    }
    public String[] getSettings(){
        return props;
    }
    private void save(){
        try {
            try (FileOutputStream out = new FileOutputStream(configFile)) {
                config.store(out,"Properties settings");
            }
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }
}
