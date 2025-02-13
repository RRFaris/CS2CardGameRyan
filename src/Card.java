import java.awt.*;

public class Card {
    //  Instance variables
    private String rank;
    private String suit;
    private int value;

    // Initialize frontend
    private GameView window;

    // Each card needs to have the corresponding image of itself
    private Image cardImage;

    // Card length in pixels
    private final int CARD_SPACING = 70;

    //  Constructor
    public Card(String rank, String suit, int value, Image cardImage) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;

        this.cardImage = cardImage;
    }
    //  Getter + Setter methods
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
        g.drawImage(cardImage,  CARD_SPACING * multiplier - 50, y, window);
    }
}
