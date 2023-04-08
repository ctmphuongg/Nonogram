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


import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseUnsignedInt;


enum GAME_STATE {
    NEW_GAME,
    GAME_IN_PROGRESS,
    GAME_LOSER,
    GAME_WINNER,
    GAME_OVER,
}


/** The main class for a Monogram game */
public class Nonogram {
    private final int MAX_ROUNDS = 5;


    private GAME_STATE gameState;
    private Round newRound;


    public Nonogram(){
        this.gameState = GAME_STATE.NEW_GAME;
    }


    /**
     * Initialize a new game
     */
    public void initNewGame(){
        if (this.gameState == GAME_STATE.NEW_GAME){
            System.out.println("Welcome to Nonogram!");
            this.gameState = GAME_STATE.GAME_IN_PROGRESS;
            do{
                PuzzleFactory puzzleFactory = new PuzzleFactory();
                puzzleFactory.displayMatrix();
                this.newRound = new Round(puzzleFactory);
                newRound.initNewRound();
            }while (!isGameOver() && !isGameWinner());
            if (isGameWinner()){
                System.out.println("YOU WIN THE GAME");
            } else{
                System.out.println("YOU LOSE THE GAME");
            }
        }


    }




    /**
     * A game is over if player wins all 5 rounds or loses a round
     * @return if the game is over
     */
    public boolean isGameOver(){
        if (this.newRound.isRoundOver()){
            this.gameState = GAME_STATE.GAME_OVER;}
        return this.gameState == GAME_STATE.GAME_OVER;}




    public boolean isGameWinner(){
        return (this.gameState == GAME_STATE.GAME_WINNER);
    }




    public static void main(String[] args) {
        Nonogram newGame = new Nonogram();
        newGame.initNewGame();
    }




}


