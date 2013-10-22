package Core;

import java.io.*;
import java.util.Properties;

public class Propertiesimpl{
  private static File configFile = new File("Properties.properties");
  private static FileInputStream inStream;
  public static Properties config;
  public static void init(){
    try {
        configFile.createNewFile();
        inStream = new FileInputStream(configFile);
        config.load(inStream);
    } catch (Exception ex) {
        System.err.println("TNAIOGNLAKNDFKLBHALHFJABNFfhasdkWE HAVWE AW EWWOR< DEAL WITH IT STUPID< ITS YOUR FAULT \n"+ex.toString());
    }
  }
}
