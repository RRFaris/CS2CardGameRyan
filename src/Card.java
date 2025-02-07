import java.awt.*;

public class Card {
    //  Instance variables
    private String rank;
    private String suit;
    private int value;

    // Initialize frontend
    private GameView window;

    //  Constructor
    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;

        this.window = window;
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

    public void draw(Graphics g) {

    }
}
