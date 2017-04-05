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
    public static final int WINDOW_WIDTH = 16;
    public static final int WINDOW_HEIGHT = 16;
    private Dimension preferredSize = new Dimension(WINDOW_WIDTH * JGamePanel.TILE_SIZE, WINDOW_HEIGHT * JGamePanel.TILE_SIZE);
    private JBomberFrame bomberFrame;
    private Timer timer;
    private long frame = 0;
    private Set<Integer> keyCodes = new HashSet<>();
    private Rectangle view = new Rectangle(); // in tiles unit


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
        final GameModel gameModel = bomberFrame.getGameModel();
        final Point playerPosition = gameModel.getPlayerPosition();
        // ensure player is displayed on screen!
        final Point topLeft = new Point(Math.max(playerPosition.x - w / TILE_SIZE + 2, 0), Math.max(playerPosition.y - h / TILE_SIZE + 2, 0));
        final Tile[][] tiles = gameModel.getTiles();
        for (int y = 0; y < gameModel.getHeight(); y++){
            for (int x = 0; x < gameModel.getWidth(); x++){
                final BufferedImage tileImage = tiles[x][y].getImage(frame);
                g.drawImage(tileImage, (x - topLeft.x) * TILE_SIZE, (y - topLeft.y) * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }
        g.setColor(Color.RED);
        g.drawString(String.format("Press ESC to quit, arrows to move player. Frame: %s", frame), w / 10, h / 10);
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
        System.out.println("JGamePanel.componentResized");
        // computing topLeft, centering player on screen
        view.setSize(getWidth() / TILE_SIZE, getHeight() / TILE_SIZE);
        final GameModel gameModel = bomberFrame.getGameModel();
        view.setLocation(gameModel.getPlayerPosition().x - (int) view.getWidth() / 2, gameModel.getPlayerPosition().y - (int) view.getHeight() / 2);

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
