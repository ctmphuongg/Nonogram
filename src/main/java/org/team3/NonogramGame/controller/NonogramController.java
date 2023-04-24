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
import org.team3.model.Nonogram;
import org.team3.model.PLAYING_MODE;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;
import org.team3.NonogramGame.NonogramView;

import java.net.URISyntaxException;

public class NonogramController {
    private NonogramView theView;
    private Round theModel;
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

    private void initEventHandler() {
        selectPlayingMode();

        theView.getGridButton().stream().forEach(button -> {
            button.setOnMouseClicked(event -> {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                theModel.setPlayingMode(PLAYING_MODE.SQUARE);
                if (theModel.checkValidGuess(row,column)) {
                    Toggle selectedButton = theView.getPlayMode().getSelectedToggle();
                    try {
                        if (selectedButton == theView.getChoose()) {
                            handleChooseMode(button, row, column);

                        } else if (selectedButton == theView.getCross()) {
                            handleCrossMode(button, row, column);

                        } else if ((selectedButton == theView.getGetHint()) && (theModel.getHints() >0)) {
                            handleHintMode(button, row, column);
                        }
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        });

        // check if the game has been won
        theModel.isWinProperty().addListener(((observable, oldValue, newValue) ->{
            GameManager.ROUNDMAP.get(PuzzleFactory.round).set(true);
            System.out.println(GameManager.ROUNDMAP.get(PuzzleFactory.round).getValue());
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_WINNER);
            scene.getStylesheets().add(getClass().getResource("/Nonogram.css").toExternalForm());
            // uses choose to get the scene
            theView.getChoose().getScene().setRoot(scene);
        } ));

        // check if the game has been lost (the live value at index 0 = false)
        theModel.getLivesValueArray(0).addListener(((observable, oldValue, newValue) ->{
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_LOSER);
            scene.getStylesheets().add(getClass().getResource("/Nonogram.css").toExternalForm());
            // uses choose to get the scene
            theView.getChoose().getScene().setRoot(scene);
        } ));

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
        if (isColoredSquare){
            button.setStyle("-fx-background-color: #185c7a;");
            button.applyCss();
            button.layout();
        } else {
            button.setText("X");
            effectPlayer.playWrong();
        }


        if (theModel.getHints() < 0) {
            theView.getGetHint().setSelected(false);
            theView.getGetHint().disabledProperty();
        } else {
            theView.getGetHint().setText(Integer.toString(theModel.getHints()));
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
        boolean correct = theModel.guessEvaluator(row, column);

        if (correct) {
            button.setText("X");
        } else {
            button.setStyle("-fx-background-color: #185c7a;");
            button.applyCss();
            button.layout();
            effectPlayer.playWrong();
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
        boolean correct = theModel.guessEvaluator(row, column);

        if (correct) {
            button.setStyle("-fx-background-color: #185c7a;");
            button.applyCss();
            button.layout();
        } else {
            button.setText("X");
            effectPlayer.playWrong();
        }
    }

    /**
     * Add effect showing user which playing mode is currently chosen
     */
    private void selectPlayingMode() {
        theView.isCross().addListener(event -> {
                    theView.getCross().setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
                    theView.getChoose().setStyle(null);
                }
        );

        theView.isChoose().addListener(event -> {
                    theView.getChoose().setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
                    theView.getCross().setStyle(null);
                }
        );
    }
}
    