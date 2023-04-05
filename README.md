# Card-Game

This is an implementation of a simple card game .This is a multiiplayer card game that supports multiple players between (2 to 4) and different types of cards (e.g. number cards, action cards, etc.).I have used java to implement this card game.

## Game Mechanics:

- Each player starts with a hand of 5 cards.

- The game starts with a deck of 52 cards (a standard deck of playing cards).

- Players take turns playing cards from their hand, following a set of rules that define what cards can be played when.

- A player can only play a card if it matches either the suit or the rank of the top card on the discard pile.

- If a player cannot play a card, they must draw a card from the draw pile. If the draw pile is empty, the game ends in a draw and no player is declared a winner.

- The game ends when one player runs out of cards and is declared the winner.

- Aces, Kings, Queens, and Jacks are action cards. When one of these is played, the following actions occur:

- Ace(A): Skip the next player in turn.

- King(K): Reverse the sequence of who plays next.

- Queen(Q): +2. The next player has to draw two cards.

- Jack(J): +4. The next player has to draw four cards.


## Implementation:

1. Create a deck of 52 cards.

2. Shuffle the deck randomly (e.g., using the Fisher-Yates shuffle algorithm).

3. Deal 5 cards to each player (e.g., by creating an arraylist for each player and removing 5 cards from the top of the deck for each array).

4. Place the top card of the deck on the discard pile.

5. Start a loop that continues until one player runs out of cards or the draw pile is empty.

6. Within the loop, take the current player's turn and perform the following steps:

7. Check if the player can play a card (i.e., if they have a card that matches either the suit or the rank of the top card on the discard pile).

8. If the player can play a card, allow them to choose a card from their hand and place it on the discard pile. If the card is an action card, perform the      corresponding action.

9. If the player cannot play a card, they must draw a card from the draw pile. If the draw pile is empty, end the loop.

10. Check if the player has run out of cards. If so, end the loop.

11. Switch to the next player and repeat.

12. Declare the winner as the player who has run out of cards.

## Unit Tests:

I have provide unit tests for my solution to ensure that it works correctly and meets the requirements of the game.

- Test that the deck contains 52 cards after it is created.

- Test that the deck is shuffled randomly.

- Test that each player can add or remove a card or not.

