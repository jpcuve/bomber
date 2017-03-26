package bomber;

import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by jpc on 25-03-17.
 * Ici pas de GUI
 * Seulement du calcul au moment de l'update
 */
public class GameModel {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    private Tile[][] tiles = new Tile[WIDTH][HEIGHT];

    public GameModel(){
        loadLevel("level.txt"); // choisir une image de base autre pour le defaut
    }

    public void loadLevel(String resourceName){
        try (final Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream(resourceName))){
            int y = 0;
            String line;
            while(scanner.hasNext()){
                line = scanner.next();
                int x = 0;
                for (char c: line.toCharArray()){
                    tiles[x][y] = Tile.forLetter(c);
                    x++;
                }
                y++;
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void update(Set<Integer> keyCodes){
        if (keyCodes.size() > 0){
            System.out.print("Héros");
            if (keyCodes.contains(KeyEvent.VK_UP)){
                System.out.print(" monte");
            }
            if (keyCodes.contains(KeyEvent.VK_DOWN)){
                System.out.print(" descend");
            }
            if (keyCodes.contains(KeyEvent.VK_LEFT)){
                System.out.print(" gauche");
            }
            if (keyCodes.contains(KeyEvent.VK_RIGHT)){
                System.out.print(" droit");
            }
            System.out.println();
        }
    }
}
