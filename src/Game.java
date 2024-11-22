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
        System.out.println("Player 1 hand:");
        for (int i = 0; i < player1.getHand().size(); i++) {
            System.out.print(player1.getHand().get(i) + " ");
        }
        System.out.println("\n\nPlayer 2 hand:");
        for (int i = 0; i < player2.getHand().size(); i++) {
            System.out.print(player2.getHand().get(i) + " ");
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
        System.out.println("\n\n<Stack>\n" + stack.get(stack.size() - 1));
    }

//    Places a card from the player's hand onto the stack
    public void placeCard() {
        Scanner input = new Scanner(System.in);
        int index = 0;
        int max = 0;

//        Sets the size of the array to the correct player's hand
        if (turn == 0) {
            max = player1.getHand().size() - 1;
        }
        else{
            max = player2.getHand().size() - 1;
        }

//        Gets user input for the index of the card they want to play
        do {
            if (turn % 2 == 0) {
                System.out.println("\n\n[Player 1 turn]");
            }
            else {
                System.out.println("\n\n[Player 2 turn]");
            }

            System.out.println("(Type 0 to draw from the deck)\nWhat card would you like to play? ");
            index = (input.nextInt()) - 1;
        } while (index < -2 || index > max);

//        Before checking if it is a valid input, check if user wants to draw a card
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

//        Checks if the card is valid
        if (turn % 2 == 0) {
            if (isValidCard(player1.getHand().get(index))) {
                stack.add(player1.getHand().get(index));
                player1.getHand().remove(index);
                turn++;
                return;
            }
        }
        else {
            if (isValidCard(player2.getHand().get(index))) {
                stack.add(player2.getHand().get(index));
                player2.getHand().remove(index);
                turn++;
                return;
            }
        }
        System.out.println("Invalid Card");
    }

//    Checks if the card the player wants to place is valid
    public boolean isValidCard(Card card) {
        if ((card.getSuit().equals(stack.get(stack.size() - 1).getSuit())) || (card.getValue() == stack.get(stack.size() - 1).getValue())) {
            return true;
        }
        return false;
    }

//    Checks if any of the players lost
    public boolean ifLost() {
        if (player1.getHand().size() == 0 || player2.getHand().size() == 0) {
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
        while (!ifLost()) {
            placeCard();
            printStack();
            System.out.println("\n\n");
            printHand();
        }

    }

//    Main function
    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
