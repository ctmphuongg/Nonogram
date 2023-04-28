/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 05/04/2023
 * Time: 14:14
 *
 * Project: csci205_final_project
 * Package: Puzzle
 * Class: Monogram
 *
 * Description:
 *
 * *****************************************/
package org.team3.model;


import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseUnsignedInt;


enum GAME_STATE {
    NEW_GAME,
    GAME_IN_PROGRESS,
    GAME_LOSER,
    GAME_WINNER,
    GAME_OVER,
}


/** The main class for a Monogram game - Mainly for testing on console */
public class Nonogram {

    private GAME_STATE gameState;
    private Round newRound;
    /** Number of rounds has been played */
    private int round;


    public Nonogram(){
        this.gameState = GAME_STATE.NEW_GAME;
        this.round = 1;
    }


    /**
     * Initialize a new game
     */
    public void initNewGame(){
        if (this.gameState == GAME_STATE.NEW_GAME){
            System.out.println("Welcome to Nonogram!");
            this.gameState = GAME_STATE.GAME_IN_PROGRESS;
            PuzzleFactory puzzleFactory;
            do{
                puzzleFactory = new PuzzleFactory();
                System.out.println("Puzzle to be guessed (demo)");
                puzzleFactory.displayMatrix();
                this.newRound = new Round(puzzleFactory);
                System.out.printf("ROUND %d \n", this.round);
                newRound.initNewRound();
                this.round ++;
            }while (!isGameOver() && !isGameWinner());
            if (isGameWinner()){
                System.out.println("YOU WIN THE GAME");
            } if(isGameOver()){
                puzzleFactory.displayMatrix();
            }
        }
    }



    /**
     * A game is over if player loses a round
     * @return if the game is over
     */
    public boolean isGameOver(){
        if (this.newRound.isRoundOver()){
            this.gameState = GAME_STATE.GAME_OVER;}
        return this.gameState == GAME_STATE.GAME_OVER;}


    /**
     * @return if the player wins the game if they win all 5 rounds
     */
    public boolean isGameWinner(){
        int MAX_ROUNDS = PuzzleFactory.getPuzzleNumber();
        if (this.round == MAX_ROUNDS){
            this.gameState = GAME_STATE.GAME_WINNER;
        }
        return (this.gameState == GAME_STATE.GAME_WINNER);
    }



//    public static void main(String[] args) {
//        Nonogram newGame = new Nonogram();
//        newGame.initNewGame();
//        Scanner scnr = new Scanner(System.in);
//        while (true) {
//            if (newGame.isGameOver() || newGame.isGameWinner()) {
//                System.out.println("Want to play again? [Y|N]");
//                if (scnr.next().strip().equalsIgnoreCase("n")) {
//                    break;
//                } else {
//                    Nonogram anotherGame = new Nonogram();
//                    anotherGame.initNewGame();
//                }
//            }
//        }
//    }

}


