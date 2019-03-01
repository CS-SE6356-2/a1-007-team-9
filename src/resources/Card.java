package resources;

public class Card {

    public Value_Types value;
    public Suite_Types suite;

    //---------------------------------------------------------------------------------------------------------------------------

    public Card(Value_Types n, Suite_Types m){
        value = n;
        suite = m;
    }//constructor that take value and suite and creates a card from the input
}//Card

//---------------------------------------------------------------------------------------------------------------------------

