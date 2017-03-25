package bomber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jpc on 25-03-17.
 * Cet écran là est l'écran de présentation de ton jeu.
 * Ecran classique, avec boutons et autres (ce que tu veux)
 * Ici je lui mets juste un bouton "démarrage" du jeu.
 * Cliquer sur ce bouton va remplacer cet écran par l'écran de jeu proprement dit, qui est complétement
 * différent car c'est un écran animé.
 * Je lui donne une dimension préférée.
 */
public class JPresentationPanel extends JPanel implements ActionListener {
    private JBomberFrame frame;
    private JButton startButton = new JButton("Start");

    public JPresentationPanel(JBomberFrame frame) {
        this.frame = frame;
        add(startButton);
        startButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            frame.switchToGame();
        }
    }
}
