import bomber.JBomberFrame;

import javax.swing.*;

/**
 * Created by jpc on 25-03-17.
 */
public class Main {
    public static void main(String[] args) {
        final JBomberFrame frame = new JBomberFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // c'est ceci qui va appeler le getPreferredSize des composants
        frame.setVisible(true);
    }
}
