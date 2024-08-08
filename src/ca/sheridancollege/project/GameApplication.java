
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// 
package ca.sheridancollege.project;

import java.util.Scanner;

public class GameApplication {
    private WarGame currentGame;
    private int numberOfPlayers;

    public void launch() {
        boolean continueGame = true;
        while (continueGame) {
            continueGame = displayMainMenu();
        }
    }

    public boolean displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the War Game!");
        System.out.println("1. Start Game");
        System.out.println("2. Quit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                do {
                    startGame();
                } while (displayPostGameMenu());
                return true; // Loop back to main menu for new game
            case 2:
                exit();
                return false;
            default:
                System.out.println("Invalid choice. Please enter 1 or 2.");
                return true;
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of players (2 or 4): ");
        numberOfPlayers = scanner.nextInt();

        if (numberOfPlayers != 2 && numberOfPlayers != 4) {
            System.out.println("Invalid number of players. Please enter 2 or 4.");
            return;
        }

        currentGame = new WarGame(numberOfPlayers);

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

    public boolean displayPostGameMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game Over!");
        System.out.println("1. Restart Game");
        System.out.println("2. Home");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return true; // Restart the game
            case 2:
                return false; // Return to home screen
            case 3:
                exit();
                return false;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                return true;
        }
    }

    public void exit() {
        System.out.println("Exiting the game...");
    }
}


