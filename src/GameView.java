import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame {
    // Instance Variables
    private Game game;

    public static final int DECK_X = 770;
    public static final int DECK_Y = 350;

    private final int WINDOW_HEIGHT = 800;
    private final int WINDOW_WIDTH = 1000;

    private Image welcomeScreen;
    private Image player1Background;
    private Image player2Background;
    private Image player1Win;
    private Image player2Win;
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
        welcomeScreen = new ImageIcon("Resources/WelcomeScreen.png").getImage();
        player1Background = new ImageIcon("Resources/Player1Background.png").getImage();
        player2Background = new ImageIcon("Resources/Player2Background.png").getImage();
        player1Win = new ImageIcon("Resources/Player1Win.png").getImage();
        player2Win = new ImageIcon("Resources/Player2Win.png").getImage();

    }

    // Methods
    public void paint(Graphics g) {
        switch(game.getState()) {
            case Game.WELCOME:
                g.drawImage(welcomeScreen, 0, 0, this);
                break;
            case Game.PLAYING:
                // Draw background
                if (game.getTurn() % 2 == 0)
                    g.drawImage(player1Background, 0, 0, this);
                else
                    g.drawImage(player2Background, 0, 0, this);

                g.drawImage(backside, 770, 350, this);

                // Draw player 1's hand
                int multiplier = 1;
                for (Card c : game.getP1Hand()) {
                    // If it isn't the player's turn, their cards will be hidden
                    if (game.getTurn() % 2 == 0)
                        c.setVisible(true);
                    else
                        c.setVisible(false);

                    c.draw(g, multiplier, 90);
                    multiplier++;
                }

                // Draw player 2's hand
                multiplier = 1;
                for (Card c : game.getP2Hand()) {
                    // If it isn't the player's turn, their cards will be hidden
                    if (game.getTurn() % 2 == 1)
                        c.setVisible(true);
                    else
                        c.setVisible(false);

                    c.draw(g, multiplier, 640);
                    multiplier++;
                }

                // Draws stack
                game.getStack().setVisible(true);
                game.getStack().draw(g, 1, 350);
                break;
            case Game.WIN_ONE:
                g.drawImage(player1Win, 0, 0, this);
                break;
            case Game.WIN_TWO:
                g.drawImage(player2Win, 0, 0, this);
                break;
        }
    }
}
