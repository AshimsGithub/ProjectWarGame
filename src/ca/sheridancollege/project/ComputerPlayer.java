/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

public class ComputerPlayer extends Player {

    public ComputerPlayer(String playerID) {
        super(playerID);
    }

    @Override
    public Card playCard() {
        // Play the top card from the hand
        return getHand().removeCard();
    }
}



