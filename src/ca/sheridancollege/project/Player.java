/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

public abstract class Player {
    private String playerID;
    private GroupOfCards hand;
    private GroupOfCards discardPile;
    private int score;

    public Player(String name) {
        this.playerID = name;
        this.hand = new GroupOfCards(0);
        this.discardPile = new GroupOfCards(0);
        this.score = 0;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public GroupOfCards getHand() {
        return hand;
    }

    public GroupOfCards getDiscardPile() {
        return discardPile;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public abstract void play();

    public void drawCard(Card card) {
        hand.addCard(card);
    }

    public void addToDiscardPile(Card card) {
        discardPile.addCard(card);
    }

    public void shuffleDiscardPile() {
        discardPile.shuffle();
    }
}
