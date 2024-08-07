/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.List;

public class WarGame {
    private GroupOfCards deck;
    private GroupOfCards playArea;
    private List<Player> players;

    public WarGame(int numPlayers) {
        this.deck = new GroupOfCards();
        this.playArea = new GroupOfCards();
        this.players = new ArrayList<>(numPlayers);
        initializeDeck();
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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void dealCards() {
        int numPlayers = players.size();
        int cardsPerPlayer = deck.size() / numPlayers;
        for (Player player : players) {
            for (int i = 0; i < cardsPerPlayer; i++) {
                player.getHand().addCard(deck.removeCard());
            }
        }
    }

    public void play() {
        initializeGame();
        while (!isGameOver()) {
            playRound();
        }
        declareWinner();
    }

    private void initializeGame() {
        dealCards();
    }

    private boolean isGameOver() {
        return players.stream().anyMatch(p -> p.getHand().size() + p.getDiscardPile().size() == 52);
    }

    private void playRound() {
        playArea.clear();

        Card[] cardsPlayed = new Card[players.size()];
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getHand().size() == 0) {
                player.transferDiscardPileToHand();
            }

            if (player.getHand().size() > 0) {
                cardsPlayed[i] = player.playCard();
                playArea.addCard(cardsPlayed[i]);
                System.out.println(player.getPlayerID() + " played: " + cardsPlayed[i]);
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
                    roundWinner = players.get(i);
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

    private void declareWinner() {
        for (Player player : players) {
            if (player.getHand().size() + player.getDiscardPile().size() == 52) {
                System.out.println(player.getPlayerID() + " wins the game!");
                return;
            }
        }
        System.out.println("No winner, the game ended in a draw.");
    }
}
