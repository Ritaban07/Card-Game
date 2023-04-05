package Tests;

import org.junit.Test;
import Parts.Card;
import Parts.Player;
import Parts.Suits;
import org.junit.Assert;

public class PlayerTest {

    /* testing for checking if cards are added to player's
       hand or not.
     */

    @Test
    public void addingPlayerCard(){      // test for adding a card to player

        Card card = new Card(1,Suits.HEARTS);
        Player player = new Player(0);
        player.addCard(card);
        int ans = player.giveCards().size();
        Assert.assertEquals(1,ans);


    }

    @Test
    public void removingPlayerCard(){     // test for removing a card from player

        Card card = new Card(1,Suits.HEARTS);
        Player player = new Player(0);
        player.addCard(card);
        player.removeCard(card);
        int ans = player.giveCards().size();
        Assert.assertEquals(0,ans);


    }


}
