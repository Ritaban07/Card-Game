
package Parts;

public class Card {

    private int number;
    private Suits suit;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }
    
    public Suits getSuit() {
        return suit;
    }

    public Card(int num, Suits s) {
        number = num;
        suit = s;
    }
    public String toString() {
        return number + " " + suit;
    }

}