package bomber;

import java.awt.event.KeyEvent;
import java.util.HashSet;

/**
 * Created by jpc on 25-03-17.
 * Ici pas de GUI
 * Seulement du calcul au moment de l'update
 */
public class GameModel {

    public void update(HashSet<Integer> keyCodes){
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
