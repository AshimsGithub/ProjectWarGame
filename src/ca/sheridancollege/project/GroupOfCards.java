/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    private ArrayList<Card> cards;

    public GroupOfCards(int size) {
        this.cards = new ArrayList<>(size);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card removeCard() {
        if (!cards.isEmpty()) {
            return cards.remove(cards.size() - 1);
        }
        return null;
    }

    public Card getTopCard() {
        if (!cards.isEmpty()) {
            return cards.get(cards.size() - 1);
        }
        return null;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void clear() {
        cards.clear();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }
}
