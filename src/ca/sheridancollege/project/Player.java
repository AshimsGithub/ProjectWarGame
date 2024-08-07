/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * This class will also manage the player's cards and actions within the game.
 * @author dancye, 2018
 * @modifier Ashim Thapa, 2024
 */
public abstract class Player {
    private String playerID;
    private GroupOfCards hand;
    private GroupOfCards discardPile;

    public Player(String playerID) {
        this.playerID = playerID;
        this.hand = new GroupOfCards();
        this.discardPile = new GroupOfCards();
    }

    public String getPlayerID() {
        return playerID;
    }

    public GroupOfCards getHand() {
        return hand;
    }

    public GroupOfCards getDiscardPile() {
        return discardPile;
    }

    public void addToDiscardPile(Card card) {
        discardPile.addCard(card);
    }

    public void shuffleDiscardPile() {
        discardPile.shuffle();
    }

    public void transferDiscardPileToHand() {
        hand.getCards().addAll(discardPile.getCards());
        discardPile.clear();
    }

    public abstract Card playCard();
}
