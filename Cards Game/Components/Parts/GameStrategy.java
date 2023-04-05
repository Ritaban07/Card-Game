package Parts;
import java.util.ArrayList;
import java.util.Scanner;


/* This class contains the main game logic.
 */
public class GameStrategy {

    private static ArrayList<Card> deck;

    private static ArrayList<Player> players;

    private static ArrayList<Card> drawPile;

    private static ArrayList<Card> discardPile;


    public static void main(String[] args) throws Exception{
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of players between 2 to 4");
            int numOfPlayers = sc.nextInt();

            if (numOfPlayers < 2 || numOfPlayers > 4) {      // Base condition for players
                throw new Exception("Invalid number of players ... Retry again and select a number between 2 to 4....");
            }

            //  Creating and shuffling the deck simultaneously

            deck = new Deck().getDeck();

            players = new ArrayList<>();
            for (int i = 1; i <= numOfPlayers; i++) {
                Player p = new Player(i);

                // adding 5 cards in each player hand
                for (int j = 1; j <= 5; j++) {
                    p.addCard(deck.get(deck.size() - 1));
                    deck.remove(deck.size() - 1);
                }
                players.add(p);

            }

            /* at the first turn the first card from the deck will act as
             the top card of discard pile.
             */

            discardPile = new ArrayList<>();
            discardPile.add(deck.get(0));
            deck.remove(0);

            /* creating a draw pile from the deck.*/
             
            drawPile = new ArrayList<>();
            for (Card c : deck) {
                drawPile.add(c);
            }

            // game starts from here

            int playerTurn = 0;
            int direction = 1;
            /*
                During player's turn cards to be taken by the particular
                player from the draw pile.
             */

            int numOfCardsTaken = 1;

            while (true) {
                /*  when the draw pile will be empty game will be end and 
                    game status will be a draw. 
                 */
                 
                if (drawPile.size() < numOfCardsTaken) {
                    System.out.println("Game drawn !! ... We are out of cards ... !!!");
                    break;
                }

                playerTurn %= numOfPlayers;            // To maintain players in a selected range.
                if (playerTurn < 0) playerTurn += numOfPlayers; // if playerturn goes to negative integers.
                playerTurn %= numOfPlayers;

                /* current player will have atleast 1 card. */ 

                
                boolean matched = false;       /*To check cards are matched or not.*/
                int matchedNum = -1;

                /* top card of the discard pile with whom the player will try to match his cards. */ 
                Card topDiscardCard = discardPile.get(discardPile.size() - 1); 
                System.out.println("Discard deck top card is : " + discardPile.get(discardPile.size() - 1));

                /* matching mechanism of Set of Cards of
                   Each player during his turn.
                 */
                for (Card currentPlayerCard : players.get(playerTurn).giveCards()) {
                    /* Matching condition for each turn
                       player will try to match each of his card
                       with the discard pile top card if the cards have
                       same numbers or same suits.
                     */

                    if (currentPlayerCard.getNumber() == topDiscardCard.getNumber() || currentPlayerCard.getSuit() == topDiscardCard.getSuit()) {

                        /* checking if action card on discardPile top ,
                         so that they are not stackable
                         1->ACE , 11->JACK , 12->QUEEN , 13->KING.
                        */ 

                        if (topDiscardCard.getNumber() == 1 || topDiscardCard.getNumber() == 11 || topDiscardCard.getNumber() == 12 || topDiscardCard.getNumber() == 13) {
                            if (currentPlayerCard.getNumber() == topDiscardCard.getNumber()) // player cannot play same action card even if available so he will skip.
                                continue;
                        }
                        /* if cards get matched then this statement will be printed. */ 
                        System.out.println("Cards matched for player " + players.get(playerTurn).giveId() + " Card and new Discard Deck top card = " + currentPlayerCard);

                        /*  to check if the current player has to
                            take more than one card or not,
                            depending upon the action card which the previous player 
                            may have played.
                         */
                        if(numOfCardsTaken > 1){
                            while (numOfCardsTaken > 0) {
                                numOfCardsTaken--;
                                System.out.println("Drawing " + drawPile.get(drawPile.size() - 1) + " Card");
                                /* adding the card to player's hand and removing it
                                   from the discard pile.
                                 */
                                players.get(playerTurn).addCard(drawPile.get(drawPile.size() - 1));
                                drawPile.remove(drawPile.size() - 1);
                            }
                            numOfCardsTaken = 1;
                        }

                        /*
                           if the player matches his card then
                           he removes the matched card from his
                           hand and place it to the discard pile.
                         */
                        players.get(playerTurn).removeCard(currentPlayerCard);
                        discardPile.add(currentPlayerCard);
                        matched = true;
                        matchedNum = currentPlayerCard.getNumber();
                        break;
                    }
                }

                /* if not matched then the player have to take a card from
                   the draw pile and keep it with him.
                 */

                if (matched == false) {
                    System.out.println("No cards match for player " + players.get(playerTurn).giveId() + " Taking " + numOfCardsTaken + " Card(s)");
                    /* number of cards to be taken by the current player
                       from the draw pile, depending upon the previous player's
                       turn.
                     */
                    while (numOfCardsTaken > 0) {
                        numOfCardsTaken--;
                        System.out.println("Drawing " + drawPile.get(drawPile.size() - 1) + " Card");
                        players.get(playerTurn).addCard(drawPile.get(drawPile.size() - 1));
                        drawPile.remove(drawPile.size() - 1);
                    }
                    numOfCardsTaken = 1;
                }


                /* if player has matched and is left with no cards
                   then, he has won the match.
                 */
                if (matched == true && players.get(playerTurn).giveCards().size() == 0) {

                    System.out.println("Hurray!!! Congratulations player " + players.get(playerTurn).giveId() + " won the match :)");

                    System.exit(0);
                }

                /* BONUS -> Action cards (Importent implementations) */

                  /* if the player has played the ACE card (Action card)
                   then the next player's turn will be skipped.
                 */

                if (matched == true && matchedNum == 1) {
                    playerTurn += direction;
                }
                  /* if the player has played the KING card (Action card)
                   then the direction of the flow of the game would be reversed.
                 */
                if (matched == true && matchedNum == 13) {
                    direction *= -1;
                }

                /* if the player has played the JACK card (Action card)
                   then the next player have to take 4 cards from the
                   draw pile.
                 */

                if (matched == true && matchedNum == 11) {
                    numOfCardsTaken = 4;
                }
                 /* if the player has played the QUEEN card (Action card)
                   then the next player have to take 2 cards from the
                   draw pile.
                 */
                if (matched == true && matchedNum == 12) {
                    numOfCardsTaken = 2;
                }
                /* to determine the next player whose gonna play. */ 
                playerTurn += direction;

                System.out.println("=====================================================");
            }
        }



    }




}