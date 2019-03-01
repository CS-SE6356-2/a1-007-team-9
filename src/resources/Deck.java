package resources;

import resources.Card;
import resources.Suite_Types;
import resources.Value_Types;

import java.util.ArrayList;
import java.util.*;

public class Deck {
    public ArrayList<Card> cards;

    //---------------------------------------------------------------------------------------------------------------------------


    public Deck(){
        cards = new ArrayList<Card>();
        for(Suite_Types currentSuite : Suite_Types.values()) {
            for(Value_Types currnetValue : Value_Types.values()) {
                cards.add(new Card(currnetValue, currentSuite));
            }
        }
    }//default constructor that creates a unshuffled deck of 52 basic playing cards

    //----------------------------------------------------------------------------------------------------------------------

    public void shuffle(){
        ArrayList<Card> temp = new ArrayList<Card>();
        Random rand = new Random();
        while(!cards.isEmpty()){
            int random = rand.nextInt(cards.size());
            temp.add(cards.get(random));
            cards.remove(random);
        }
        cards = temp;

    }

}
