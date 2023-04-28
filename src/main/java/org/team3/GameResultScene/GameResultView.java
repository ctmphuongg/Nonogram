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
package org.team3.GameResultScene;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * MVC view class for Game Over scene
 */
public class GameResultView {
    /** Vbox container for root */
    private VBox root;

    /** Labels for game result, play again, back to round list, and play next round */
    private Label lblRoundState; Label lblAgain; Label lblBacktoRound; Label lblNextRound;

    /** Image to be displayed for each game result */
    private ImageView resultImageView;

    /**
     * @return the root of the scene
     */
    public VBox getRoot() {
        return root;
    }

    /**
     * Initialize the entire Game Over scene
     */
    private void initScene(String gameMessage,boolean gameStatus){
        // Set up the Vbox container for the root
        this.root = new VBox();

        // Set up image for each game result
        this.resultImageView = new ImageView();
        this.resultImageView .setFitHeight(100);
        this.resultImageView .setFitWidth(100);

        // Set up game result label
        this.lblRoundState = new Label(gameMessage);
        this.lblRoundState.setStyle("-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-font-size: 25px; -fx-text-fill: white;");
        // Set up play again label
        this.lblAgain = new Label("Play Again");
        this.lblAgain.getStyleClass().add("small-blue-text");
        // Set up play next round label
        this.lblNextRound = new Label("Play Next Round");
        this.lblNextRound.getStyleClass().add("small-blue-text");

        // Set up back to round label with an image inside
        this.lblBacktoRound = new Label();
        ImageView lblImageView = new ImageView(new Image("/pic/back.png"));
        lblImageView.setFitWidth(30); // Set the size of the image view to fit within the label
        lblImageView.setFitHeight(30);
        this.lblBacktoRound.setGraphic(lblImageView); // Set the image view as the label's graphic


        // Display message and image according to each game state
        if (gameStatus==true){
            //the game state is win
            this.resultImageView .setImage(new Image("/pic/happy.png"));
            this.root.getChildren().addAll(this.resultImageView , this.lblRoundState,this.lblAgain, this.lblNextRound,this.lblBacktoRound);
        }
        else{
            this.resultImageView .setImage(new Image("/pic/sad.png"));
            this.root.getChildren().addAll(this.resultImageView , this.lblRoundState,this.lblAgain,this.lblBacktoRound);

        }

    }


    /**
     * @return play again label
     */
    public Label getLblAgain() {
        return lblAgain;
    }

    /**
     * @return play next round label
     */
    public Label getLblNextRound() {
        return lblNextRound;
    }

    /**
     * @return play back to round list label
     */
    public Label getLblBacktoRound() {
        return lblBacktoRound;
    }



    public GameResultView(String gameMessage, boolean gameStatus){
        this.initScene(gameMessage,gameStatus);
    }


}
