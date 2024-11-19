import java.util.ArrayList;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] rank, String[] suit, int[] value) {
        cards = new ArrayList<Card>();

        cardsLeft = cards.size();
    }

    // Methods
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    // Returns number of cards
    public int getCardsLeft() {
        return cardsLeft;
    }

    // Pulls a card out of the deck and returns it
    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        return
    }

    // Shuffles the deck
    public void shuffle() {

    }
}

