/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 17/04/2023
 * Time: 09:14
 *
 * Project: csci205_final_project
 * Package: org.team3.GameResult
 * Class: GameResultView
 *
 * Description:
 *
 * *****************************************/
package org.team3.GameOver;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverView {

//    private Round theModel = new Round();

    private VBox root;

    private Label lblRoundState;


    private Button btnAgain;
    private Button btnRound;
    private Button btnNextRound;

    public VBox getRoot() {
        return root;
    }

    private void initScene(String gameMessage,boolean gameStatus){
        this.root = new VBox();
        this.lblRoundState = new Label(gameMessage);
        this.btnAgain = new Button("Play Again?");
        this.btnNextRound = new Button("Play Next Round?");
        this.btnRound = new Button("Back to round list");
        this.root.getChildren().addAll(this.lblRoundState,this.btnRound,this.btnAgain);
        if (gameStatus==true){
            //the game state is win
            this.root.getChildren().addAll(btnNextRound);
        }
    }

    public Button getBtnAgain() {
        return btnAgain;
    }

    public Button getBtnNextRound() {
        return btnNextRound;
    }

    public Button getBtnRound() {
        return btnRound;
    }

    public GameOverView(String gameMessage,boolean gameStatus){
        this.initScene(gameMessage,gameStatus);
    }


}
