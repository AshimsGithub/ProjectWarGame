/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

public class WarPlayer extends Player {
    private ArrayList<PlayingCard> hand;

    public WarPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }

    public void receiveCard(PlayingCard card) {
        hand.add(card);
    }

    public PlayingCard playCard() {
        if (!hand.isEmpty()) {
            return hand.remove(0);
        }
        return null;
    }

    public int getHandSize() {
        return hand.size();
    }

    @Override
    public void play() {
        // Implement the play logic specific to the War game
    }
}
