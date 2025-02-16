import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame {
    // Instance Variables
    private Game game;

    private final int WINDOW_HEIGHT = 800;
    private final int WINDOW_WIDTH = 1000;

    private Image welcomeScreen;
    private Image background;
    private Image backside;

    // Constructor
    public GameView(Game game) {
        // Initialize back end
        this.game = game;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("Uno");
        this.setVisible(true);

        // Initialize card images
        backside = new ImageIcon("Resources/Backside.png").getImage();

        // Initialize backgrounds
        background = new ImageIcon("Resources/Background.png").getImage();
        welcomeScreen = new ImageIcon("Resources/WelcomeScreen.png").getImage();

    }

    // Methods
    public void paint(Graphics g) {
        switch(game.getState()) {
            case Game.WELCOME:
                g.drawImage(welcomeScreen, 0, 0, this);
                break;
            case Game.PLAYING:
                // Draw background
                g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
                g.drawImage(backside, 400, 300, this);

                // Draw player 1's hand
                int multiplier = 1;
                for (Card c : game.getP1Hand()) {
                    // Draws indexes
                    g.drawString(String.valueOf(multiplier), multiplier*70-7, 45);

                    if (game.getTurn() % 2 == 0)
                        c.setVisible(true);
                    else
                        c.setVisible(false);

                    c.draw(g, multiplier, 50);
                    multiplier++;
                }

                // Draw player 2's hand
                multiplier = 1;
                for (Card c : game.getP2Hand()) {
                    // Draws indexes
                    g.drawString(String.valueOf(multiplier), multiplier*70-7, 545);

                    if (game.getTurn() % 2 == 1)
                        c.setVisible(true);
                    else
                        c.setVisible(false);

                    c.draw(g, multiplier, 550);
                    multiplier++;
                }

                // Draws stack
                game.getStack().setVisible(true);
                game.getStack().draw(g, 1, 300);
                break;
            case Game.WIN_ONE:
                break;
            case Game.WIN_TWO:
                break;
        }
    }
}
