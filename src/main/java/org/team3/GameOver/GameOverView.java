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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GameOverView {

//    private Round theModel = new Round();

    private VBox root;

    private Label lblRoundState;

    private Label lblAgain;
    private Label lblBacktoRound;
    private Label lblNextRound;

    private ImageView imageView;

    public VBox getRoot() {
        return root;
    }

    private void initScene(String gameMessage,boolean gameStatus){
        this.root = new VBox();

        this.imageView = new ImageView();
        this.imageView.setFitHeight(100);
        this.imageView.setFitWidth(100);

        this.lblRoundState = new Label(gameMessage);
        this.lblRoundState.setStyle("-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-font-size: 25px; -fx-text-fill: white;");

        this.lblAgain = new Label("Play Again");
        this.lblAgain.getStyleClass().add("small-blue-text");

        this.lblNextRound = new Label("Play Next Round");
        this.lblNextRound.getStyleClass().add("small-blue-text");

        //Back to round label
        this.lblBacktoRound = new Label();
        ImageView lblImageView = new ImageView(new Image("/pic/back.png"));
        // Set the size of the image view to fit within the label
        lblImageView.setFitWidth(30);
        lblImageView.setFitHeight(30);
        // Set the image view as the label's graphic
        this.lblBacktoRound.setGraphic(lblImageView);

        // Display message corresponding to each game state
        if (gameStatus==true){
            //the game state is win
            this.imageView.setImage(new Image("/pic/happy.png"));
            this.root.getChildren().addAll(this.imageView, this.lblRoundState,this.lblAgain, this.lblNextRound,this.lblBacktoRound);
        }
        else{
            this.imageView.setImage(new Image("/pic/sad.png"));
            this.root.getChildren().addAll(this.imageView, this.lblRoundState,this.lblAgain,this.lblBacktoRound);

        }

    }

    public Label getLblAgain() {
        return lblAgain;
    }

    public Label getLblNextRound() {
        return lblNextRound;
    }

    public Label getLblBacktoRound() {
        return lblBacktoRound;
    }

    public GameOverView(String gameMessage,boolean gameStatus){
        this.initScene(gameMessage,gameStatus);
    }


}
