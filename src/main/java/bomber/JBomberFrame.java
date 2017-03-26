package bomber;


import javax.swing.*;
import java.awt.*;

/**
 * Created by jpc on 25-03-17.
 * Fenêtre du jeu.
 * Contient soit l'écran titre (JPresentationPanel), soit l'écran jeu (JGamePanel)
 */
public class JBomberFrame extends JFrame {
    public static final String PRESENTATION = "presentation";
    public static final String GAME = "game";
    private JPanel contentPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout(); // ce layout permet de superposer des écrans à la façon d'un jeu de cartes. Seule une carte est montrée à la fois.
    private GameModel gameModel = new GameModel(); // modèle du jeu (contient les gameobject)

    public JBomberFrame() throws HeadlessException {
        super("Titre de mon jeu");
        this.add(contentPanel);
        contentPanel.setLayout(cardLayout);
        contentPanel.add(PRESENTATION, new JPresentationPanel(this));
        contentPanel.add(GAME, new JGamePanel(this));
        switchToPresentation();
    }

    public void switchToPresentation(){
        cardLayout.show(contentPanel, PRESENTATION);

    }

    public void switchToGame(){
        gameModel.loadLevel("level.txt");
        cardLayout.show(contentPanel, GAME);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

}
