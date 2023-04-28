/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023
 * Instructor: Brian King
 * Section: 9 am
 * Name: An Ngo
 * Date: 2023
 * Lab / Assignment: lab
 * Description:
 * * *****************************************/

package org.team3.model;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * A class that stores and generates a puzzle matrix
 */
public class PuzzleFactory {
    /**The current game round*/
    public static int round = 0;

    /**The matrix puzzle*/
    private int [][] matrix;

    /**Row puzzle hint*/
    private ArrayList<Integer> [] rowHint;

    /**Column puzzle hint*/
    private ArrayList<Integer> [] columnHint;


    public PuzzleFactory() {
        this.matrix = convertCSVtoArray(getPuzzleFileName());
        this.rowHint = new ArrayList[this.matrix.length];
        this.columnHint = new ArrayList[this.matrix.length];
        generateHint();
    }


    /**
     * generate the hints for the users
     */
    private void generateHint(){
        //row hints
        for (int i = 0;i < matrix.length;++i){
            //for each row

            // array to store the number of consecutive colored box
            ArrayList<Integer> tp = new ArrayList<>();

            // store the current consecutive colored box
            int curCount = 0;
            for (int j : matrix[i]){
                if (j==0){
                    // if the current box is not colored and the current box count is not 0
                    if (curCount!=0)
                        tp.add(curCount);
                    curCount=0;
                }
                else{
                    curCount++;
                }
            }
            if (curCount!=0){
                // if there are unsaved consecutive segment
                tp.add(curCount);
            }
            this.rowHint[i] = tp;
        }

        //column hint
        for (int i = 0;i < matrix[0].length;++i) {
            // for each column

            // array to store the number of consecutive colored box
            ArrayList<Integer> tp = new ArrayList<>();

            // store the current consecutive colored box
            int curCount = 0;
            for (int j = 0;j < matrix.length;++j)  {
                if (matrix[j][i] == 0) {
                    // if the current box is not colored and the current box count is not 0
                    if (curCount != 0)
                        tp.add(curCount);
                    curCount = 0;
                } else {
                    curCount++;
                }
            }
            if (curCount != 0) {
                // if there are unsaved consecutive segment
                tp.add(curCount);
            }
            this.columnHint[i] = tp;
        }
    }

    /**
     * Print the puzzle matrix - mainly for test
     */
    public void displayMatrix(){
        //count the column and row with most hint segment
        int col_hint_max = -1,row_hint_max=-1;
        for (int i = 0 ;i < matrix.length;++i){
            col_hint_max=Integer.max(col_hint_max,columnHint[i].size());
            row_hint_max=Integer.max(row_hint_max,rowHint[i].size());
        }

        // print the column hints
        //padding to align with the matrix.
        // Padding will include the length of a separator | and ' ' after each elements
        String padding ="";
        for (int i = 0;i<=row_hint_max*2;++i)
            padding=padding+" ";

        String columnHintPrint="";
        for (int i=col_hint_max-1;i>=0;--i) {
            // add padding to the current row
            columnHintPrint+=padding;
            for (ArrayList j:this.columnHint){
                if (j.size()>i){
                    // if the position is presented in the arraylist, print it (but count from the bottom)
                    columnHintPrint+=j.get(j.size()-i-1).toString();
                }
                else
                    //if not, simply add a blank
                    columnHintPrint+=" ";
                columnHintPrint+=" ";
            }
            // next line
            columnHintPrint+="\n";
        }

        // add separating line
        columnHintPrint+=padding;
        for (int i=0;i<matrix.length;++i)
            columnHintPrint+="--";

        System.out.println(columnHintPrint);
        for (int i = 0 ;i < matrix.length;++i) {
            // hint for this row
            String hintRow = "";
            for (int j:rowHint[i]) {
                hintRow += j+" ";
            }
            hintRow+='|';
            //fill in the blank
            while (hintRow.length()<padding.length()){
                hintRow= " " + hintRow;
            }
            System.out.print(hintRow);
            //print the contents of the matrix
            for (int j = 0; j < matrix[i].length; ++j)
                System.out.print(matrix[i][j] + " ");

            System.out.println();
        }
    }

    /**
     * Get the number of the colored boxes in the matrix
     * @return the number of colored boxes in the matrix
     */
    public int getColoredBox(){
        int count=0;
        //loop through the matrix and count
        for (int i = 0 ;i < matrix.length;++i) {
            for (int j = 0; j < matrix[i].length; ++j)
                if (matrix[i][j]==1)
                    count++;
        }
        return count;
    }

    /**
     * Read the puzzle file and convert it into puzzle matrix
     * @return
     */
    private static int[][] convertCSVtoArray(String fileName) {
        List<List<Integer>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> values = Arrays.stream(line.split(",")).map(x->Integer.parseInt(x)).toList();
                records.add(values);
            }
        }catch (Exception e){

        }
        int [][] puzzle = new int [records.size()][records.size()];
        for (int i = 0;i<records.size();++i)
            for (int j = 0;j<records.size();++j)
                puzzle[i][j] = records.get(i).get(j);
        return puzzle;
    }

    /**
     * Get a puzzle file name based on current round
     * @return the round's puzzle file name
     */
    private String getPuzzleFileName() {
        String fileName="";
        try{
            List<String> puzzleFileNameList = Files.list(Paths.get("src/main/resources/datasets/")).
                    map(Path::getFileName).map(Path::toString).sorted().toList();
            fileName = "src/main/resources/datasets/"+ puzzleFileNameList.get((round) % puzzleFileNameList.size());
        }catch (Exception e){}
        return fileName;
    }

    /**
     * Get the number of puzzle in the csv file
     * @return  the number of puzzle
     */
    public static int getPuzzleNumber(){
        int numPuzzle = 0;
        try{
            List<String> puzzleFileNameList = Files.list(Paths.get("src/main/resources/datasets/")).
                    map(Path::getFileName).map(Path::toString).toList();
           numPuzzle=puzzleFileNameList.size();
        }catch (Exception e){}
        return numPuzzle;
    }

    /**
     * Get the puzzle matrix for the round set
     * @param round the round number
     */
    public static void setRound(int round) {
        if (round>=getPuzzleNumber())round = 0;
        PuzzleFactory.round = round;
    }

    /**
     * Increase the number of round every time create a new Round object
     * and get the puzzle matrix for that round
     */
    public static void increaseRound() {
        PuzzleFactory.round+=1;
        PuzzleFactory.round%=getPuzzleNumber();
    }

    /**
     * @return the puzzle matrix
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * @return current round number
     */
    public static int getRound() {
        return round;
    }

    /**
     * @return the row hint
     */
    public ArrayList<Integer>[] getRowHint() {
        return rowHint;
    }

    /**
     * @return the column hint
     */
    public ArrayList<Integer>[] getColumnHint() {
        return columnHint;
    }
}
