package Core;

public class Properties{
  File configFile = new File("Properties.properties");
  FileInputStream inStream;
  Properties config = new Properties(configFile.);
  public Properites(){
      try {
        inStream = new FileInputStream(configFile);
        config.load(inStream);
        config.setProperty("fwdKB", "W");
        config.setProperty("backKB", "S");
        config.setProperty("rightKB", "D");
        config.setProperty("leftKB", "A");
        config.setProperty("spellKB", "K");
        config.setProperty("eatKB", "L");
    } catch (Exception ex) {
        System.err.println("TNAIOGNLAKNDFKLBHALHFJABNFfhasdkWE HAVWE AW EWWOR< DEAL WITH IT STUPID< ITS YOUR FAULT \n"+ex.toString());
    }
  }
  public String[] getProps(){
  	String[] c = new String[];
  	
  }
}
