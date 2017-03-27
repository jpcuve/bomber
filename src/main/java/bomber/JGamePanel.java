package bomber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jpc on 25-03-17.
 * Ecran du jeu.
 */
public class JGamePanel extends JPanel implements ComponentListener, KeyListener, ActionListener {
    public static final int PERIOD = 20; // nombre de millisecondes entre chaque rafraichissement d'écran
    public static final int TILE_SIZE = 32;
    private Dimension preferredSize = new Dimension(GameModel.WIDTH * JGamePanel.TILE_SIZE, GameModel.HEIGHT * JGamePanel.TILE_SIZE);
    private JBomberFrame bomberFrame;
    private Timer timer;
    private long frame = 0;
    private Set<Integer> keyCodes = new HashSet<>();


    public JGamePanel(JBomberFrame bomberFrame) {
        this.bomberFrame = bomberFrame;
        this.addKeyListener(this);
        this.addComponentListener(this);
        this.setFocusable(true);
        this.timer = new Timer(0, this);
        timer.setRepeats(false);
        // le timer sera démarré quand le composant JGamePanel sera affiché
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        // ici en principe tu dessines en fonction de ton bomberFrame.getGameModel()
        // pour simplifier j'affiche le numéro du frame au milieu de l'écran
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, w, h);
        final Tile[][] tiles = bomberFrame.getGameModel().getTiles();
        for (int y = 0; y < GameModel.HEIGHT; y++){
            for (int x = 0; x < GameModel.WIDTH; x++){
                final BufferedImage tileImage = tiles[x][y].getImage(frame);
                g.drawImage(tileImage, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }
        g.setColor(Color.RED);
        g.drawString("Press ESC to quit", w / 10, h / 10);
        g.drawString(Long.toString(frame), w / 2, h / 2);
        frame++;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // va être appelé par le timer tous les "period"
        long now = System.currentTimeMillis();
        final GameModel gameModel = bomberFrame.getGameModel();
        gameModel.update(frame, keyCodes); // updater le modèle en passant les input comme paramètre
        keyCodes.clear();
        long delay = PERIOD - (System.currentTimeMillis() - now);
        timer.setInitialDelay((int) delay); // attendre 20 ms - temps pour updater le gamemodel
        timer.start(); // ceci dont boucle sur actionPerformed toutes les 20ms
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
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredSize;
    }
}
