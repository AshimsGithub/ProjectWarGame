/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

public class GameApplication {
    private WarGame currentGame;
    private int numberOfPlayers;

    public void launch() {
        displayMainMenu();
    }

    public void displayMainMenu() {
        // Example of how you might display the main menu
        System.out.println("Welcome to the War Game!");
        // Code to handle user input and start a game
        startGame();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players: ");
        numberOfPlayers = scanner.nextInt();

        currentGame = new WarGame("War", numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Enter name for player " + (i + 1) + ": ");
            String name = scanner.next();
            if (i % 2 == 0) {
                currentGame.addPlayer(new HumanPlayer(name));
            } else {
                currentGame.addPlayer(new ComputerPlayer(name));
            }
        }

        currentGame.play();
    }

    public void exit() {
        System.out.println("Exiting the game...");
    }
}
