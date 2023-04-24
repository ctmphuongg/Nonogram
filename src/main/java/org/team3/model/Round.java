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

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

enum ROUND_STATE{
    NEW_ROUND,
    ROUND_IN_PROGRESS,
    ROUND_WINNER,
    ROUND_OVER
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
    /** Puzzle dimension */
    private final int PUZZLE_ROW = 5;
    private final int PUZZLE_COL = 5;

    /** Puzzle to be guessed */
    private final int[][] guessPuzzle;

    /** Number of painted squares that needed to be guessed */
    private int paintedSquareNotGuessed;

    /** Row hints of the puzzle*/
    private final ArrayList<Integer>[] rowHint;

    /** Column hints of puzzle*/
    private final ArrayList<Integer>[] colHint;

    /** Number of lives have left */
    private int lives;
    /** Array of lives boolean properties. This would be used for displaying lives */
    private ArrayList<SimpleBooleanProperty> livesValueArray;

    /** Number of hints have left */
    private int hints;

    /** Current puzzle from user's guesses */
    private final SQUARE_STATE[][] currentPuzzle;

    /** User's last guess*/
    private String guessRow;
    private String guessCol;

    /** The playing mode player chooses to play (choose a square, cross a square or quit) */
    private PLAYING_MODE playingMode;


    /** Current game state */
    private ROUND_STATE roundState;

    /** Scanner to read player's guess */
    private Scanner scnr = new Scanner(System.in);
    private PuzzleFactory puzzleFactory;
    private SimpleBooleanProperty isWin;


    public void setPlayingMode(PLAYING_MODE playingMode) {
        this.playingMode = playingMode;
    }

    public PuzzleFactory getPuzzleFactory() {
        return puzzleFactory;
    }

    public Round(PuzzleFactory puzzleFactory){
        this.hints = 3; // Max hints per round is 3
        this.lives = 3; // Max lives per round is 3
        this.livesValueArray = new ArrayList<>();
        for (int i = 0;i<this.lives;++i)//add the properties to list
            livesValueArray.add(new SimpleBooleanProperty(true));
        this.currentPuzzle = generatePuzzleState();
        this.guessPuzzle = puzzleFactory.getMatrix(); //Get the puzzle matrix to be guessed in puzzleFactory
        this.rowHint = puzzleFactory.getRowHint();
        this.colHint = puzzleFactory.getColumnHint();
        this.guessRow = null;
        this.guessCol = null;
        this.roundState = ROUND_STATE.NEW_ROUND;
        this.paintedSquareNotGuessed = puzzleFactory.getColoredBox();
        this.puzzleFactory = puzzleFactory;
        this.isWin = new SimpleBooleanProperty(false);
    }

    public SimpleBooleanProperty getLivesValueArray(int i) {
        return this.livesValueArray.get(i);
    }

    /**
     * Decrease the current lives and set the value property of the lives box at that position to false
     */
    private void decreaseLives(){
        this.lives--;
        this.livesValueArray.get(lives).setValue(false);
    }

    /**
     * Get the number of hints have left
     * @return
     */
    public int getHints() {
        return hints;
    }

    public boolean isIsWin() {
        return isWin.get();
    }

    public SimpleBooleanProperty isWinProperty() {
        return isWin;
    }

    /**
     * Create an 5x5 array with all squares are in NOT_CHOSEN state
     * @return 5x5 array
     */
    private SQUARE_STATE[][] generatePuzzleState() {
        SQUARE_STATE[][] arr = new SQUARE_STATE[PUZZLE_ROW][PUZZLE_COL];

        // Initializing the matrix with all the squares in NOT_CHOSEN states
        for (int i = 0; i < PUZZLE_ROW; i++) {
            for (int j = 0; j < PUZZLE_COL; j++) {
                arr[i][j] = SQUARE_STATE.NOT_CHOSEN;
            }
        }
        return arr;
    }

