/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 06/04/2023
 * Time: 18:15
 *
 * Project: csci205_final_project
 * Package: org.team3
 * Class: Round
 *
 * Description:
 *
 * *****************************************/
package org.team3.model;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

enum ROUND_STATE{
    NEW_ROUND,
    ROUND_IN_PROGRESS,
    ROUND_WINNER,
    ROUND_OVER
}

enum SIGN_MODE{
    CROSS,
    SQUARE,
}

enum SQUARE_STATE{
    CORRECTLY_CHOSEN,
    WRONGLY_CHOSEN,
    NOT_CHOSEN,
    CORRECTLY_CROSSED,
    WRONGLY_CROSSED
}

/** A class for a Round in a Monogram game */
public class Round {
    /** Maximum number of lives for each round */
    private final int MAX_LIVES = 3;

    /** Puzzle dimension */
    private final int PUZZLE_ROW = 5;
    private final int PUZZLE_COL = 5;

    /** Puzzle to be guesses*/
    private int[][] guessPuzzle;

    /** Number of painted squares that are correctly guessed */
    private int paintedSquareNotGuessed;

    /** Row hints of the puzzle*/
    private ArrayList<Integer>[] rowHint;

    /** Column hints of puzzle*/
    private ArrayList<Integer>[] colHint;

    /** Number of lives have left */
    private int lives;

    /** Current puzzle from user's guesses */
    private SQUARE_STATE[][] currentPuzzle;

    /** User's last guess*/
    private String guessRow;
    private String guessCol;

    /** The sign player chooses to play (by default, choose a square) */
    private SIGN_MODE signMode;

    /** Current game state */
    private ROUND_STATE roundState;

    /** Scanner to read player's guess */
    private Scanner scnr = new Scanner(System.in);

    private PuzzleFactory puzzleFactory;
    public Round(PuzzleFactory puzzleFactory){
        this.puzzleFactory=puzzleFactory;
        this.lives = 0;
        this.currentPuzzle = generatePuzzleState();
        this.guessPuzzle = puzzleFactory.getMatrix();
        this.rowHint = puzzleFactory.getRowHint();
        this.colHint = puzzleFactory.getColumnHint();
        this.guessRow = null;
        this.guessCol = null;
        this.roundState = ROUND_STATE.NEW_ROUND;
        this.signMode = SIGN_MODE.SQUARE;
        this.paintedSquareNotGuessed = puzzleFactory.getColoredBox();
    }


    /**
     * Create an 5x5 array with all squares are in NOT_CHOSEN state
     * @return 5x5 array
     */
    private SQUARE_STATE[][] generatePuzzleState() {
        SQUARE_STATE[][] arr = new SQUARE_STATE[PUZZLE_ROW][PUZZLE_COL];

        // Initializing the array with 0
        for (int i = 0; i < PUZZLE_ROW; i++) {
            for (int j = 0; j < PUZZLE_COL; j++) {
                arr[i][j] = SQUARE_STATE.NOT_CHOSEN;
            }
        }
        return arr;
    }

    /**
     * Get player's playing mode each turn: Cross a square or want to quit?
     */
    private void getPlayingMode() {
        while (true) {
            System.out.println("Cross a square? [Y|N]:");
            String playingMode = scnr.next();

            if (playingMode.strip().equalsIgnoreCase("y")) {
                this.signMode = SIGN_MODE.CROSS;
                break;
            } else if (playingMode.strip().equalsIgnoreCase("n")){
                break;
            } else {
                System.out.println("Please choose an option!");
            }
        }

    }

    /**
     * Get player's guesses and check if it's valid
     */
    private void getUserGuess() {
        do {
            System.out.print("Enter row number: ");
            this.guessRow = scnr.next();
            System.out.print("Enter column number: ");
            this.guessCol = scnr.next();
        } while(!checkValidGuess());
    }


    /**
     * Check player's guess
     * A guess is valid if the row and column numbers are from 1 to 5 and only contain digits
     * @return true if it's a valid guess. Otherwise, return false
     */
    private boolean checkValidGuess(){
        if (this.guessRow.matches(".*\\D.*") || this.guessCol.matches(".*\\D.*")) { //return false if the guess contains non-digit characters
            System.out.println("Please enter a number!");
            return false;
        }
        if (parseInt(this.guessCol)-1 > PUZZLE_COL || parseInt(this.guessCol) <=0 || parseInt(this.guessRow)-1> PUZZLE_ROW || parseInt(this.guessRow) <=0 ){
            System.out.printf("Invalid square. Please enter numbers from 1 to 5\n", PUZZLE_COL);
            return false;
        }
        if (this.currentPuzzle[parseInt(this.guessRow)-1][parseInt(this.guessCol)-1]!=SQUARE_STATE.NOT_CHOSEN){
            System.out.println("The chosen box has been crossed, please try again!");
            return false;
        }
        return true;
    }


    /**
     *
     * @return true if the player uses all the lives -> The round is over
     */
    public boolean isRoundOver(){
        if (this.lives == MAX_LIVES){
            this.roundState = ROUND_STATE.ROUND_OVER;
        }
        return (this.roundState == ROUND_STATE.ROUND_OVER);
    }

    /**
     *
     * @return true if the player correctly guesses all the painted squares -> Win the round -> move to next round
     */
    public boolean isRoundWinner(){
        if (this.paintedSquareNotGuessed == 0){
            this.roundState = ROUND_STATE.ROUND_WINNER;
        }
        return (this.roundState == ROUND_STATE.ROUND_WINNER);
    }


