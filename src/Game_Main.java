import resources.*;

public class Game_Main {

    public static void main(String[] args) {
        Deck deck = new Deck();
        Player one = new Player();
        deck.Shuffle();

        one.DrawInitialHand(deck);
        one.MakeBet(100);
        one.PrintInformation();
        one.move = Possible_Actions.STAND;
        one.Play(deck);

        one.PrintInformation();


    }//main


}
