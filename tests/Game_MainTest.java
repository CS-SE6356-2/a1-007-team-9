import org.junit.Test;
import resources.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Game_MainTest {

    @Test
    public void dealInitialHandsTest() {

        Deck TestDeck = new Deck();

        Player TestPlayer1 = new Player(1);
        Player TestPlayer2 = new Player(2);

        ArrayList<Player> TestPlayers = new ArrayList<Player>();
        TestPlayers.add(TestPlayer1);
        TestPlayers.add(TestPlayer2);

      //  Game_Main.hit(TestPlayers, TestDeck); todo


        assertEquals(2, TestPlayer1.hand.size());
        assertEquals(2, TestPlayer2.hand.size());
    }

   @Test
    public void getPlayerMoneyTest() {
        Player testPlayer = new Player(5);

       assertEquals(1000, testPlayer.money);
   }

   @Test
    public void getCardTest() {
       Card testCard = new Card(Value.ACE, Suite.HEART);

       assertEquals("HEART", testCard.suite.toString());
       assertEquals("ACE",testCard.value.toString());
   }

   @Test
    public void blackJackTest() {
        Player testPlayer = new Player(1); //todo
   }

   @Test
    public void testDealer() {

   }
}