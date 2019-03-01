package resources;

import resources.Card;

import java.util.ArrayList;

public class Player {

    public ArrayList<Card> hand; // The players current hand
    public Possible_Actions move;
    public Boolean game_state; // True = int, False = out of the game
    public Boolean round_state; // True = int, False = out of the round
    public int card_sum; // Sum of the current cards in the players hand
    public int money; // Current money that the player has
    public int bet; // Players current bet



    //---------------------------------------------------------------------------------------------------------------------------

    public Player(){
        card_sum = 0;
        money = 1000;
        bet = 0;
        hand = new ArrayList<Card>();

        game_state = true;
        round_state = true;
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public void Play(Deck deck){
        switch(this.move){
            case HIT:
                GetCard(deck);
                System.out.println("Players card is " +hand.get(hand.size()-1).value + " " + hand.get(hand.size()-1).suite);
                System.out.println("Players current hand is worth: " + card_sum);
                System.out.println();
                break;

            case DOUBLE:
                System.out.println("Player has double down");
                GetCard(deck);
                money  = money - bet;
                bet = bet*2;
                round_state = false;
                PrintInformation();
                break;

            case SURRENDER:
                System.out.println("Player has Surrendered and is now out of the round");
                System.out.println();
                money += bet/2;
                bet = 0;
                round_state = false;
                break;

            case STAND:
                System.out.println("Player has decided to Stand");
                System.out.println();
                round_state = false;
                break;

        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public void DrawInitialHand(Deck deck){
        GetCard(deck);
        GetCard(deck);
        AddCardSum();

    }

    public void GetCard(Deck deck){
        hand.add(deck.Draw());
        AddCardSum();
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public void MakeBet(int value){
        System.out.println("Player has bet: " + value);
        bet += value;
        money -= value;
        System.out.println("Current bet is: " + bet);
        System.out.println();
    }

    //---------------------------------------------------------------------------------------------------------------------------

   public void AddCardSum(){
       int acecounter = 0;
        card_sum = 0;
        for (int i = 0; i < hand.size(); i++){
            switch (hand.get(i).value){
                case ACE:
                    acecounter++;
                    card_sum += 11;
                    break;
                case TWO:
                    card_sum += 2;
                    break;
                case THREE:
                    card_sum += 3;
                    break;
                case FOUR:
                    card_sum += 4;
                    break;
                case FIVE:
                    card_sum += 5;
                    break;
                case SIX:
                    card_sum += 6;
                    break;
                case SEVEN:
                    card_sum += 7;
                    break;
                case EIGHT:
                    card_sum += 8;
                    break;
                case NINE:
                    card_sum += 9;
                    break;
                case TEN:
                    card_sum += 10;
                    break;
                case JACK:
                    card_sum += 10;
                    break;
                case QUEEN:
                    card_sum += 10;
                    break;
                case KING:
                    card_sum += 10;
                    break;
            }
            while(acecounter > 0 && card_sum > 21){
                card_sum -= 10;
                acecounter--;
            }

        }//for loop
        CheckforBust();
    }//Adding up card sum for the player

    //---------------------------------------------------------------------------------------------------------------------------

    void CheckforBust(){
        if(card_sum > 21){
            round_state = false;
            bet = 0;
            System.out.println("Player has busted with a " + card_sum + " Player is out of this round");
            System.out.println();
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
        //Prints the players current hand
    public void PrintHand(){
        System.out.println("Players hand is worth: " + card_sum);
        System.out.println("Players hand is: ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(hand.get(i).suite + " " + hand.get(i).value);
        }
        System.out.println();
    }
        //prints all information on the player
    public void PrintInformation(){
        PrintHand();
        System.out.println("Players current bet: " + bet);
        System.out.println("Players current money: " + money);
        System.out.println();
    }

}//player

