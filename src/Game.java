import java.util.ArrayList;
import java.util.Scanner;

public class Game {
//    Instance variables
    private Player player1;
    private Player player2;
    private Deck deck;
    private ArrayList<Card> stack;
    private int turn;

//    Constructor
    public Game() {
//        Initializes the stack
        stack = new ArrayList<Card>();

//        Initializes who's turn it is
        turn = 0;

//        Initializes each player's hand
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        player1 = new Player("Player 1", hand1);
        player2 = new Player("Player 2", hand2);

//        Sets up the deck of cards
        String[] rank = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] suit = {"🟥", "🟦", "🟩", "🟨"};
        int[] value = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

//        Initializes the deck of cards
        deck = new Deck(rank, suit, value);
    }

//    Methods

//    Prints game name and instructions
    public void printInstructions() {
        System.out.println("                ____         ____      ____              ____        ____________");
        System.out.println("               |    |       |    |    |    \\            |    |      /            \\");
        System.out.println("               |    |       |    |    |      \\          |    |     /     ____     \\");
        System.out.println("               |    |       |    |    |        \\        |    |    |     /    \\     |");
        System.out.println("               |    |       |    |    |    |\\    \\      |    |    |    |      |    |");
        System.out.println("               |    |       |    |    |    |  \\    \\    |    |    |    |      |    |");
        System.out.println("               |    |       |    |    |    |    \\    \\  |    |    |    |      |    |");
        System.out.println("               |    |       |    |    |    |      \\    \\|    |    |    |      |    |");
        System.out.println("               |     \\_____/     |    |    |        \\        |    |     \\____/     |");
        System.out.println("                \\               /     |    |          \\      |     \\              /");
        System.out.println("  Welcome to     \\_____________/      |____|            \\____|      \\____________/");

        System.out.println("""
                
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\
                
                
                  Instructions
                
                * Each player starts with 7 cards
                * Each player takes turns playing cards onto the stack
                * If a player does not have a card of the same number or the same color, they must draw from the deck
                * Who ever plays all of their cards down first wins!
                
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""");
    }

//    Fills each player's hand with a certain amount of cards
    public void setHand(int amount) {
        if (deck.isEmpty()) {
            System.out.println("Error, no cards in deck");
            return;
        }
        deck.shuffle();
        for (int i = 0; i < amount; i++) {
            player1.getHand().add(deck.deal());
            player2.getHand().add(deck.deal());
        }
    }

//    Displays each player's hand
    public void printHand() {
        System.out.println("Player 1 hand:\n");
        for (int i = 0; i < player1.getHand().size(); i++) {
            System.out.println(i + " --> " + player1.getHand().get(i));
        }
        System.out.println("\nPlayer 2 hand:\n");
        for (int i = 0; i < player2.getHand().size(); i++) {
            System.out.println(i + " --> " + player2.getHand().get(i));
        }
    }

//    Displays the deck that players draw from
    public void printDeck() {
        for (int i = 0; i < deck.getCards().size(); i++) {
            System.out.println(deck.getCards().get(i) + " ");
        }
    }

//    Chooses one card from the deck to start the game
    public void setupStack() {
        stack.add(deck.deal());
    }

//    Displays stack where players put their cards
    public void printStack() {
        System.out.println("\n<Stack>\n" + stack.getFirst());
    }

//    Places a card from the player's hand onto the stack
    public void placeCard() {
        Scanner input = new Scanner(System.in);
        int index = 0;
        do {
            System.out.println("What card would you like to play? ");
            index = input.nextInt();
        } while (index < 0 || index >6);


    }

//    Checks if the card the player wants to place is valid
    public boolean isValidCard(Card card) {
        if (card.getSuit().equals(stack.getFirst().getSuit()) || card.getValue() == stack.getFirst().getValue()) {
            return true;
        }
        return false;
    }

//    Main game loop
    public void playGame() {
        printInstructions();
        setHand(7);
        printHand();
        setupStack();
        printStack();


    }

//    Main function
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
