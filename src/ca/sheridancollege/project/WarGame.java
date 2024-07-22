/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

public class WarGame extends Game {
    private Deck deck;

    public WarGame(String gameName) {
        super(gameName);
        deck = new Deck();
    }

    @Override
    public void play() {
        deck.shuffle();
        dealCards();
        while (!isGameOver()) {
            playRound();
        }
        declareWinner();
    }

    private void dealCards() {
        ArrayList<Player> players = getPlayers();
        int playerCount = players.size();
        while (!deck.showCards().isEmpty()) {
            for (Player player : players) {
                if (!deck.showCards().isEmpty()) {
                    ((WarPlayer) player).receiveCard(deck.dealCard());
                }
            }
        }
    }

    private boolean isGameOver() {
        for (Player player : getPlayers()) {
            if (((WarPlayer) player).getHandSize() == 0) {
                return true;
            }
        }
        return false;
    }

    private void playRound() {
        ArrayList<Player> players = getPlayers();
        ArrayList<PlayingCard> playedCards = new ArrayList<>();

        for (Player player : players) {
            playedCards.add(((WarPlayer) player).playCard());
        }

        int winnerIndex = determineRoundWinner(playedCards);
        WarPlayer winner = (WarPlayer) players.get(winnerIndex);
        for (PlayingCard card : playedCards) {
            winner.receiveCard(card);
        }
    }

    private int determineRoundWinner(ArrayList<PlayingCard> playedCards) {
        int winnerIndex = 0;
        PlayingCard winningCard = playedCards.get(0);

        for (int i = 1; i < playedCards.size(); i++) {
            PlayingCard currentCard = playedCards.get(i);
            if (beats(currentCard, winningCard)) {
                winnerIndex = i;
                winningCard = currentCard;
            }
        }

        return winnerIndex;
    }

    private boolean beats(PlayingCard card1, PlayingCard card2) {
        if (card1.getRank() == PlayingCard.Rank.ACE && card2.getRank() == PlayingCard.Rank.TWO) {
            return false; // Ace loses to 2
        }
        if (card1.getRank() == PlayingCard.Rank.TWO && card2.getRank() == PlayingCard.Rank.ACE) {
            return true; // 2 beats Ace
        }
        return card1.getRank().ordinal() > card2.getRank().ordinal();
    }

    @Override
    public void declareWinner() {
        WarPlayer winner = null;
        int maxCards = 0;

        for (Player player : getPlayers()) {
            int handSize = ((WarPlayer) player).getHandSize();
            if (handSize > maxCards) {
                winner = (WarPlayer) player;
                maxCards = handSize;
            }
        }

        System.out.println("The winner is " + winner.getPlayerID() + " with " + maxCards + " cards!");
    }
}
