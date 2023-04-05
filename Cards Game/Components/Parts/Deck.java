package Parts;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        
        deck = new ArrayList<>();             // creating the deck.
        for(Suits suits : Suits.values()){

            for(int i = 1; i <= 13; i++){      
                deck.add(new Card(i,suits));
            }

        }
        
        suffel(deck,deck.size());            // shuffling the deck 

    }

    public void suffel( ArrayList<Card> deck, int n){
        // Creating a object for Random class
        Random r = new Random();
          
        //  Start from the last element and swap one by one. We don't
        //  need to run for the first element that's why i > 0
        for (int i = n-1; i > 0; i--) {
              
            // Pick a random index from 0 to i
            int j = r.nextInt(i);
              
            // Swap arr[i] with the element at random index
            Card temp = deck.get(i);
            deck.set(i,deck.get(j));
            deck.set(j,temp);
        }
    }

    public ArrayList<Card> getDeck() {       // For getting the ArrayList of Deck.
        return deck;
    }

}