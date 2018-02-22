package com.rockpaperscissors.strategy;

import com.rockpaperscissors.Move;

public class ConstantsMoveStrategy implements MoveStrategy {

    public final Move constantMove;

    public ConstantsMoveStrategy(Move constantMove) {
        this.constantMove = constantMove;
    }

    public Move getMove() {
        return constantMove;
    }
}
