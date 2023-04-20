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
import org.team3.model.Round;

public class GameOverView {

//    private Round theModel = new Round();

    private VBox root;

    private Label lblRoundState;

    private Label lblPlayAgain;

    private Button btnYes;
    private Button btnNo;
    private Button btnRound;

    public VBox getRoot() {
        return root;
    }

    private void initScene(String gameMessage){
        this.root = new VBox();
        this.lblRoundState = new Label(gameMessage);
        this.lblPlayAgain = new Label("Play Again?");
        this.btnYes = new Button("Yes");
        this.btnNo = new Button("No");
        this.lblPlayAgain = new Label("Play Next Round?");
        this.btnYes = new Button("Yes");
        this.btnNo = new Button("No");
        this.btnRound = new Button("Back to round list");

        this.root.getChildren().addAll(this.lblRoundState,this.lblPlayAgain, this.btnYes,this.btnNo,this.btnRound);

    }

    public Button getBtnRound() {
        return btnRound;
    }

    public Button getBtnYes() {
        return btnYes;
    }

    public Button getBtnNo() {
        return btnNo;
    }

    public GameOverView(String gameMessage){
        this.initScene(gameMessage);
    }


}
