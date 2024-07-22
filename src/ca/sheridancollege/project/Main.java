/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        WarGame game = new WarGame("War Card Game");
        ArrayList<Player> players = new ArrayList<>();
        players.add(new WarPlayer("Player 1"));
        players.add(new WarPlayer("Player 2"));

        game.setPlayers(players);
        game.play();
    }
}

