package resources;


import java.util.*;

public class Deck {
    public ArrayList<Card> cards;

    //---------------------------------------------------------------------------------------------------------------------------


    public Deck(){
        cards = new ArrayList<Card>();
        for(Suite currentSuite : Suite.values()) {
            for(Value currnetValue : Value.values()) {
                cards.add(new Card(currnetValue, currentSuite));
            }
        }
        Shuffle();
    }//default constructor that creates a unshuffled deck of 52 basic playing cards

    //---------------------------------------------------------------------------------------------------------------------------

    public void Shuffle(){
        ArrayList<Card> temp = new ArrayList<Card>();
        Random rand = new Random();
        while(!cards.isEmpty()){
            int random = rand.nextInt(cards.size());
            temp.add(cards.get(random));
            cards.remove(random);
        }
        cards = temp;

    }//shuffle

    //---------------------------------------------------------------------------------------------------------------------------

    public Card Draw(){
    	try {
    		Card drawn = this.cards.get(this.cards.size()-1);
	        this.cards.remove(drawn);
	        return drawn;
    	}catch (Exception e) {
    		Deck AddDeck = new Deck();
    		this.AddDeck(AddDeck);
    		Card drawn = this.cards.get(this.cards.size()-1);
	        this.cards.remove(drawn);
	        return drawn;
    	}
       
    }

    //---------------------------------------------------------------------------------------------------------------------------
        //allows the user to play with any number of decks
    public void AddDeck(Deck additional){
        for (int i = 0; i < additional.cards.size(); i++){
            this.cards.add(additional.cards.get(i));
        }
        Shuffle();
    }

    //---------------------------------------------------------------------------------------------------------------------------

    public void PrintDeck(){
        for (int i = 0; i < this.cards.size(); i++) {
            System.out.println(this.cards.get(i).value + " " + this.cards.get(i).suite);
        }
    }
}
