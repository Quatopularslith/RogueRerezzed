package Core;

/**
 * @author Torri
 */
public class Main {
    public static void main(String[] args){
        GameLoop gl = new GameLoop();
        gl.start();
        MainMenu m = new MainMenu(750,500);
    }
}