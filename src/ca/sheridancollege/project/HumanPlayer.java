/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerID) {
        super(playerID);
    }

    @Override
    public Card playCard() {
        return getHand().removeCard();
    }
}


