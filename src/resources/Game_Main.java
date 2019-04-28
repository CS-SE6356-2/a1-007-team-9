package resources;



import javax.swing.*;
import java.util.*;

public class Game_Main {
    static Scanner input = new Scanner(System.in);
    private static Player currentPlayer;
    public static Deck deck;
    protected static GameWindow BlackJackGUI;
    
    public static void main(String[] args) {

//        GUIF BlackJackGUI = new GUIF();
//        BlackJackGUI.createGui();
    	BlackJackGUI = new GameWindow();
    	BlackJackGUI.frmBlackjack.setVisible(true);


        deck = new Deck();
        ArrayList<Player> players = new ArrayList<Player>();
        System.out.println("Welcome to BlackJack");
        BlackJackGUI.setOutput("Welcome to BlackJack");
        System.out.println();


//        System.out.print("How many deck would you like to play with(1-8): ");
        int deckNum = 4;
        //Getting the number of decks that the player wished to play with
//        while(true){
//            String userInput = input.next(); //TODO change console input to gui input
//            if(IsNumber(userInput)){
//                deckNum = Integer.parseInt(userInput);
//                if(0 < deckNum && deckNum < 9){
//                    deckNum--;
//                    break;
//                }
//            }
//            System.out.print("Invalid input Please enter a valid number between 1 and 8.");
//        }
        //creating the additional decks
        for (int i = 0; i < deckNum; i++){
            deck.AddDeck(new Deck());
        }


        //Getting number of players that are playing max 4
//        System.out.print("How many players would you like to play with(1-4): ");
        int playerCounter = 4;
//        while(true){
//            String userInput = input.next();//TODO change console input to gui input
//            if(IsNumber(userInput)){
//                playerCounter = Integer.parseInt(userInput);
//                if(0 < playerCounter && playerCounter < 9){
//                    break;
//                }
//            }
//            System.out.print("Invalid input Please enter a valid number between 1 and 4.");
//        }

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
//        System.out.print("How many round do you wish to play(1-10) ");

        int rounds = 0;
        while(true){
            String roundamt = JOptionPane.showInputDialog("How many round do you wish to play(1-10) ");
            System.out.println(roundamt);
            if(IsNumber(roundamt)){
                rounds = Integer.parseInt(roundamt);
                if(0 < rounds && rounds < 9){
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

    }

    //---------------------------------------------------------------------------------------------------------------------------

    static void PlayRound(Deck deck, ArrayList<Player> players){
        Player dealer = new Player(0);


        makeBet(players);
        DealInitialHands(players, deck);
        PrintPlayers(players);


        dealer.DrawInitialHand(deck);

        System.out.println("The dealer is currently showing a " + dealer.hand.get(0).value + " " + dealer.hand.get(0).suite);
        BlackJackGUI.setDealerHand(dealer.hand.get(0).value + " " + dealer.hand.get(0).suite);

        if (dealer.hand.get(0).value == Value.ACE){
                //insurance(players, dealer);
            }

        CheckForBlackJack(players,dealer);

        for (int i = 0; i < players.size(); i++) {
            while(players.get(i).round_state){
                currentPlayer = players.get(i);
                System.out.println("Player " + (i+1) + " what would you like to do");
                System.out.println("Your current hand is worth: " + players.get(i).card_sum);
                BlackJackGUI.setHandSumLabel(Integer.toString(players.get(i).card_sum));
                BlackJackGUI.setPlayerHand(players.get(i).hand.get(0).value.name()+ " "+ players.get(i).hand.get(0).suite.toString()); //setting the gui player hand label
                
                //print player hand
                setPlayerHandLabel(players.get(i));
                
                int move = 0;
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
                        int p = Integer.parseInt(playerMove);
                        if(0 < p & p < 5){
                            if(p == 3 && players.get(i).hand.size() != 2){
                                System.out.println("Can't surrender Please enter a valid number between 1 and 4");
                                continue;
                            } else if(p == 4 && (players.get(i).hand.size() != 2 || !(players.get(i).money >= players.get(i).bet))){
                                System.out.println("Can't double Please enter a valid number between 1 and 4");
                                continue;
                            }
                                move = p;
                            break;
                        }
                    }
                    System.out.println("Please enter a valid number between 1 and 4");
                }//while loop to get player input

                switch(move){
                    case 1://Hit
                        hit(players.get(i),deck);
                        break;
                    case 2: //Stand
                        stand(players.get(i),deck);
                        break;
                    case 3://Surrender
                        surrender(players.get(i),deck);
                        break;
                    case 4: // Double
                        doublebet(players.get(i),deck);
                        break;
                }
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

                players.get(i).resetPlayer();

                //player has blackjack
            }else if(players.get(i).card_sum  == 21 && players.get(i).card_sum != dealer_sum){
                System.out.println("Player " + (i+1) + " has won with a BlackJack");
                System.out.println();

                players.get(i).money += players.get(i).bet + (players.get(i).bet * 1.5);
                players.get(i).resetPlayer();

                //player wins gets even money
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

            }
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

    public static void CheckForBlackJack(ArrayList<Player> players, Player dealer){//checkings a change
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

    //---------------------------------------------------------------------------------------------------------------------

    //different move the play can take
    public static void hit(Player current, Deck deck) {
        current.move = Possible_Moves.HIT;
        current.Play(deck);
        setPlayerHandLabel(current);
    }

    public static void stand(Player current, Deck deck){
        current.move = Possible_Moves.STAND;
        current.Play(deck);
        setPlayerHandLabel(current);
    }
    public static void surrender(Player current, Deck deck) {
        current.move = Possible_Moves.SURRENDER;
        current.Play(deck);
        setPlayerHandLabel(current);
    }
    public static void doublebet(Player current, Deck deck) {
        current.move = Possible_Moves.DOUBLE;
        current.Play(deck);
        setPlayerHandLabel(current);
    }

    //------------------------------------------------------------------------------------------------------------------

    //getting first bets from the players
    public static void makeBet(ArrayList<Player>players){
        //Getting initial bets from all non dealer player
        for (int i = 0; i < players.size(); i++){
            System.out.print("Player " + (i+1) + " please enter you bet for this round: ");
            while(true){
                String betamt = JOptionPane.showInputDialog("Player " + (i+1) + " please enter a valid bet amount between 1 and " + players.get(i).money);
                System.out.println(betamt);
                if(IsNumber(betamt)){
                    int temp = Integer.parseInt(betamt);
                    if(0 < temp && temp <= players.get(i).money){
                        players.get(i).MakeBet(temp);
                        break;
                    }
                }
                System.out.print("Please enter a valid number between 1 and " + players.get(i).money + ": ");
            }
        }// end for loop
    }

    //------------------------------------------------------------------------------------------------------------------

    public static void insurance(ArrayList<Player> players, Player dealer){
        System.out.println("Dealer is currently showing a Ace.");
        for(int i = 0; i < players.size(); i++){
            System.out.println("Player " + i+1 + " would you like to make a insurance bet: Y or N");
            String userinput;
            while(true) {
                userinput = JOptionPane.showInputDialog("would you like to make a insurance bet: Y or N");
                if(userinput == "Y" || userinput == "N"){
                    break;
                }
                System.out.println("Please enter Y or N");
            }//getting user input

            if(userinput == "Y"){
                String insamt = JOptionPane.showInputDialog("enter bet amount");
                System.out.println(insamt);
                int insurance_bet = Integer.parseInt(insamt);


            }

        }//player loop
    }//insurance

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    
    public static void setPlayerHandLabel(Player currentPlayer) {

        String playerHandString = "";
        for (int j =0; j< currentPlayer.hand.size(); j++) {
        	playerHandString += currentPlayer.hand.get(j).value.name() + "-" + currentPlayer.hand.get(j).suite.toString() + "    ";
        }
        BlackJackGUI.setPlayerHand(playerHandString);	
    }

}
