package resources;



import java.util.ArrayList;

public class Player {
    int playernumber;
    public ArrayList<Card> hand; // The players current hand
    public Possible_Moves move;
    public Boolean game_state; // True = int, False = out of the game
    public Boolean round_state; // True = int, False = out of the round
    public int card_sum; // Sum of the current cards in the players hand
    public int money; // Current money that the player has
    public int bet; // Players current bet



    //---------------------------------------------------------------------------------------------------------------------------

    public Player(int num){
        playernumber = num;
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
                DrawCard(deck);
                PrintInformation();
                System.out.println();
                CheckForBust();
                break;

            case DOUBLE:
                System.out.println("Player has double down");
                money -= bet;
                bet = bet*2;
                round_state = false;
                DrawCard(deck);
                CheckForBust();
                PrintInformation();
                break;

            case SURRENDER:
                System.out.println("Player has Surrendered and is now out of the round");
                System.out.println();
                money += bet/2;
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
        DrawCard(deck);
        DrawCard(deck);
        CheckForBust();

    }

    public void DrawCard(Deck deck){
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
    }//Adding up card sum for the player

    //---------------------------------------------------------------------------------------------------------------------------

    boolean CheckForBust(){
        if(card_sum > 21){
            round_state = false;
            if(playernumber != 0) {
                System.out.println("Player has busted with a " + card_sum + " Player is out of this round");
                System.out.println();
                return true;
            }else{
                System.out.println("Dealer has busted");
                System.out.println();
                return true;
            }
        }
        return false;
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

    //--------------------------------------------------------------------------------------------------------------------

        //prints all information on the player
    public String PrintInformation(){
    	String guiInformation = "Current bet: " + bet + "\nMoney: "+ money + "\nHand: " + card_sum;

        System.out.println();
        System.out.println("Player: " + playernumber);
        System.out.println("Players current bet: " + bet);
        System.out.println("Players current money: " + money);
        PrintHand();
        
        return guiInformation;
    }

    //---------------------------------------------------------------------------------------------------------------------

    public void resetPlayer(){
        this.bet = 0;
        this.hand = new ArrayList<Card>();
        this.card_sum = 0;
        this.round_state = true;
    }

}//player

