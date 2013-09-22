package Core;

/**
 * @author Torri
 */
public class Main {
    public static void main(String[] args){
        Game g = new Game();
        g.start();
        MainMenu m = new MainMenu(1000,800);
    }
}