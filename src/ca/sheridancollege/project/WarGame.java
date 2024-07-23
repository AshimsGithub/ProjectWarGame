/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public class WarGame extends Game {
    private GroupOfCards deck;
    private GroupOfCards playArea;

    public WarGame(String gameName, int numPlayers) {
        super(gameName);
        this.deck = new GroupOfCards(52);
        this.playArea = new GroupOfCards(0);
        initializeDeck();
        setPlayers(new ArrayList<>(numPlayers));
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.addCard(new Card(suit, rank));
            }
        }
        deck.shuffle();
    }

    @Override
    public void play() {
        initializeGame();
        while (!isGameOver()) {
            playRound();
        }
        declareWinner();
    }

    public void initializeGame() {
        dealCards();
    }

    protected boolean isGameOver() {
        // Check if game is over
        return deck.size() == 0 && getPlayers().stream().allMatch(p -> p.getHand().size() == 0);
    }

    protected void playRound() {
        System.out.println("Playing a round...");
        playArea.clear();

        List<Player> players = getPlayers();
        Card[] cardsPlayed = new Card[players.size()];
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getHand().size() > 0) {
                Card card = player.getHand().removeCard();
                cardsPlayed[i] = card;
                playArea.addCard(card);
                System.out.println(player.getPlayerID() + " played: " + card);
            } else {
                cardsPlayed[i] = null;
            }
        }
        compareCards(cardsPlayed);
    }

    private void compareCards(Card[] cardsPlayed) {
        Card winningCard = null;
        Player roundWinner = null;
        for (int i = 0; i < cardsPlayed.length; i++) {
            if (cardsPlayed[i] != null) {
                if (winningCard == null || compareCardRanks(cardsPlayed[i], winningCard) > 0) {
                    winningCard = cardsPlayed[i];
                    roundWinner = getPlayers().get(i);
                }
            }
        }

        if (roundWinner != null) {
            System.out.println(roundWinner.getPlayerID() + " wins the round with " + winningCard);
            for (Card card : playArea.getCards()) {
                roundWinner.addToDiscardPile(card);
            }
        } else {
            System.out.println("No winner for this round.");
        }
    }

    private int compareCardRanks(Card card1, Card card2) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int rank1 = java.util.Arrays.asList(ranks).indexOf(card1.getRank());
        int rank2 = java.util.Arrays.asList(ranks).indexOf(card2.getRank());
        return Integer.compare(rank1, rank2);
    }

    @Override
    public void declareWinner() {
        // Implement logic to declare the winner
        System.out.println("Game Over! Declaring the winner...");
    }

    public void dealCards() {
        int numPlayers = getPlayers().size();
        int cardsPerPlayer = deck.size() / numPlayers;
        for (Player player : getPlayers()) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                player.getHand().addCard(deck.removeCard());
            }
        }
    }

    public void shuffleDiscardPile(Player player) {
        player.shuffleDiscardPile();
    }

    public void endGame() {
        System.out.println("Ending the game...");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayScores() {
        System.out.println("Displaying scores...");
        for (Player player : getPlayers()) {
            System.out.println(player.getPlayerID() + ": " + player.getScore());
        }
    }

    public void addPlayer(Player player) {
        getPlayers().add(player);
    }
}
