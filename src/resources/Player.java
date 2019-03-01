package resources;

import resources.Card;

import java.util.ArrayList;

public class Player {

    ArrayList<Card> hand; // The players current hand
    Boolean action_state;
    Boolean game_state; // True = int, False = out of the game
    Boolean round_state; // True = int, False = out of the round
    int card_sum; // Sum of the current cards in the players hand
    int money; // Current money that the player has
    int bet; // Players current bet

    Player(){
        card_sum = 0;
        money = 1000;
        bet = 0;
        game_state = true;
        round_state = true;
    }

}

