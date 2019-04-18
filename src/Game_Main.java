import resources.*;

import javax.swing.*;
import java.util.*;

import static resources.GUIF.createGui;

public class Game_Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        GUIF BlackJackGUI = new GUIF();
        BlackJackGUI.createGui();


        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<Player>();
        System.out.println("Welcome to BlackJack");
        System.out.println();


        System.out.print("How many deck would you like to play with(1-8): ");
        int deckNum = 0;
        //Getting the number of decks that the player wished to play with
        while(true){
            String userInput = input.next();
            if(IsNumber(userInput)){
                deckNum = Integer.parseInt(userInput);
                if(0 < deckNum && deckNum < 9){
                    deckNum--;
                    break;
                }
            }
            System.out.print("Invalid input Please enter a valid number between 1 and 8.");
        }
        //creating the additional decks
        for (int i = 0; i < deckNum; i++){
            deck.AddDeck(new Deck());
        }


        //Getting number of players that are playing max 4
        System.out.print("How many players would you like to play with(1-4): ");
        int playerCounter = 0;
        while(true){
            String userInput = input.next();
            if(IsNumber(userInput)){
                playerCounter = Integer.parseInt(userInput);
                if(0 < playerCounter && playerCounter < 9){
                    break;
                }
            }
            System.out.print("Invalid input Please enter a valid number between 1 and 4.");
        }

        for (int i = 1; i <= playerCounter; i++) {
            players.add(new Player(i));
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).PrintInformation();
        }


        StartGame(deck,players);






    }//main

    //---------------------------------------------------------------------------------------------------------------------------

    static void StartGame(Deck deck, ArrayList<Player> players){
        System.out.print("How many round do you wish to play(1-10) ");
        int rounds = 0;
        while(true){
            String userInput = input.next();
            if(IsNumber(userInput)){
                rounds = Integer.parseInt(userInput);
                if(0 < rounds && rounds < 9){
                    break;
                }
            }
            System.out.print("Invalid input Please enter a valid number between 1 and 10.");
        }
        for (int i = 0; i < rounds; i++) {
            System.out.println("Welcome to round " + (i+1));
            PrintPlayers(players);
            PlayRound(deck, players);
        }

    }

    //---------------------------------------------------------------------------------------------------------------------------

    static void PlayRound(Deck deck, ArrayList<Player> players){
        Player dealer = new Player(0);


        //Getting initial bets from all non dealer players
        for (int i = 0; i < players.size(); i++){
        System.out.print("Player " + (i+1) + " please enter you bet for this round: ");
            while(true){
                String userinput = input.next();
                if(IsNumber(userinput)){
                    int temp = Integer.parseInt(userinput);
                    if(0 < temp && temp <= players.get(i).money){
                        players.get(i).MakeBet(temp);
                        break;
                    }
                }
                System.out.print("Please enter a valid number between 1 and " + players.get(i).money + ": ");
            }
        }// end for loop

        DealInitialHands(players, deck);
        PrintPlayers(players);


        dealer.DrawInitialHand(deck);
        System.out.println("The dealer is currently showing a " + dealer.hand.get(0).value + " " + dealer.hand.get(0).suite);
            if (dealer.hand.get(0).value == Value.ACE){
                //insurance clause
            }

        CheckForBlackJack(players,dealer);


        for (int i = 0; i < players.size(); i++) {
            while(players.get(i).round_state){
                System.out.println("Player " + (i+1) + " what would you like to do");
                System.out.println("Your current hand is worth: " + players.get(i).card_sum);

                while(true){
                    String playerMove;
                    System.out.println("1: HIT");
                    System.out.println("2: STAY");
                    if(players.get(i).hand.size() == 2){
                        System.out.println("3: SURRENDER");
                        if(players.get(i).money >= players.get(i).bet) {
                            System.out.println("4: DOUBLE");
                        }
                    }
                    playerMove = input.next();
                    if(IsNumber(playerMove)){
                        int temp = Integer.parseInt(playerMove);
                        if(players.get(i).hand.size() == 2 && players.get(i).money >= players.get(i).bet){
                            if(0 < temp && temp <= 4){
                                switch(temp){
                                    case 1://Hit
                                        players.get(i).move = Possible_Moves.HIT;
                                        break;
                                    case 2: //Stand
                                            players.get(i).move = Possible_Moves.STAND;
                                        break;
                                    case 3://Surrender
                                        players.get(i).move = Possible_Moves.SURRENDER;
                                        break;
                                    case 4: // Double
                                        players.get(i).move = Possible_Moves.DOUBLE;
                                        break;
                                }
                                players.get(i).Play(deck);
                                break;
                            }
                            System.out.println("Please enter a valid number between 1 and 4");
                        }else if (players.get(i).hand.size() == 2){
                            if(0 < temp && temp <= 4) {
                                switch (temp) {
                                    case 1://Hit
                                        players.get(i).move = Possible_Moves.HIT;
                                        break;
                                    case 2: //Stand
                                        players.get(i).move = Possible_Moves.STAND;
                                        break;
                                    case 3://Surrender
                                        players.get(i).move = Possible_Moves.SURRENDER;
                                        break;
                                }
                                players.get(i).Play(deck);
                                break;
                            }
                        }else{
                            if(0 < temp && temp <= 2){
                                switch(temp){
                                    case 1: // Hit
                                        players.get(i).move = Possible_Moves.HIT;
                                        break;
                                    case 2:// Stand
                                        players.get(i).move = Possible_Moves.STAND;
                                        break;
                                }
                                players.get(i).Play(deck);
                                break;
                            }
                            System.out.println("Please enter a valid number between 1 and 2");
                        }
                    }
                }//while loop
            }// while round state
        }// for players

        //After all of the players have played out their hands dealer goes
        //dealer will play until they have atleast a 17 card sum
        while(dealer.card_sum < 17){
            dealer.DrawCard(deck);
        }

        CheckforWinners(players, dealer);




    }

    //---------------------------------------------------------------------------------------------------------------------------

    static void CheckforWinners(ArrayList<Player> players, Player dealer){
        int dealer_sum = dealer.card_sum;
        System.out.println("Dealers sum is: " + dealer_sum);
        for (int i = 0; i < players.size(); i++) {

            //player loses and loses bet
            if(players.get(i).card_sum > 21){
                System.out.println("Player " + (i+1) + " lost.");
                System.out.println();

                players.get(i).bet = 0;
                players.get(i).hand = new ArrayList<Card>();
                players.get(i).card_sum = 0;

                //player has blackjack
            }else if(players.get(i).card_sum  == 21 && players.get(i).card_sum != dealer_sum){
                System.out.println("Player " + (i+1) + " has won with a BlackJack");
                System.out.println();


                players.get(i).bet = 0;
                players.get(i).hand = new ArrayList<Card>();
                players.get(i).card_sum = 0;

                //player wins gets even money
            }else if(players.get(i).card_sum > dealer_sum || (players.get(i).card_sum < 21 && dealer_sum > 21)){
                //player wins gets even money
                System.out.println("Player " + (i+1) + " won. They have been paid: " + players.get(i).bet);
                System.out.println();

                players.get(i).money += (players.get(i).bet*2);
                players.get(i).bet = 0;
                players.get(i).hand = new ArrayList<Card>();
                players.get(i).card_sum = 0;

                //player pushes and gets
            }else if(players.get(i).card_sum == dealer_sum){
               //player pushes get only their bet back
                System.out.println("Player " + (i+1) + " pushed.");
                System.out.println();

                players.get(i).money += players.get(i).bet;
                players.get(i).bet = 0;
                players.get(i).hand = new ArrayList<Card>();
                players.get(i).card_sum = 0;

            }else{
                //player loses and loses bet
                System.out.println("Player " + (i+1) + " lost.");
                System.out.println();

                players.get(i).bet = 0;
                players.get(i).hand = new ArrayList<Card>();
                players.get(i).card_sum = 0;

            }

            players.get(i).round_state = true;
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    static void DealInitialHands(ArrayList<Player> players, Deck deck){
        for (int i = 0; i < players.size(); i++) {
            players.get(i).DrawInitialHand(deck);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public static void PrintPlayers(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {
            players.get(i).PrintInformation();
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public static void CheckForBlackJack(ArrayList<Player> players, Player dealer){
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).card_sum == 21){
                if(dealer.card_sum != 21){
                    System.out.println("Player " + (i+1) + " has a BlackJack");
                    players.get(i).money += players.get(i).bet + (players.get(i).bet * 1.5);
                    players.get(i).bet = 0;

                }else{
                    System.out.println("Player " + (i+1) + " has a BlackJack, As does the dealer");
                    players.get(i).money += players.get(i).bet + (players.get(i).bet);
                    players.get(i).bet = 0;
                }
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public static boolean IsNumber(String stringNum) {
        try {
            double d = Double.parseDouble(stringNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
