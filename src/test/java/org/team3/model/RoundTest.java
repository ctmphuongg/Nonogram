package org.team3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RoundTest {
    /** New RoundFactory to conduct tests*/
    private RoundFactory roundTest;
    /** New PuzzleFactory for the round*/
    private PuzzleFactory puzzle;
    /** Initiate new Round and Puzzle before each test*/
    @BeforeEach
    void setUp() {
        puzzle = new PuzzleFactory();
        roundTest = new RoundFactory(puzzle);
    }

    /** Test if the program can activate each playing mode*/
    @Test
    void setPlayingMode() {
        roundTest.setPlayingMode(PLAYING_MODE.CROSS);
        assertEquals(roundTest.getPlayMode(), PLAYING_MODE.CROSS);
            roundTest.setPlayingMode(PLAYING_MODE.SQUARE);
        assertEquals(roundTest.getPlayMode(), PLAYING_MODE.SQUARE);
        roundTest.setPlayingMode(PLAYING_MODE.HINT);
        assertEquals(roundTest.getPlayMode(), PLAYING_MODE.HINT);
    }

    /**
     * Check if the puzzle generated is fit with the initial one
     */
    @Test
    void getPuzzleFactory() {
        assertEquals(roundTest.getPuzzleFactory(), puzzle);
    }

    /**
     * Test if the hints are consistent with results when user choose each square
     */
    @Test
    void getHints() {
        // Initially, must have 3 hints
        assertEquals(roundTest.getHints(), 3);

        // Choose a box that already chosen, must return false
        roundTest.setPlayingMode(PLAYING_MODE.HINT);
        roundTest.guessEvaluator(2, 3);
        assertEquals(roundTest.getHints(), 2);
    }

    /**
     * Test if the remaining lives are modified correctly according to users' choice
     */
    @Test
    void getLives() {
        // Initially have 3 lives
        assertEquals(roundTest.getLives(), 3);

        // Choose a box, if that box is true, lives unchanged, else, lives decrease
        roundTest.setPlayingMode(PLAYING_MODE.CROSS);
        boolean result = roundTest.guessEvaluator(2, 3);

        if (result) {
            assertEquals(roundTest.getLives(), 3);
        } else {
            assertEquals(roundTest.getLives(), 2);
        }
    }


    /**
     * Test checkValidGuess with cases of indexes out of range and chosen box
     */
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

    /**
     * Test if the guessEvaluator return the same as real result, got from getHints
     */
    @Test
    void guessEvaluator() {
        // Check hint to see if a box is colored
        roundTest.setPlayingMode(PLAYING_MODE.SQUARE);

        // Compare with guess evaluator
        RoundFactory roundCompare = new RoundFactory(puzzle);
        roundCompare.setPlayingMode(PLAYING_MODE.HINT);

        // The 2 results must be the same
        assertEquals(roundTest.guessEvaluator(2, 2), roundCompare.isColored(2, 2));
    }


    /**
     * Test if the function correctly return the square answer from inner nonogram matrix
     */
    @Test
    void isColored() {
        // Check hint to see if a box is colored
        roundTest.setPlayingMode(PLAYING_MODE.HINT);

        // Compare with guess evaluator
        RoundFactory roundCompare = new RoundFactory(puzzle);
        roundCompare.setPlayingMode(PLAYING_MODE.SQUARE);

        // The 2 results must be the same
        assertEquals(roundTest.isColored(4, 4), roundCompare.guessEvaluator(4, 4));
    }


    /**
     * Test the get current round state
     */
    @Test
    void getRoundState() {
        assertEquals(roundTest.getRoundState(), ROUND_STATE.NEW_ROUND);
    }
}