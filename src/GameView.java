import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    // Instance Variables
    private Game window;

    private final int WINDOW_HEIGHT = 800;
    private final int WINDOW_WIDTH = 1000;

    private Image background;
    private Image blue0;

    // Constructor
    public GameView(Game window) {
        // Initialize back end
        this.window = window;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("Uno");
        this.setVisible(true);

        background = new ImageIcon("Resources/Background.png").getImage();
        blue0 = new ImageIcon("Resources/b0.png").getImage();

        // Initialize card images
    }

    // Methods
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawImage(blue0, 0, 100, this);
    }
}
