package com.rockpaperscissors.strategy;

import com.rockpaperscissors.Move;

import java.util.Random;

public class RandomMoveStrategy implements MoveStrategy {
    public Move getMove() {
        Move[] moves = Move.values();
        Random random = new Random();
        return moves[random.nextInt(moves.length)];
    }
}
