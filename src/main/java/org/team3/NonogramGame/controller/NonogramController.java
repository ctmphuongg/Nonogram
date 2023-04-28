/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023
 * Instructor: Prof. Brian King
 *
 * Name: MINHPHUONG CAO
 * Section: Section 01
 * Date: 4/17/23
 * Time: 9:12 AM
 *
 * Project: csci205_final_project
 * Package: org.team3.controller
 * Class: NonogramController*
 * Description:
 *
 * *****************************************/

package org.team3.NonogramGame.controller;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import org.team3.GameManager.GameManager;
import org.team3.GameManager.SceneManager;
import org.team3.MusicPlayer;
import org.team3.model.PLAYING_MODE;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;
import org.team3.NonogramGame.NonogramView;

import java.net.URISyntaxException;

/**
 * MVC controller class for Nonogram main game scene
 */
public class NonogramController {
    /** the view of the scene */
    private NonogramView theView;
    /** the model of the scene */
    private Round theModel;
    /** music player model */
    private MusicPlayer effectPlayer;

    public NonogramController(Round theModel, NonogramView theView) throws URISyntaxException {
        this.theModel = theModel;
        this.theView = theView;
        this.effectPlayer = new MusicPlayer();

        initEventHandler();
        initBindings();
    }

    /**
     * Bindings for the models
     */
    private void initBindings(){
        //bindings for lives
        for (int i = 0; i<theView.getLivesArray().size(); ++i){
            theView.getLivesArray().get(i).visibleProperty().bind(theModel.getLivesValueArray(i));
        }

    }

    /**
     * An internal helper method to initialize the event handlers
     */
    private void initEventHandler() {
        //Get the playing mode selected before setting action
        selectPlayingMode();

        //Set on action for each square clicked
        theView.getGridButton().stream().forEach(button -> {
            button.setOnMouseClicked(event -> {
                // Get the position of the square clicked
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                theModel.setPlayingMode(PLAYING_MODE.SQUARE); //Update the model according to the selected playing mode

                if (theModel.checkValidGuess(row,column)) {
                    Toggle selectedButton = theView.getBtnPlayMode().getSelectedToggle(); //get the currently selected playing mode
                    //Handle each action based on the currently selected playing mode
                    try {
                        if (selectedButton == theView.getBtnChoose()) {
                            handleChooseMode(button, row, column);

                        } else if (selectedButton == theView.getBtnCross()) {
                            handleCrossMode(button, row, column);

                        } else if ((selectedButton == theView.getBtnGetHint()) && (theModel.getHints() >0)) {
                            handleHintMode(button, row, column); //Can only select get hint mode if number of hints left > 0
                        }
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        });

        // check if the game has been won
        theModel.isWinProperty().addListener(((observable, oldValue, newValue) ->{
            GameManager.ROUNDMAP.get(PuzzleFactory.round).set(true); //update the ROUNDMAP if the round has been won
            // Change to game over scene with game winner message
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_WINNER);
            scene.getStylesheets().add(getClass().getResource("/Nonogram.css").toExternalForm());
            // uses choose to get the scene
            theView.getBtnChoose().getScene().setRoot(scene);
        } ));

        // check if the game has been lost (the live value at index 0 = false)
        theModel.getLivesValueArray(0).addListener(((observable, oldValue, newValue) ->{
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_LOSER);
            scene.getStylesheets().add(getClass().getResource("/Nonogram.css").toExternalForm());
            // uses choose to get the scene
            theView.getBtnChoose().getScene().setRoot(scene);
        } ));

        // Get restart button and set on action to restart the game
        Button btnRestart = theView.getBtnRestart();
        btnRestart.setOnAction(event -> {
            try {
                SceneManager.addNewGame();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN); // Set up new scene to play again
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            btnRestart.getScene().setRoot(scene);
        });
    }

    /**
     * Handle when Hint mode is chosen
     * @param button button of playing mode
     * @param row x-coordinates of chosen cell
     * @param column y-coordinates of chosen cell
     */
    private void handleHintMode(Button button, int row, int column) throws URISyntaxException {
        theModel.setPlayingMode(PLAYING_MODE.HINT);
        boolean isColoredSquare = theModel.isColored(row, column);
        //If the selected square is a colored square, then update GUI accordingly
        if (isColoredSquare){
            button.setStyle("-fx-background-color: #185c7a;");
            button.applyCss();
            button.layout();
        } else {
            button.setText("X");
        }

        //Disable get hint button if there are no hints left
        if (theModel.getHints() < 0) {
            theView.getBtnGetHint().setSelected(false);
            theView.getBtnGetHint().disabledProperty();
        } else {
            theView.getBtnGetHint().setText(Integer.toString(theModel.getHints())); //update the number of hints left
        }
    }

    /**
     * Handle when cross mode is chosen
     * @param button button of playing mode
     * @param row x-coordinates of chosen cell
     * @param column y-coordinates of chosen cell
     */
    private void handleCrossMode(Button button, int row, int column) throws URISyntaxException {
        theModel.setPlayingMode(PLAYING_MODE.CROSS);
        //Check if correctly choose the square
        boolean correct = theModel.guessEvaluator(row, column);

        if (correct) {
            button.setText("X");
        } else {
            button.setStyle("-fx-background-color: #185c7a;");
            button.applyCss();
            button.layout();
            effectPlayer.playWrong(); //set up the music effect if player chooses wrong square
        }
    }

    /**
     * Handle when Choose mode is chosen
     * @param button button of playing mode
     * @param row x-coordinates of chosen cell
     * @param column y-coordinates of chosen cell
     */
    private void handleChooseMode(Button button, int row, int column) throws URISyntaxException {
        theModel.setPlayingMode(PLAYING_MODE.SQUARE);
        //Check if correctly choose the square
        boolean correct = theModel.guessEvaluator(row, column);
        if (correct) {
            button.setStyle("-fx-background-color: #185c7a;");
            button.applyCss();
            button.layout();
        } else {
            button.setText("X");
            effectPlayer.playWrong(); //set up the music effect if player chooses wrong square
        }
    }

    /**
     * Add effect showing user which playing mode is currently chosen
     */
    private void selectPlayingMode() {
        theView.isCross().addListener(event -> {
                    theView.getBtnCross().setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
                    theView.getBtnChoose().setStyle(null);
                }
        );

        theView.isChoose().addListener(event -> {
                    theView.getBtnChoose().setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
                    theView.getBtnCross().setStyle(null);
                }
        );
    }
}
    