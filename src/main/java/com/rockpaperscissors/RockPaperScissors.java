package com.rockpaperscissors;

import com.rockpaperscissors.game.Game;
import com.rockpaperscissors.strategy.ConstantsMoveStrategy;
import com.rockpaperscissors.strategy.RandomMoveStrategy;

import java.util.Arrays;

public class RockPaperScissors {
    private static int DEFAULT_COUNT_OF_ITERATION = 10;

    public static void main(String... argv) {
        int countOfIteration;
        if (argv.length != 0) {
            countOfIteration = Integer.parseInt(argv[0]);
        } else {
            countOfIteration = DEFAULT_COUNT_OF_ITERATION;
        }
        Game game = new Game();
        Player dart = new Player("Veider", 0, new RandomMoveStrategy());
        Player luke = new Player("Skywalker", 0, new ConstantsMoveStrategy(Move.ROCK)); // design )
        Player yoda = new Player("Yoda", 0, new RandomMoveStrategy());
        for (int i = 0; i < countOfIteration; i++) {
            game.addPlayers(Arrays.asList(dart, luke, yoda));
            game.doGame();
            game.present();
            game.cleanUp();
        }
        System.out.println("=======Statistics=========");
        System.out.println(dart.getAlias() + "->" + dart.getRang());
        System.out.println(luke.getAlias() + "->" + luke.getRang());
        System.out.println(yoda.getAlias() + "->" + yoda.getRang());
    }
}
