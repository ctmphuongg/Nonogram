package org.team3.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleFactoryTest {

    /**
     * test if the return matrix is the correct puzzle in the database
     */
    @Test
    void getMatrix() {
        // set the round = 0 => the Ducks file in database
        PuzzleFactory.setRound(0);
        PuzzleFactory testFactory = new PuzzleFactory();
        int [][] testMatrix = testFactory.getMatrix();
        int [][] referMatrix = {{0,1,1,1,0},{0,1,0,1,1},{0,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        for (int i=0;i<5;++i)
            for (int j=0;j<5;++j)
                assertEquals(testMatrix[i][j],referMatrix[i][j]);

        // set the round = 2 => the Piggy file in database
        PuzzleFactory.setRound(2);
        testFactory = new PuzzleFactory();
        int [][] testMatrix1 = testFactory.getMatrix();
        int [][] referMatrix1 = {{1,1,1,1,0},{0,1,1,1,1},{1,1,0,1,0},{1,1,1,1,1},{1,1,1,1,0}};
        for (int i=0;i<5;++i)
            for (int j=0;j<5;++j)
                assertEquals(testMatrix1[i][j],referMatrix1[i][j]);

    }

    /**
     * check if the row hint function returns correctly
     */
    @Test
    void getRowHint() {
        // set the round = 1 => the Lion file in database
        PuzzleFactory.setRound(1);
        PuzzleFactory testFactory = new PuzzleFactory();

        //creating the refer test
        ArrayList<Integer>[] referRowHint = new ArrayList[5];
        referRowHint[0]=new ArrayList<>();
        referRowHint[0].add(3);
        referRowHint[1]=new ArrayList<>();
        referRowHint[1].add(4);
        referRowHint[2]=new ArrayList<>();
        referRowHint[2].add(4);
        referRowHint[3]=new ArrayList<>();
        referRowHint[3].add(4);
        referRowHint[4]=new ArrayList<>();
        referRowHint[4].add(5);

        // compare the refer and the test
        ArrayList<Integer>[] testRowHint = testFactory.getRowHint();
        for (int i=0;i<testRowHint.length;++i){
            assertEquals(testRowHint[i],referRowHint[i]);
        }
    }

    /**
     * check if the column hint function returns correctly
     */
    @Test
    void getColumnHint() {
        //// set the round = 1 => the Tree file in database
        PuzzleFactory.setRound(4);
        PuzzleFactory testFactory = new PuzzleFactory();

        //creating the refer test
        ArrayList<Integer>[] referColumnHint = new ArrayList[5];
        referColumnHint[0]=new ArrayList<>();
        referColumnHint[0].add(2);referColumnHint[0].add(1);
        referColumnHint[1]=new ArrayList<>();
        referColumnHint[1].add(3);referColumnHint[1].add(1);
        referColumnHint[2]=new ArrayList<>();
        referColumnHint[2].add(1);referColumnHint[2].add(3);
        referColumnHint[3]=new ArrayList<>();
        referColumnHint[3].add(3);referColumnHint[3].add(1);
        referColumnHint[4]=new ArrayList<>();
        referColumnHint[4].add(2);referColumnHint[4].add(1);

        // compare the refer and the test
        ArrayList<Integer>[] testColumnHint = testFactory.getColumnHint();
        for (int i=0;i<testColumnHint.length;++i){
            assertEquals(testColumnHint[i],referColumnHint[i]);
        }
    }

    /**
     * test the function counting colored box returns correctly
     */
    @Test
    void getColoredBox() {
        //set round = 1
        PuzzleFactory.setRound(1);
        PuzzleFactory testFactory1= new PuzzleFactory();
        // round 1 has 20 boxes
        assertEquals(20,testFactory1.getColoredBox());

        //set round = 0
        PuzzleFactory.setRound(0);
        PuzzleFactory testFactory0= new PuzzleFactory();
        // round 1 has 20 boxes
        assertEquals(20,testFactory0.getColoredBox());
    }

    /**
     * Count the number puzzles in the path
     */
    @Test
    void getPuzzleNumber() {
        //the number of puzzle in the database should be equal to the number of files in the folder path
        File directory = new File("src/main/resources/datasets/");
        assertEquals(PuzzleFactory.getPuzzleNumber(),directory.listFiles());
    }



    /**
     * test the increase round function
     */
    @Test
    void increaseRound() {
        // initially the round is 0
        PuzzleFactory.increaseRound();
        assertEquals(1,PuzzleFactory.round);

        // increase another time
        PuzzleFactory.increaseRound();
        assertEquals(2,PuzzleFactory.round);
    }
}