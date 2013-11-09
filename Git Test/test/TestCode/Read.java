/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author JackWhiteIII
 */
public class Read {
    private int keyUp;
    private int keyDown;
    private int keyLeft;
    private int keyRight;
    public void read(){
        try{
            File theDir = new File(System.getProperty("user.home")+"\\AppData\\Roaming\\Minification\\AntiMatter\\");
            if(!theDir.exists()){
                theDir.mkdir();
                //save();
                System.out.println("works");
            }
            System.out.println(System.getProperty("user.home")+"\\AppData\\Roaming\\Minification\\AntiMatter\\"+"options.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home")+"\\AppData\\Roaming\\Minification\\AntiMatter\\"+"options.txt"))) {
                String line;
                while( (line = br.readLine()) != null ){
                    String[] props = line.split("=");
                    int name = Integer.parseInt(props[0]);
                    switch(name){
                        case 0:
                            keyUp = Integer.parseInt(props[1]);
                            break;
                        case 1:
                            keyDown = Integer.parseInt(props[1]);
                            break;
                        case 2:
                            keyLeft = Integer.parseInt(props[1]);
                            break;
                        case 3:
                            keyRight = Integer.parseInt(props[1]);
                            break;
                    }
                }
            }
        } catch (IOException e) {
        }
    }
}
