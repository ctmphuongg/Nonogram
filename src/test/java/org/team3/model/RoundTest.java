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
        assertEquals(roundTest.checkValidGuess(15,3), false);

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
    void displayMatrix() {
    }

    @Test
    void initNewRound() {
    }

    @Test
    void isColored() {
    }

    @Test
    void getHint() {
    }

    @Test
    void getRoundState() {
        assertEquals(roundTest.getRoundState(), ROUND_STATE.NEW_ROUND);
    }
}