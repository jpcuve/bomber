package bomber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jpc on 25-03-17.
 * Ecran du jeu.
 */
public class JGamePanel extends JPanel implements ComponentListener, KeyListener, ActionListener {
    public static final int PERIOD = 20; // nombre de millisecondes entre chaque rafraichissement d'écran
    private JBomberFrame bomberFrame;
    private Timer timer;
    private long frame = 0;
    private Set<Integer> keyCodes = new HashSet<>();


    public JGamePanel(JBomberFrame bomberFrame) {
        this.bomberFrame = bomberFrame;
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.setFocusable(true);
        this.timer = new Timer(PERIOD, this);
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        // ici en principe tu dessines en fonction de ton bomberFrame.getGameModel()
        // pour simplifier j'affiche le numéro du frame au milieu de l'écran
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.RED);
        g.drawString("Press ESC to quit", w / 10, h / 10);
        g.drawString(Long.toString(frame), w / 2, h / 2);
        frame++;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // va être appelé tous les "period"
        final HashSet<Integer> keys = new HashSet<>(keyCodes); // lire les input (que les touches clavier ici mais facile d'ajouter la souris)
        final GameModel gameModel = bomberFrame.getGameModel();
        gameModel.update(keys); // updater le modèle en passant les input comme paramètre
        repaint(); // provoquer le rafraichissement de la fenetre (dessin du modèle). Va appeler paint(Graphics g) dès que possible.
    }

    @Override
    public void componentResized(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
        keyCodes.clear();
        this.requestFocusInWindow(); // demande que cette fenêtre reçoive les pressions de touches du clavier
        timer.start();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        timer.stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){ // si escape, retour à l'écran de présentation
            bomberFrame.switchToPresentation();
        }
        keyCodes.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyCodes.remove(e.getKeyCode());
    }

}
