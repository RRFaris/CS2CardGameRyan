// Ryan Faris
// 12/5/2024

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Game implements MouseListener {
    // States of the game
    public static final int WELCOME = 0;
    public static final int PLAYING = 1;
    public static final int WIN_ONE = 2;
    public static final int WIN_TWO = 3;
    private int state;

    // Location of the mouse
    int mouseX;
    int mouseY;

    // Initialize players
    private Player player1;
    private Player player2;
    private Player player;

    private Deck deck;
    private ArrayList<Card> stack;

    private int turn;

    private final int LENGTH_SUIT = 13;

    // Initialize frontend
    private GameView window;

    // Create an array of card images
    private Image[][] images;

    // Constructor
    public Game() {
        // Initialize frontend
        window = new GameView(this);

        // Set game state to welcome screen
        state = WELCOME;

        // Initializes the stack
        stack = new ArrayList<Card>();

        // Initializes whose turn it is
        turn = 0;

        // Initializes each player's hand
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();
        player1 = new Player("Player 1", hand1);
        player2 = new Player("Player 2", hand2);
        player = null;

        // Sets up the deck of cards
        String[] rank = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "↻", "🚫", "+2"};
        String[] suit = {"🟥", "🟦", "🟩", "🟨"};
        int[] value = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 2};

        String[] wildRank = {"🌈", "+4"};
        int[] wildValue = {12, 4};

        images = new Image[4][LENGTH_SUIT*4];

        // Fill array with all images of cards
        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[0][i] = new ImageIcon("Resources/r" + i + ".png").getImage();
        }

        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[1][i] = new ImageIcon("Resources/b" + i + ".png").getImage();
        }

        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[2][i] = new ImageIcon("Resources/g" + i + ".png").getImage();
        }

        for (int i = 0; i < LENGTH_SUIT; i++) {
                images[3][i] = new ImageIcon("Resources/y" + i + ".png").getImage();
        }

        // Initialize wild card images
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                images[i][14] = new ImageIcon("Resources/blackWild.png").getImage();
                images[i][15] = new ImageIcon("Resources/blackPlus4.png").getImage();
            }
        }

        // Initializes the deck of cards
        deck = new Deck(rank, suit, value, wildRank, wildValue, images);

        // Add a new mouse listener
        window.addMouseListener(this);
    }

    // Mouse stuff
    @Override
    public void mouseClicked(MouseEvent e) {
        // Necessary for code to run
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        state = PLAYING;
        window.repaint();

        if (turn % 2 == 0)
            player = player1;
        else
            player = player2;

        if (state == PLAYING) {
            for (Card c : player.getHand()) {
                if (c.isClicked(mouseX, mouseY, c)) {
                    placeCard(c);
                    break;
                }
            }
            if ((mouseX > GameView.DECK_X && mouseX < GameView.DECK_X + Card.CARD_WIDTH) && (mouseY > GameView.DECK_Y && mouseY < GameView.DECK_Y + Card.CARD_HEIGHT)) {
                player.getHand().add(deck.deal());
                turn++;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Necessary for code to run
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Necessary for code to run
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Necessary for code to run
    }

    // Getter methods
    public Image[][] getImages() {
        return images;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getP1Hand() {
        return player1.getHand();
    }

    public ArrayList<Card> getP2Hand() {
        return player2.getHand();
    }

    public Card getStack() {
        return stack.getLast();
    }

    public int getState() {
        return state;
    }

    public int getTurn() {
        return turn;
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
            deck.shuffle();
        } while (isAddition(card) || isWild(card) || card.getRank().equals("↻") || card.getRank().equals("🚫"));
        stack.add(card);
    }

    // Displays stack where players put their cards
    public void printStack() {
        System.out.println("\n\n\n\t\2\t\t\t\t<Stack>\n\t\t\t\t\t" + stack.getLast());
    }

    // Places a card from the player's hand onto the stack
    public void placeCard(Card card) {
        Player otherPlayer = null;

        // Sets Player variables to the correct player
        if (turn %2 == 0) {
            player = player1;
            otherPlayer = player2;
        }
        else{
            player = player2;
            otherPlayer = player1;
        }

        ArrayList<Card> hand = player.getHand();

        // Give other player more cards if special card is played
        if (isAddition(card) && isValidCard(card)) {
            for (int i = 0; i < card.getValue(); i++) {
                otherPlayer.getHand().add(deck.deal());
            }
        }

        // Asks user to switch the color when they play a wild card
        if (isWild(card)) {
            stack.add(card);
            Scanner s = new Scanner(System.in);
            String suit;
            do {
                System.out.println("What color do you want to change it to? ");
                suit = s.nextLine();
            } while (!suit.equals("blue") && !suit.equals("red") && !suit.equals("yellow") && !suit.equals("green"));

            String cardSuit = "";
            switch (suit) {
                case "blue":
                    cardSuit = "🟦";
                    System.out.println("blue chosen");
                    break;
                case "red":
                    cardSuit = "🟥";
                    System.out.println("red chosen");
                    break;
                case "yellow":
                    cardSuit = "🟨";
                    System.out.println("yellow chosen");
                    break;
                case "green":
                    cardSuit = "🟩";
                    System.out.println("green chosen");
                    break;
                }


            stack.add(new Card(card.getRank(), cardSuit, 19, card.getCardImage()));
            hand.remove(card);
            turn++;
            return;
        }

        // Checks if the card is valid
        if(isValidCard(card)) {
            stack.add(card);
            if (!card.getRank().equals("↻") && !card.getRank().equals("🚫")) {
                turn++;
            }
            hand.remove(card);
            return;
        }

        System.out.println("Invalid Card");
    }

    // Checks if the card the player wants to place is valid
    public boolean isValidCard(Card card) {
        return (card.getSuit().equals(stack.getLast().getSuit())) || (card.getValue() == stack.getLast().getValue()) || isWild(card);
    }

    // Checks if any of the players lost
    public boolean ifLost() {
        return player1.getHand().isEmpty() || player2.getHand().isEmpty();
    }

    public String getWinner() {
        if (player1.getHand().isEmpty())
            return player1.getName();
        else
            return player2.getName();
    }

    // Checks if the card the user plays is a special card
    public boolean isAddition(Card card) {
        return card.getRank().equals("+2") || card.getRank().equals("+4");
    }

    public boolean isWild(Card card) {
        if (card.getSuit().equals("⬛️")) {
            return true;
        }
        return false;
    }

    // Main game loop
    public void playGame() {
        window.repaint();
        printInstructions();
        setHand(7);
        setupStack();
        if (state == PLAYING) {
            while (!ifLost()) {
                printHand();
                printStack();
                window.repaint();
                for (int i = 0; i < 20; i++) {
                    System.out.println("\n");
                }
            }
            if (getWinner().equals("Player 1"))
                state = WIN_ONE;
            else
                state = WIN_TWO;
        }
    }

    // Main function
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
