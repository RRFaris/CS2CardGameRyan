import java.util.ArrayList;

public class Player {
//    Instance variables
    private String name;
    private ArrayList<Card> hand;
    private int points;

//    Constructor
    public Player(String name) {
        this.name = name;
        points = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        points = 0;
        hand = new ArrayList<Card>();
    }

//    Methods

}
