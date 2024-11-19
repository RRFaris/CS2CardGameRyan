import java.util.ArrayList;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck() {

    }

    // Methods
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        return
    }
}

