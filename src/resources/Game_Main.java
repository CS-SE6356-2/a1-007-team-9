package resources;



import javax.swing.*;
import java.util.*;

public class Game_Main {
    private static Player currentPlayer;
    public static Deck deck;
    protected static GameWindow BlackJackGUI;
    
    public static void main(String[] args) {
    	BlackJackGUI = new GameWindow();
    	BlackJackGUI.frmBlackjack.setVisible(true);

        deck = new Deck();
        ArrayList<Player> players = new ArrayList<Player>();
        System.out.println("Welcome to BlackJack");
        BlackJackGUI.setOutput("Welcome to BlackJack");
        System.out.println();

        int playerCounter = 4;

        for (int i = 1; i <= playerCounter; i++) {
            players.add(new Player(i));
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).PrintInformation();
        }

        StartGame(deck,players);
        System.out.println("Thanks for playing players won");
        for(int i = 0; i < players.size(); i++){
            System.out.println("Player " + (i+1) + " won: " + players.get(i).money);
            System.out.println();
        }
        return;
    }//main

    //---------------------------------------------------------------------------------------------------------------------------

    static void StartGame(Deck deck, ArrayList<Player> players){
        int rounds = 0;
        while(true){
            String roundamt = JOptionPane.showInputDialog("How many rounds do you wish to play(1-10) ");
            System.out.println(roundamt);
            if(IsNumber(roundamt)){
                rounds = Integer.parseInt(roundamt);
                if(0 < rounds && rounds < 11){
                    break;
                }
            }
            System.out.print("Invalid input Please enter a valid number between 1 and 10.");
        }
        for (int i = 0; i < rounds; i++) {
            System.out.println("Welcome to round " + (i+1));
            BlackJackGUI.setRoundLabel(Integer.toString(i +1));
            PrintPlayers(players);
            PlayRound(deck, players);
        }
        
        BlackJackGUI.disableBtns();
        Player GameOver = new Player(-1);
        PopUpWindow GameOverFrame = new PopUpWindow(GameOver, "");
        GameOverFrame.setVisible(true);
        
        return;
    }

    //---------------------------------------------------------------------------------------------------------------------------

    static void PlayRound(Deck deck, ArrayList<Player> players){
    	resetHand(players);
        Player dealer = new Player(0);

        makeBet(players);
        DealInitialHands(players, deck);
        PrintPlayers(players);

        dealer.DrawInitialHand(deck);
        initialDealerHandLabel(dealer);


        System.out.println("The dealer is currently showing a " + dealer.hand.get(0).value + " " + dealer.hand.get(0).suite);
        BlackJackGUI.setDealerHand(dealer.hand.get(0).value + " " + dealer.hand.get(0).suite);

        if (dealer.hand.get(0).value == Value.ACE){
                insurance(players, dealer);
            }

        CheckForBlackJack(players);

        for (int i = 0; i < players.size(); i++) {
            currentPlayer = players.get(i);
            BlackJackGUI.setPlayerNumber(Integer.toString(currentPlayer.playernumber));
            
            BlackJackGUI.setPlayerHand(players.get(i).hand.get(0).value.name()+ " "+ players.get(i).hand.get(0).suite.toString()); //setting the gui player hand label

            while(players.get(i).round_state){
                //print player hand
                setPlayerHandLabel(players.get(i));
                


            }// while round state
        }// for players

        //After all of the players have played out their hands dealer goes
        //dealer will play until they have at least a 17 card sum
        while(dealer.card_sum < 17){
            dealer.DrawCard(deck);
            setDealerHandLabel(dealer);
        }
        setDealerHandLabel(dealer);

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

                players.get(i).resetPlayer();

                //player wins gets even money
            }else if(players.get(i).card_sum == 21 && (players.get(i).hand.size() == 2) ){
                //player wins gets even money
            	PopUpWindow BlackJackPopUp = new PopUpWindow(players.get(i), "has BlackJack");
            	BlackJackPopUp.setVisible(true);
                System.out.println("Player " + (i+1) + " won, with a blackjack " );
                System.out.println();
                players.get(i).resetPlayer();


                //player pushes and gets
            }else if(players.get(i).card_sum > dealer_sum || (players.get(i).card_sum < 21 && dealer_sum > 21)){
                //player wins gets even money
                System.out.println("Player " + (i+1) + " won. They have been paid: " + players.get(i).bet);
                System.out.println();

                players.get(i).money += (players.get(i).bet*2);
                players.get(i).resetPlayer();


                //player pushes and gets
            }else if(players.get(i).card_sum == dealer_sum){
               //player pushes get only their bet back
                System.out.println("Player " + (i+1) + " pushed.");
                System.out.println();

                players.get(i).money += players.get(i).bet;
                players.get(i).resetPlayer();


            }else{
                //player loses and loses bet
                System.out.println("Player " + (i+1) + " lost.");
                System.out.println();

                players.get(i).resetPlayer();
                dealer.resetPlayer();

            }
        }

    }//check for winner

    //---------------------------------------------------------------------------------------------------------------------------

    static void DealInitialHands(ArrayList<Player> players, Deck deck){
        for (int i = 0; i < players.size(); i++) {
            players.get(i).DrawInitialHand(deck);
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public static void PrintPlayers(ArrayList<Player> players){
        for (int i = 0; i < players.size(); i++) {
        	
            String playerInfo = players.get(i).PrintInformation();
            
            int playerNumber = i + 1;
            
            switch(playerNumber) {
	            case 1: BlackJackGUI.setPlayer1Info(playerInfo);
	            		break;
	            case 2: BlackJackGUI.setPlayer2Info(playerInfo);
	            		break;
	            case 3: BlackJackGUI.setPlayer3Info(playerInfo);
	            		break;
	            case 4: BlackJackGUI.setPlayer4Info(playerInfo);
	            		break;
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public static void CheckForBlackJack(ArrayList<Player> players){
        //players.get(0).card_sum = 21;
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).card_sum == 21){
            	
//            	PopUpWindow BlackJackPopUp = new PopUpWindow(players.get(i), "has won with a BlackJack\"");
//                BlackJackPopUp.setVisible(true);

                players.get(i).money += players.get(i).bet + (players.get(i).bet * 1.5);
                players.get(i).bet = 0;
                players.get(i).round_state = false;
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

    //---------------------------------------------------------------------------------------------------------------------

    //different move the play can take
    public static void hit(Player current, Deck deck) { 
        current.move = Possible_Moves.HIT;
        current.Play(deck);
        
        setPlayerHandLabel(current);
        
        String playerInfo = current.PrintInformation();
        int playerNumber = current.playernumber;

        switch(playerNumber) {
        case 1: BlackJackGUI.setPlayer1Info(playerInfo);
        		break;
        case 2: BlackJackGUI.setPlayer2Info(playerInfo);
        		break;
        case 3: BlackJackGUI.setPlayer3Info(playerInfo);
        		break;
        case 4: BlackJackGUI.setPlayer4Info(playerInfo);
        		break;
    }
        
        if (current.CheckForBust()) {
        	//show busted
        	PopUpWindow PlayerBust = new PopUpWindow(current, "busted");
        	PlayerBust.setVisible(true);
        }
        
    }

    public static void stand(Player current, Deck deck){
        current.move = Possible_Moves.STAND;
        current.Play(deck);
        setPlayerHandLabel(current);
    }

    public static void surrender(Player current, Deck deck) {
        if((current.hand.size() == 2)){//TODO update bet in gui and money in gui
            current.move = Possible_Moves.SURRENDER;
            current.Play(deck);
            setPlayerHandLabel(current);
        }else{
            //gui splash to say invalid move
        	PopUpWindow Invalid = new PopUpWindow(current, "made an invalid move");
        	Invalid.setVisible(true);
        }
    }

    public static void doublebet(Player current, Deck deck) {
        if((current.hand.size() == 2 && (current.money >= current.bet))) {//TODO update bet in gui and money
            current.move = Possible_Moves.DOUBLE;
            current.Play(deck);
            setPlayerHandLabel(current);
            if (current.CheckForBust()) {
                //show busted
                PopUpWindow PlayerBust = new PopUpWindow(current, "busted");
                PlayerBust.setVisible(true);
            }
        }else{
            //gui splash to say invalid move
        	PopUpWindow Invalid = new PopUpWindow(current, "made an invalid move");
        	Invalid.setVisible(true);
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    //getting first bets from the players
    public static void makeBet(ArrayList<Player>players){
        //Getting initial bets from all non dealer player
        for (int i = 0; i < players.size(); i++){
            System.out.print("Player " + (i+1) + " please enter you bet for this round: ");
            if(players.get(i).money != 0) {
                while (true) {
                    String betamt = JOptionPane.showInputDialog("Player " + (i + 1) + " please enter a valid bet amount between 1 and " + players.get(i).money + "or 0 if you wish to sit out");
                    System.out.println(betamt);
                    if (IsNumber(betamt)) {
                        int temp = Integer.parseInt(betamt);
                        if (0 <= temp && temp <= players.get(i).money) {
                            players.get(i).MakeBet(temp);
                            if(temp == 0){
                                players.get(i).round_state = false;
                            }
                            break;
                        }
                    }
                    System.out.print("Please enter a valid number between 1 and " + players.get(i).money + ": ");
                }
            }

        }// end for loop
    }

    //------------------------------------------------------------------------------------------------------------------

    public static void insurance(ArrayList<Player> players, Player dealer){//TODO update player information if bet is paid out or not
        System.out.println("Dealer is currently showing a Ace.");
        dealer.card_sum = 21;
        for(int i = 0; i < players.size(); i++){
            int insurance_bet = 0;
            players.get(i).PrintInformation();
            System.out.println("Player " + i+1 + " would you like to make a insurance bet: Y or N");
            String userinput;

            while(true) {
                userinput = JOptionPane.showInputDialog("Player " + (i+1) + " would you like to make a insurance bet: Y or N");
                userinput = userinput.toUpperCase();
                if (userinput.equals("Y") || userinput.equals("N")) {
                   break;
                }//getting user input
            }//while


            if(userinput.equals('Y')){
                insurance_bet = players.get(i).bet/2;

                if(dealer.card_sum == 21){
                    players.get(i).money += insurance_bet;
                    players.get(i).round_state = false;
                }else{
                    players.get(i).money -= insurance_bet;
                }
            }//Yes to insurance

        }//player loop

        if(dealer.card_sum == 21){
            System.out.println("Dealer has a BlackJack insurance is paid out");
            System.out.println();
        }else{
            System.out.println("Dealer did not have a BlackJack insurance is collected");
            System.out.println();
        }
    }//insurance

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    
    public static void setPlayerHandLabel(Player currentPlayer) {

        String playerHandString = "";
        for (int j =0; j< currentPlayer.hand.size(); j++) {
        	try {
        		playerHandString += currentPlayer.hand.get(j).value.name() + "-" + currentPlayer.hand.get(j).suite.toString() + "    ";
        	}catch (Exception e) {
        		System.out.println("whoops");
        		System.out.println(e);
        	}
        }
        BlackJackGUI.setPlayerHand(playerHandString);	
        BlackJackGUI.setHandSumLabel(Integer.toString(currentPlayer.card_sum));
    }


    public static void initialDealerHandLabel(Player Dealer){
        String playerHandString = "";
        for (int j =0; j< 1; j++) {
            try {
                playerHandString += Dealer.hand.get(j).value.name() + "-" + Dealer.hand.get(j).suite.toString() + "    ";
            }catch (Exception e) {
                System.out.println("whoops");
                System.out.println(e);
            }
        }
        BlackJackGUI.setDealerHand(playerHandString);
        BlackJackGUI.setDealerSum(Integer.toString(getCardValue(Dealer.hand.get(0).value)));
    }

    public static void setDealerHandLabel(Player Dealer) {

        String playerHandString = "";
        for (int j =0; j< Dealer.hand.size(); j++) {
        	try {
        		playerHandString += Dealer.hand.get(j).value.name() + "-" + Dealer.hand.get(j).suite.toString() + "    ";
        	}catch (Exception e) {
        		System.out.println("whoops");
        		System.out.println(e);
        	}
        }
        BlackJackGUI.setDealerHand(playerHandString);	
        BlackJackGUI.setDealerSum(Integer.toString(Dealer.card_sum));
    }

    public static void resetHand(ArrayList<Player> players) {
    	 for (int i = 0; i < players.size(); i++) {
         	players.get(i).hand = new ArrayList<Card>();
         }
    }

    public static int getCardValue(Value value){
        switch (value){
            case ACE:
                return 11;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
                return 10;
            case JACK:
                return 10;
            case QUEEN:
                return 10;
            case KING:
                return 10;
        }
        return 0;
        }
    }


