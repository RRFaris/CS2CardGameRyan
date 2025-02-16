import javax.swing.*;
import java.awt.*;

public class Card {
    //  Instance variables
    private String rank;
    private String suit;
    private int value;

    // Card position
    private int cardX;
    private int cardY;

    // Card dimensions
    public static final int CARD_WIDTH = 60;
    public static final int CARD_HEIGHT = 96;

    // Decides when to show card
    private boolean isVisible;

    // Initialize frontend
    private GameView window;

    // Each card needs to have the corresponding image of itself
    private Image cardImage;
    private Image backside;

    // Card length in pixels
    private final int CARD_SPACING = 70;

    //  Constructor
    public Card(String rank, String suit, int value, Image cardImage) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;

        this.cardImage = cardImage;
        backside = new ImageIcon("Resources/Backside.png").getImage();
    }

    //  Getter + Setter methods
    public Image getCardImage() {
        return cardImage;
    }

    public void setVisible(boolean visibility) {
        isVisible = visibility;
    }

    public boolean isClicked(int mouseX, int mouseY, Card card) {
        if ((mouseX > cardX && mouseX < (cardX + CARD_WIDTH)) && (mouseY > cardY && mouseY < (cardY + CARD_HEIGHT))) {
            return true;
        }
        return false;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "|" + suit + " " + rank + "|";
    }

    public void draw(Graphics g, int multiplier, int y) {
        cardX = (CARD_SPACING * multiplier) + 150;
        cardY = y;
        if (isVisible)
            g.drawImage(cardImage, cardX, cardY, window);
        else
            g.drawImage(backside, cardX, cardY, window);
    }
}
