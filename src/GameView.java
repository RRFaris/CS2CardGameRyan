import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame {
    // Instance Variables
    private Game game;

    private final int WINDOW_HEIGHT = 800;
    private final int WINDOW_WIDTH = 1000;

    private Image background;

    // Constructor
    public GameView(Game game) {
        // Initialize back end
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("Uno");
        this.setVisible(true);

        // Initialize card images
        background = new ImageIcon("Resources/Background.png").getImage();
    }

    // Methods
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

        int multiplier = 1;
        for (Card c : game.getP1Hand()) {
            g.drawString(String.valueOf(multiplier), multiplier*70-7, 45);
            c.draw(g, multiplier, 50);
            multiplier++;
        }

        multiplier = 1;
        for (Card c : game.getP2Hand()) {
            g.drawString(String.valueOf(multiplier), multiplier*70-7, 545);
            c.draw(g, multiplier, 550);
            multiplier++;
        }

        multiplier = 1;
        game.getStack().draw(g, multiplier, 300);
    }
}
