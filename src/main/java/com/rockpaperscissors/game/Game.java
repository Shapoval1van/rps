package com.rockpaperscissors.game;

import com.rockpaperscissors.Move;
import com.rockpaperscissors.Player;

import java.util.*;

public class Game {

    protected List<Player> players;
    protected Stack<Player> losers;
    protected Player winner;
    private final int MIN_PLAYERS_FOR_GAME = 2;
    private final int MAX_COUNT_OF_REPLAY = 10;

    public Game() {
        players = new ArrayList<>();
        losers = new Stack<>();
    }

    public Player doGame() {
        System.out.println("=========New Game========");
        if (players.size() < MIN_PLAYERS_FOR_GAME) {
            throw new RuntimeException("Not enough players for the game");
        }
        do {
            System.out.println("=========New Round========");
            Player loser = playRound();
            players.remove(loser);
            losers.add(loser);
        } while (isGameFinished()); //game will finish if will remain only one player
        winner = players.stream().findFirst().get();
        winner.addRang();
        return winner;
    }

    /**
     * call {@link #cleanUp()} after game to clean previous result
     */
    public void cleanUp() {
        players.clear();
        losers.clear();
    }

    public void addPlayers(List<Player> player) {
        players.addAll(player);
    }

    public void present() { //redefine to make another type of present
        Stack<Player> rating = (Stack<Player>) losers.clone();
        System.out.println(String.format("|%10S|%10S|", "Place", "Alias"));
        System.out.println(String.format("|%10S|%10S|", "1", winner.getAlias()));
        int placeCounter = 2;
        while (!rating.isEmpty()) {
            System.out.println(String.format("|%10S|%10S|", placeCounter, rating.pop().getAlias()));
            placeCounter++;
        }
    }

    private Player playRound() {
        List<Player> loser = new ArrayList<>();
        //we need to check case when players will always do draw
        for (int i = 0; i < MAX_COUNT_OF_REPLAY && loser.isEmpty(); i++) {
            compute(players, loser, 0);
        }
        return loser.get(0);
    }

    /**
     * In which round we want to remove one loser if we has several losers we do {@link #compute(List, List, int)} again
     *
     * @param players
     * @return loser
     */
    private void compute(List<Player> players, List<Player> loser, int countOfReplay) {
        List<Player> losers;
        if (!loser.isEmpty()) {
            return;
        }
        if (countOfReplay > MAX_COUNT_OF_REPLAY) {
            throw new RuntimeException("We cant find winner... Draw!!!!! Friendship won!!!!!!");
        }
        System.out.println("-------------------");
        Map<Move, List<Player>> playersMove = collectPlayersMove(players);
        boolean isRockPresent = playersMove.keySet().contains(Move.ROCK);
        boolean isPaperPresent = playersMove.keySet().contains(Move.PAPER);
        boolean isScissorsPresent = playersMove.keySet().contains(Move.SCISSORS);
        if (!isPaperPresent && isRockPresent && isScissorsPresent) {
            losers = playersMove.get(Move.SCISSORS);
        } else if (!isRockPresent && isScissorsPresent && isPaperPresent) {
            losers = playersMove.get(Move.PAPER);
        } else if (!isScissorsPresent && isPaperPresent && isRockPresent) {
            losers = playersMove.get(Move.ROCK);
        } else {
            losers = players;
        }
        if (losers.size() == 1) {
            loser.add(losers.get(0));
        }
        countOfReplay++;
        compute(losers, loser, countOfReplay);
    }

    private Map<Move, List<Player>> collectPlayersMove(List<Player> players) {
        Map<Move, List<Player>> playersMove = new HashMap<>();
        players.forEach(player -> {
            Move move = player.doMove();
            if (playersMove.keySet().contains(move)) {
                playersMove.get(move).add(player);
            } else {
                playersMove.put(move, new ArrayList<>(Arrays.asList(player)));
            }
        });
        return playersMove;
    }

    private boolean isGameFinished() {
        return players.size() - losers.size() == 1;
    }
}
