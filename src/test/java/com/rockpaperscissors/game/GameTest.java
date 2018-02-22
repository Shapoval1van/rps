package com.rockpaperscissors.game;

import com.rockpaperscissors.Move;
import com.rockpaperscissors.Player;
import com.rockpaperscissors.strategy.ConstantsMoveStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.junit.Assert.assertTrue;


public class GameTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Player player = new Player("test", 0, new ConstantsMoveStrategy(Move.ROCK));
    private Player player1 = new Player("test1", 0, new ConstantsMoveStrategy(Move.ROCK));

    @Test
    public void notEnoughPlayersGameTest() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Not enough players for the game");
        Game game = new Game();
        game.addPlayers(Collections.singletonList(player));
        game.doGame();
    }

    @Test
    public void drawGameTest() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("We cant find winner... Draw!!!!! Friendship won!!!!!!");
        Game game = new Game();
        game.addPlayers(Collections.singletonList(player));
        game.addPlayers(Collections.singletonList(player1));
        game.doGame();
    }

    @Test
    public void gameTest_Rock_should_win() {
        Game game = new Game();
        game.addPlayers(Collections.singletonList(player));
        player1.setMoveStrategy(new ConstantsMoveStrategy(Move.SCISSORS));
        game.addPlayers(Collections.singletonList(player1));
        game.doGame();
        game.present();
        assertTrue(outContent.toString().contains("|         1|      TEST|"));
        assertTrue(outContent.toString().contains("|         2|     TEST1|"));
    }
}
