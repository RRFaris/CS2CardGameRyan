import java.util.ArrayList;

public class Player {
    // Instance variables
    private final String name;
    private ArrayList<Card> hand;
    private int points;

    // Constructor
    public Player(String name) {
        this.name = name;
        points = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        points = 0;
        this.hand = hand;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    // Methods
    public void addPoints(int points) {
        this.points += points;
    }

    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    public String toString() {
        return this.name + " has " + this.points + " points \n" + this.name + "'s cards: " + hand;
    }
}
