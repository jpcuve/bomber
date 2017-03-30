package bomber;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by jpc on 25-03-17.
 * Ici pas de GUI
 * Seulement du calcul au moment de l'update.
 * Ici le modèle est comme un jeu d'échec. Damier de 16x16, chaque case est occupée par un Tile.
 * Il est "stateless" c'est à dire que chaque update ne tient compte que du damier au moment de l'update, et
 * pas des événements du passé qui ne sont pas mémorisé.
 */
public class GameModel {
    private int width;
    private int height;
    private Tile[][] tiles;
    private Point playerPosition = new Point(0, 0);

    public GameModel(){
        loadLevel("level.txt"); // choisir une image de base autre pour le defaut
    }

    public void loadLevel(String resourceName){ // charger un niveau, fichier ASCII dans "resources" représentant un damier 16x16
        try (final Scanner scanner = new Scanner(ClassLoader.getSystemResourceAsStream(resourceName))){
            int y = 0;
            String line;
            this.width = scanner.nextInt();
            this.height = scanner.nextInt();
            this.tiles = new Tile[width][height];
            while(scanner.hasNext()){
                line = scanner.next();
                int x = 0;
                for (char c: line.toCharArray()){
                    tiles[x][y] = Tile.forLetter(c);
                    switch(tiles[x][y]){
                        case PLAYER:
                            playerPosition = new Point(x, y);
                            break;
                    }
                    x++;
                }
                y++;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Point getPlayerPosition() {
        return playerPosition;
    }

    public void update(long frame, Set<Integer> keyCodes){
        if (keyCodes.size() > 0){ // si oui des touches ont été pressées
            final Point newPlayerPosition = new Point(playerPosition);
            if (keyCodes.contains(KeyEvent.VK_UP)){
                newPlayerPosition.y -= 1;
            }
            if (keyCodes.contains(KeyEvent.VK_DOWN)){
                newPlayerPosition.y += 1;
            }
            if (keyCodes.contains(KeyEvent.VK_LEFT)){
                newPlayerPosition.x -= 1;
            }
            if (keyCodes.contains(KeyEvent.VK_RIGHT)){
                newPlayerPosition.x += 1;
            }
            // si la nouvelle position est un obstacle, rien faire
            if (!tiles[newPlayerPosition.x][newPlayerPosition.y].isObstacle()){
                tiles[playerPosition.x][playerPosition.y] = Tile.EMPTY;
                tiles[newPlayerPosition.x][newPlayerPosition.y] = Tile.PLAYER;
                playerPosition = newPlayerPosition;
            }
        }
    }
}