    public int getLives() {
        return lives;
    }

    /**
     * Get player's playing mode each turn: Cross a square, Choose a square, Get hint or Want to quit?
     */
    public void getPlayingMode() {
        while (true) {
            System.out.println("Select a playing mode: ");
            System.out.println("1. Choose a square");
            System.out.println("2. Cross a square");
            System.out.println("3. Get hint");
            System.out.println("4. Quit");
            String playingMode = scnr.next();

            // Change playing mode according to user's choices
            if (playingMode.strip().equals("1")){
                this.playingMode = PLAYING_MODE.SQUARE;
                break;
            } else if (playingMode.strip().equals("2")) {
                this.playingMode = PLAYING_MODE.CROSS;
                break;
            } else if (playingMode.strip().equals("4")) {
                this.roundState = ROUND_STATE.ROUND_OVER;
                break;
            }else if (playingMode.strip().equals("3")){
                if (this.hints == 0){ // Not allowed to get hint if there are no hints left
                    System.out.println("No hints left!");
                }
                else {
                    this.playingMode = PLAYING_MODE.HINT;
                    break;
                }
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
    public boolean checkValidGuess(int row,int col){
        this.guessRow = ""+row;
        this.guessCol = ""+col;
        if (this.guessRow.matches(".*\\D.*") || this.guessCol.matches(".*\\D.*")) { //return false if the guess contains non-digit characters
            System.out.println("Please enter a number!");
            return false;
        }
        if (parseInt(this.guessCol) > PUZZLE_COL || parseInt(this.guessCol) <0 || parseInt(this.guessRow)>PUZZLE_ROW || parseInt(this.guessRow) <0 ){
            System.out.printf("Invalid square. Please enter numbers from 1 to 5\n", PUZZLE_COL);
            return false;
        }
        if (this.currentPuzzle[parseInt(this.guessRow)][parseInt(this.guessCol)]!= SQUARE_STATE.NOT_CHOSEN){
            System.out.println("The chosen box has been chosen, please try again!");
            return false;
        }
        return true;
    }

    private boolean checkValidGuess(){
        if (this.guessRow.matches(".*\\D.*") || this.guessCol.matches(".*\\D.*")) { //return false if the guess contains non-digit characters
            System.out.println("Please enter a number!");
            return false;
        }
        if (parseInt(this.guessCol) > PUZZLE_COL || parseInt(this.guessCol) <0 || parseInt(this.guessRow)>PUZZLE_ROW || parseInt(this.guessRow) <0 ){
            System.out.printf("Invalid square. Please enter numbers from 1 to 5\n", PUZZLE_COL);
            return false;
        }
        if (this.currentPuzzle[parseInt(this.guessRow)][parseInt(this.guessCol)]!= SQUARE_STATE.NOT_CHOSEN){
            System.out.println("The chosen box has been chosen, please try again!");
            return false;
        }
        return true;
    }


    /**
     *
     * @return true if the player uses all the lives -> The round is over
     */
    public boolean isRoundOver(){
        if (this.livesValueArray.size()==0){ // The round is over if there are no lives left
            this.roundState = ROUND_STATE.ROUND_OVER;
        }
        return (this.roundState == ROUND_STATE.ROUND_OVER);
    }

    /**
     *
     * @return true if the player correctly guesses all the painted squares -> Win the round -> move to next round
     */
    public boolean isRoundWinner(){
        if (this.paintedSquareNotGuessed == 0){ //If player guesses all the squares, then they win
            this.roundState = ROUND_STATE.ROUND_WINNER;
        }
        return (this.roundState == ROUND_STATE.ROUND_WINNER);
    }


    /**
     * Evaluate if player crossed or chose the correct squares and print appropriate messages
     * If the guess is incorrect, decrement the number of lives have left
     * If player chooses to get a hint, decrement the number of hints have left
     */
    public boolean guessEvaluator(int row,int column) {
        if (this.playingMode == PLAYING_MODE.SQUARE) {
            if (guessPuzzle[row][column] == 1) { //Correctly chosen a square (square = 1)
                this.currentPuzzle[row][column] = SQUARE_STATE.CORRECTLY_CHOSEN;
                System.out.println("Correctly Chosen!");
                this.paintedSquareNotGuessed--;
                if (isRoundWinner()) {
                    isWin.setValue(true);
                }
                return true;

            } else {
                this.currentPuzzle[row][column] = SQUARE_STATE.WRONGLY_CHOSEN;
                System.out.println("Wrongly Chosen! ");
                decreaseLives();
                System.out.println("Lives remained: "+ getLivesValueArray(lives).getValue());
                return false;
            }
        }

        else if (this.playingMode == PLAYING_MODE.CROSS){
            if (guessPuzzle[row][column] == 0){ //Correctly crossed a square (square = 0)
                this.currentPuzzle[row][column] = SQUARE_STATE.CORRECTLY_CROSSED;
                System.out.println("Correctly Crossed!");

                return true;
            } else {
                this.currentPuzzle[row][column] = SQUARE_STATE.WRONGLY_CROSSED;
                decreaseLives();
                System.out.println("Wrongly Crossed!");
                System.out.println("Lives remained: "+ getLivesValueArray(lives).getValue());
                return false;
            }
        }
        else {
            this.getHint(column, row);
            this.hints--;
        }
        System.out.printf("You have %d lives and %d hints left \n",this.livesValueArray, this.hints);
        return false;
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
            columnHintPrint+=padding+"|";
            //checker for the first hint element of the column
            boolean checker=false;
            for (ArrayList j:this.colHint){
                // if the position is presented in the arraylist, print it (but count from the bottom)
                if (j.size()>i){
                    columnHintPrint+=j.get(j.size()-i-1).toString()+"|";
                    //if this is the first hint of the row

                }
                else
                    //if not, simply add a blank
                    columnHintPrint+=" |";
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

            System.out.println("Ready to play Nonogram! You have 3 lives this round.");

            // Display empty puzzle matrix
//            this.displayMatrix();

            // Continue asking for guesses if the game is not over or the player doesn't win
//            while(!(isRoundOver()) && !(isRoundWinner())) {
//                playNextTurn();
//            }

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
        if (!isRoundOver()) {
            this.getUserGuess();
            if (this.checkValidGuess()) {
                this.guessEvaluator(Integer.parseInt(this.guessCol)-1,Integer.parseInt(this.guessRow)-1);
                this.displayMatrix();
            }
        }
    }


    /**
     * Check if the square is a colored square
     * @param row - row to get hint
     * @param col - col to get hint
     * @return true if the square is a colored square
     */
    public boolean isColored(int row, int col){
        this.hints --;
        if (this.guessPuzzle[row][col]==1){
            this.currentPuzzle[row][col]=SQUARE_STATE.CORRECTLY_CHOSEN;
            this.paintedSquareNotGuessed--;
            if (isRoundWinner()) {
                isWin.setValue(true);
            }
            return true;
        }
        this.currentPuzzle[row][col]=SQUARE_STATE.CORRECTLY_CROSSED;
        return false;
    }

    /**
     * Change the state of the square that player wants to get hint
     * @param row - player's guessed row
     * @param column - player's guessed col
     */
    public void getHint(int row, int column){
        //update the square
        if (this.guessPuzzle[row][column]==1){
            this.currentPuzzle[row][column]=SQUARE_STATE.CORRECTLY_CHOSEN;
            if (isRoundWinner()) {
                isWin.setValue(true);
            }
        }
        else{
            this.currentPuzzle[row][column]=SQUARE_STATE.CORRECTLY_CROSSED;
        }
    }

    /**
     *
     * @return current Round state
     */
    public ROUND_STATE getRoundState() {
        return roundState;
    }

}
