/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT, you might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 * @author dancye
 */
public class GroupOfCards 
{
    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size; // the size of the grouping
    
    public GroupOfCards(int givenSize) {
        size = givenSize;
        cards = new ArrayList<>(size);
    }
    
    /**
     * A method that will get the group of cards as an ArrayList
     * @return the group of cards.
     */
    public ArrayList<Card> showCards() {
        return cards;
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param givenSize the max size for the group of cards
     */
    public void setSize(int givenSize) {
        size = givenSize;
    }
    
    /**
     * Adds a card to the group
     * @param card the card to add
     */
    public void addCard(Card card) {
        if (cards.size() < size) {
            cards.add(card);
        }
    }
    
    /**
     * Removes and returns a card from the group
     * @return the card removed
     */
    public Card removeCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }
}
