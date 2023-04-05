package Tests;

import Parts.Card;
import Parts.Deck;
import Parts.Suits;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;


public class DeckTest {

    @Test
    public void checkShuffling(){
        // unshuffled deck
        ArrayList<Card> unShuffled = new ArrayList<>();

        for(Suits suits : Suits.values()){

            for(int i = 1; i <= 13; i++){
                unShuffled.add(new Card(i,suits));
            }

        }

        Deck deck = new Deck();
        ArrayList<Card> shuffled = deck.getDeck();
        // to check if the new deck created is shuffled or not.
        Assert.assertEquals(false,unShuffled.toString() == shuffled.toString());


    }
    @Test
    public void testDeckContains52Cards() {
        Deck deck = new Deck();
        ArrayList<Card> cards=deck.getDeck();
        Assert.assertEquals(52, cards.size());       // To check no of cards are 52 or not.
    }



}
