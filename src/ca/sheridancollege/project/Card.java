/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the code 
 * should remember to add themselves as a modifier.
 * @author dancye, 2018
 * @modifier Jonathan Penava, 2024
 */
public class Card {
    private final String suit; // The suit of the card (e.g., Hearts, Diamonds)
    private final String rank; // The rank of the card (e.g., 2, 3, Jack, Queen)

    /**
     * Constructor to create a card with a specific suit and rank.
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * @return the rank of the card
     */
    public String getRank() {
        return rank;
    }

    /**
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
