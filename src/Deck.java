import java.util.ArrayList;
import java.awt.*;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] rank, String[] suit, int[] value, String[] wildRank, int[] wildValue, Image[][] images) {
        cards = new ArrayList<Card>();
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                cards.add(new Card(rank[j], suit[i], value[j], images[i][j]));
            }
        }

        // Initialize wild cards
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                cards.add(new Card(wildRank[j], "⬛️", wildValue[j], images[i][j + 14]));
            }
        }

        cardsLeft = cards.size();
    }

    // Methods

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
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
        return cards.get(--cardsLeft);
    }

    // Shuffles the deck
    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int num = (int)(Math.random() * cards.size());
            Card temp = cards.get(i);
            cards.set(i, cards.get(num));
            cards.set(num, temp);
        }
        cardsLeft = cards.size();
    }
}

