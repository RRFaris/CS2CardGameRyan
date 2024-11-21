import java.util.ArrayList;

public class Deck {
    // Instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] rank, String[] suit, int[] value) {
        cards = new ArrayList<Card>();
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                cards.add(new Card(rank[j], suit[i], value[j]));
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

