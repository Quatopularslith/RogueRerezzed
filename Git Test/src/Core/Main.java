package Core;

/**
 * @author Torri
 */
public class Main {
    public static MainMenu m;
    public static void main(String[] args){
        m = new MainMenu(750,500);
        Game g = new Game();
        g.start();
    }
}