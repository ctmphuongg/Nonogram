package org.team3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class RoundTest {
    private Round roundTest;
    private PuzzleFactory puzzle;
    @BeforeEach
    void setUp() {
        puzzle = new PuzzleFactory();
        roundTest = new Round(puzzle);
    }

    @Test
    void setPlayingMode() {
        roundTest.setPlayingMode(PLAYING_MODE.CROSS);
        assertEquals(roundTest.getRoundState(), ROUND_STATE.NEW_ROUND);
    }

    @Test
    void getPuzzleFactory() {
        assertEquals(roundTest.getPuzzleFactory(), puzzle);
    }

    @Test
    void getLivesValueArray() {
    }

    @Test
    void getHints() {
        // Initially, must have 3 hints
        assertEquals(roundTest.getHints(), 3);

        // Choose a box that already chosen, must return false
        roundTest.setPlayingMode(PLAYING_MODE.HINT);
        roundTest.guessEvaluator(2, 3);
        assertEquals(roundTest.getHints(), 2);
    }

    @Test
    void isIsWin() {
    }

    @Test
    void isWinProperty() {
    }

    @Test
    void getLives() {

        assertEquals(roundTest.getLives(), 3);
    }


    @Test
    void checkValidGuess() {
        // Row exceed puzzle size, must return false
        assertEquals(roundTest.checkValidGuess(15,3), false);

        // Choose a box that already chosen, must return false
        roundTest.setPlayingMode(PLAYING_MODE.SQUARE);
        roundTest.guessEvaluator(2, 3);
        assertEquals(roundTest.checkValidGuess(2, 3), false);

        // Choose a valid box, must return
        assertEquals(roundTest.checkValidGuess(3, 3), true);

    }

    @Test
    void isRoundOver() {
    }

    @Test
    void isRoundWinner() {
    }

    @Test
    void guessEvaluator() {
    }

    @Test
    void initNewRound() {
    }

    @Test
    void isColored() {
    }


    @Test
    void getRoundState() {
        assertEquals(roundTest.getRoundState(), ROUND_STATE.NEW_ROUND);
    }
}