    /**
     * Evaluate if player crossed or chose the correct squares and print appropriate messages
     * If the guess is incorrect, increment the number of lives
     */
    public void guessEvaluator() {
        if (this.signMode == SIGN_MODE.SQUARE) {
            if (guessPuzzle[parseInt(this.guessRow)-1][parseInt(this.guessCol)-1] == 1) { //Correctly chosen a square (square = 1)
                this.currentPuzzle[parseInt(this.guessRow)-1][parseInt(this.guessCol)-1] = SQUARE_STATE.CORRECTLY_CHOSEN;
                System.out.println("Correctly Chosen!");
                this.paintedSquareNotGuessed--;

            } else {
                this.currentPuzzle[parseInt(this.guessRow)-1][parseInt(this.guessCol)-1] = SQUARE_STATE.WRONGLY_CHOSEN;
                System.out.println("Wrongly Chosen! ");
                this.lives++;
            }
        }

        else {
            if (guessPuzzle[parseInt(this.guessCol)-1][parseInt(this.guessCol)-1] == 0){ //Correctly crossed a square (square = 0)
                this.currentPuzzle[parseInt(this.guessRow)-1][parseInt(this.guessCol)-1] = SQUARE_STATE.CORRECTLY_CROSSED;
                System.out.println("Correctly Crossed!");

                this.paintedSquareNotGuessed--;
            } else {
                this.currentPuzzle[parseInt(this.guessRow)-1][parseInt(this.guessCol)-1] = SQUARE_STATE.WRONGLY_CROSSED;
                this.lives++;
                System.out.println("Wrongly Crossed!");

            }
        }
        System.out.printf("You have %d lives left \n",MAX_LIVES - this.lives);

    }


    /**
     * Display current matrix after each turn
     */
    public void displayMatrix() {
        int col_hint_max = -1,row_hint_max=-1;
        for (int i = 0 ;i < this.guessPuzzle.length;++i){
            col_hint_max=Integer.max(col_hint_max,this.colHint[i].size());
            row_hint_max=Integer.max(row_hint_max,this.rowHint[i].size());
        }

        // print the column hints
        //padding to align with the matrix.
        // Padding will include the length of a separator | and ' ' after each elements
        String padding ="";
        for (int i = 0;i<row_hint_max*2;++i)
            padding=padding+" ";

        String columnHintPrint="";
        for (int i=col_hint_max-1;i>=0;--i) {
            // add padding to the current row
            columnHintPrint+=padding;
            //checker for the first hint element of the column
            boolean checker=false;
            for (ArrayList j:this.colHint){
                if (j.size()>i){
                    //if this is the first hint of the row
                    if (!checker)
                    {
                        checker=true;
                        columnHintPrint+="|";
                    }
                    // if the position is presented in the arraylist, print it (but count from the bottom)
                    columnHintPrint+=j.get(j.size()-i-1).toString()+"|";
                }
                else
                    //if not, simply add a blank
                    columnHintPrint+=" ";
            }
            // next line
            columnHintPrint+='\n';
        }
        System.out.printf(columnHintPrint);
        for (int i = 0; i < this.currentPuzzle.length; ++i) {
            System.out.println("-".repeat(this.currentPuzzle.length *2+1+padding.length()));

            // hint for this row
            String hintRow = "";
            for (int j:this.rowHint[i]) {
                hintRow += j+" ";
            }
            //fill in the blank
            while (hintRow.length()<padding.length()){
                hintRow= " " + hintRow;
            }
            System.out.print(hintRow);
            System.out.print("|");
            for (int j = 0; j < this.currentPuzzle[i].length; ++j) {
                if (this.currentPuzzle[i][j] == SQUARE_STATE.CORRECTLY_CROSSED || this.currentPuzzle[i][j] == SQUARE_STATE.WRONGLY_CHOSEN) {
                    System.out.print("X" + "|");
                } else if (this.currentPuzzle[i][j] == SQUARE_STATE.CORRECTLY_CHOSEN || this.currentPuzzle[i][j] == SQUARE_STATE.WRONGLY_CROSSED) {
                    System.out.print("1" + "|");
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
        System.out.println("-".repeat(this.currentPuzzle.length *2+1+padding.length()));
    }


    /**
     * Initialize a new round
     */
    public void initNewRound() {
        if (this.roundState == ROUND_STATE.NEW_ROUND) {
            this.roundState = ROUND_STATE.ROUND_IN_PROGRESS;
            System.out.println(this.paintedSquareNotGuessed);

            // Generate puzzle
            System.out.println("Ready to play Nonogram! You have 3 lives this round.");

            // Display empty puzzle matrix
            this.displayMatrix();

            // Continue asking for guesses if the game is not over or the player doesn't win
            while(!(isRoundOver()) && !(isRoundWinner())) {
                System.out.println(this.paintedSquareNotGuessed);
                playNextTurn();
            }

            if(isRoundWinner()){
                System.out.println("CONGRATS!! YOU WIN!!");

            } else if (isRoundOver()) {
                System.out.println("YOU LOSE!!");
            }

        }
    }

    /**
     * Play next turn if the round is not over
     * Ask for guess, evaluate guess, and display the matrix after each guesses
     */
    private void playNextTurn() {
        this.getPlayingMode();
        this.getUserGuess();
        if (this.checkValidGuess()) {
            this.guessEvaluator();
            this.displayMatrix();
        }
    }

    /**
     * Hint the user of the state of the chosen
     * @param row
     * @param column
     * @return
     */
    public int getHint(int row, int column){
        //update the box
        if (this.guessPuzzle[row][column]==1){
            this.currentPuzzle[row][column]=SQUARE_STATE.CORRECTLY_CHOSEN;
        }
        else{
            this.currentPuzzle[row][column]=SQUARE_STATE.CORRECTLY_CROSSED;
        }
        return this.guessPuzzle[row][column];
    }

}
