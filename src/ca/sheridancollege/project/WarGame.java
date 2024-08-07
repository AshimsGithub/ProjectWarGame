/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Iterator;
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
            removePlayersWithoutCards();
        }
        declareWinner();
    }

    private void initializeGame() {
        dealCards();
    }

    private boolean isGameOver() {
        // The game is over when only one player is left
        return players.size() == 1;
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

        resolveRound(cardsPlayed);
    }

    private void resolveRound(Card[] cardsPlayed) {
        List<Integer> winningIndices = new ArrayList<>();
        Card winningCard = null;
        for (int i = 0; i < cardsPlayed.length; i++) {
            if (cardsPlayed[i] != null) {
                if (winningCard == null || compareCardRanks(cardsPlayed[i], winningCard) > 0) {
                    winningCard = cardsPlayed[i];
                    winningIndices.clear();
                    winningIndices.add(i);
                } else if (compareCardRanks(cardsPlayed[i], winningCard) == 0) {
                    winningIndices.add(i);
                }
            }
        }

        if (winningIndices.size() == 1) {
            // Clear winner
            Player roundWinner = players.get(winningIndices.get(0));
            System.out.println(roundWinner.getPlayerID() + " wins the round with " + winningCard);
            for (Card card : playArea.getCards()) {
                roundWinner.addToDiscardPile(card);
            }
        } else {
            // Tie detected
            System.out.println("Tie detected between players.");
            initiateTieBreaker(winningIndices);
        }
    }

    private void initiateTieBreaker(List<Integer> tiedPlayersIndices) {
        System.out.println("Initiating tie-breaker...");
        List<Card> tieBreakerCards = new ArrayList<>();

        for (int index : tiedPlayersIndices) {
            Player player = players.get(index);
            if (player.getHand().size() == 0) {
                player.transferDiscardPileToHand();
            }

            if (player.getHand().size() > 0) {
                Card tieBreakerCard = player.playCard();
                tieBreakerCards.add(tieBreakerCard);
                playArea.addCard(tieBreakerCard);
                System.out.println(player.getPlayerID() + " plays tie-breaker card: " + tieBreakerCard);
            }
        }

        resolveRound(tieBreakerCards.toArray(new Card[0]));
    }

    private int compareCardRanks(Card card1, Card card2) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        int rank1 = java.util.Arrays.asList(ranks).indexOf(card1.getRank());
        int rank2 = java.util.Arrays.asList(ranks).indexOf(card2.getRank());
        return Integer.compare(rank1, rank2);
    }

    private void removePlayersWithoutCards() {
        Iterator<Player> iterator = players.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getHand().size() == 0 && player.getDiscardPile().size() == 0) {
                System.out.println(player.getPlayerID() + " has been eliminated.");
                iterator.remove();
            }
        }
    }

    private void declareWinner() {
        if (players.size() == 1) {
            System.out.println(players.get(0).getPlayerID() + " wins the game!");
        } else {
            System.out.println("The game ended in a draw.");
        }
    }
}

