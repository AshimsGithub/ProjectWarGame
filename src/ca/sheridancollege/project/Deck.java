/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

public class Deck extends GroupOfCards {
    
    public Deck() {
        super(52);
        initializeDeck();
        shuffle();
    }
    
    private void initializeDeck() {
        for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
            for (PlayingCard.Rank rank : PlayingCard.Rank.values()) {
                addCard(new PlayingCard(suit, rank));
            }
        }
    }

    public PlayingCard dealCard() {
        return (PlayingCard) removeCard();
    }
}
