import resources.*;

public class Game_Main {

    public static void main(String[] args) {
        Deck deck = new Deck();

        for(int i = 0; i < deck.cards.size(); i++){
            System.out.println(deck.cards.get(i).suite + " " + deck.cards.get(i).value);

        }

        deck.shuffle();
        System.out.println();

        for(int i = 0; i < deck.cards.size(); i++){
            System.out.println(deck.cards.get(i).suite + " " + deck.cards.get(i).value);

        }
    }


}
