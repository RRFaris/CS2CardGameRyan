// Ryan Faris
// 12/5/2024

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // Instance variables
    private Player player1;
    private Player player2;
    private Deck deck;
    private ArrayList<Card> stack;
    private int turn;

    private final int LENGTH_SUIT = 12;

    // Initialize frontend
    private GameView window;

    // Create an array of card images
    private Image[][] images;

    // Constructor
    public Game() {
        // Initialize frontend
        window = new GameView(this);

        // Initializes the stack
        stack = new ArrayList<Card>();

        // Initializes who's turn it is
        turn = 0;

        // Initializes each player's hand
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        player1 = new Player("Player 1", hand1);
        player2 = new Player("Player 2", hand2);

        // Sets up the deck of cards
        String[] rank = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "↻", "🚫"};
        String[] suit = {"🟥", "🟦", "🟩", "🟨"};
        int[] value = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        // Initializes the deck of cards
        deck = new Deck(rank, suit, value);

        images = new Image[4][LENGTH_SUIT*4];

        // Fill array with all images of cards
        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[0][i] = new ImageIcon("Resources/g" + i + ".png").getImage();
        }

        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[1][i] = new ImageIcon("Resources/b" + i + ".png").getImage();
        }

        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[2][i] = new ImageIcon("Resources/y" + i + ".png").getImage();
        }

        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[3][i] = new ImageIcon("Resources/r" + i + ".png").getImage();
        }
    }

    // Methods

    public Image[][] getImages() {
        return images;
    }

    // Prints game name and instructions
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

    // Fills each player's hand with a certain amount of cards
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

    // Displays each player's hand
    public void printHand() {
        System.out.println("Player 1 hand:");

        for (int i = 0; i < player1.getHand().size(); i++) {
            System.out.print(" " + (i + 1) + ":");
            System.out.print(player1.getHand().get(i) + " ");
        }

        System.out.println("\n\nPlayer 2 hand:");

        for (int i = 0; i < player2.getHand().size(); i++) {
            System.out.print(" " + (i + 1) + ":");
            System.out.print(player2.getHand().get(i) + " ");
        }
    }

    // Displays the deck that players draw from
    public void printDeck() {
        for (int i = 0; i < deck.getCards().size(); i++) {
            System.out.println(deck.getCards().get(i) + " ");
        }
    }

    // Chooses one card from the deck to start the game
    public void setupStack() {
        Card card;
        do {
            card = deck.deal();
            if (card.getRank().equals("+2") || card.getRank().equals("+4") || card.getRank().equals("↻") || card.getRank().equals("🚫")) {
                deck.shuffle();
            }
        } while (card.getRank().equals("+2") || card.getRank().equals("+4") || card.getRank().equals("↻") || card.getRank().equals("🚫"));
        stack.add(card);

    }

    // Displays stack where players put their cards
    public void printStack() {
        System.out.println("\n\n\n\t\2\t\t\t\t<Stack>\n\t\t\t\t\t" + stack.get(stack.size() - 1));
    }

    // Places a card from the player's hand onto the stack
    public void placeCard() {
        Scanner input = new Scanner(System.in);
        int index = 0;
        int max = 0;

        Card card1 = null;
        Card card2 = null;

        // Sets the size of the array to the correct player's hand
        if (turn %2 == 0) {
            max = player1.getHand().size() - 1;
        }
        else{
            max = player2.getHand().size() - 1;
        }

        // Gets user input for the index of the card they want to play
        do {
            if (turn %2 == 0) {
                System.out.println("\n\n[Player 1 turn]\n");
            }
            else {
                System.out.println("\n\n[Player 2 turn]\n");

            }

            System.out.println("(Type 0 to draw from the deck)\nWhat card would you like to play? ");
            index = (input.nextInt()) - 1;

            if (index == -1) {
                max++;
            }
        } while (index < -2 || index > max);

        // Before checking if it is a valid input, check if user wants to draw a card
        if (turn %2 == 0 && index == -1) {
            player1.getHand().add(deck.deal());
            turn++;
            return;
        }

        if (turn %2 != 0 && index == -1) {
            player2.getHand().add(deck.deal());
            turn++;
            return;
        }

        // Sets temporary variables to the player's card because removing cards mess up the index's and therefore the cards
        if (turn %2 == 0 ){
            card1 = player1.getHand().get(index);
            card2 = player2.getHand().get(0);
        }
        else {
            card2 = player2.getHand().get(index);
            card1 = player1.getHand().get(0);
        }


        // Give other player more cards if special card is played
        if (isSpecial(card1) && turn %2 == 0) {
            for (int i = 0; i < card1.getValue(); i++) {
                player2.getHand().add(deck.deal());
            }
        }

        if (isSpecial(card2) && turn %2 != 0) {
            for (int i = 0; i < card2.getValue(); i++) {
                player1.getHand().add(deck.deal());
            }
        }

        // Checks if the card is valid
        if (turn % 2 == 0) {
            if (isValidCard(card1)) {
                stack.add(card1);
                if (!card1.getRank().equals("↻") && !card1.getRank().equals("🚫")) {
                    turn++;
                }
                player1.getHand().remove(index);
                return;
            }
        }
        else {
            if (isValidCard(card2)) {
                stack.add(card2);
                if (!card2.getRank().equals("↻") && !card2.getRank().equals("🚫")) {
                    turn++;
                }
                player2.getHand().remove(index);
                return;
            }
        }
        System.out.println("Invalid Card");
    }

    // Checks if the card the player wants to place is valid
    public boolean isValidCard(Card card) {
        return (card.getSuit().equals(stack.get(stack.size() - 1).getSuit())) || (card.getValue() == stack.get(stack.size() - 1).getValue());
    }

    // Checks if any of the players lost
    public boolean ifLost() {
        return player1.getHand().isEmpty() || player2.getHand().isEmpty();
    }

    // Checks if the card the user plays is a special card
    public boolean isSpecial(Card card) {
        return card.getRank().equals("+2") || card.getRank().equals("+4");
    }

    // Main game loop
    public void playGame() {
        printInstructions();
        setHand(7);
        setupStack();
        window.repaint();
        while (!ifLost()) {
            printHand();
            printStack();
            placeCard();
            window.repaint();
            for (int i = 0; i < 20; i++) {
                System.out.println("\n");
            }

        }
    }

    // Main function
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
