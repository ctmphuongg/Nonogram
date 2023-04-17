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

package org.team3.controller;

import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import org.team3.model.Nonogram;
import org.team3.model.PLAYING_MODE;
import org.team3.model.Round;
import org.team3.view.NonogramView;

public class NonogramController {
    private NonogramView theView;
    private Round theModel;

    public NonogramController(Round theModel, NonogramView theView) {
        this.theModel = theModel;
        this.theView = theView;

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

        theView.getGridButton().stream().forEach(button -> {
            button.setOnMouseClicked(event -> {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                theModel.setPlayingMode(PLAYING_MODE.SQUARE);
                if (theModel.checkValidGuess(row,column)) {
                    Toggle selectedButton = theView.getPlayMode().getSelectedToggle();


                    if (selectedButton == theView.getChoose()) {
                        theModel.setPlayingMode(PLAYING_MODE.SQUARE);
                        boolean correct = theModel.guessEvaluator(row, column);

                        if (correct) {
                            button.setStyle("-fx-background-color: #185c7a;");
                            button.applyCss();
                            button.layout();
                        } else {
                            button.setText("X");
                        }


                    } else if (selectedButton == theView.getCross()) {
                        theModel.setPlayingMode(PLAYING_MODE.CROSS);
                        boolean correct = theModel.guessEvaluator(row, column);

                        if (correct) {
                            button.setText("X");
                        } else {
                            button.setStyle("-fx-background-color: #185c7a;");
                            button.applyCss();
                            button.layout();
                        }
                    } else if ((selectedButton == theView.getGetHint()) && (theModel.getHints() >0)) {
                        theModel.setPlayingMode(PLAYING_MODE.HINT);
                        boolean isColoredSquare = theModel.isColored(row,column);
                        if (isColoredSquare){
                            button.setStyle("-fx-background-color: #185c7a;");
                            button.applyCss();
                            button.layout();
                        } else {
                            button.setText("X");
                        }


                        if (theModel.getHints() <0) {
                            theView.getGetHint().setSelected(false);
                            theView.getGetHint().disabledProperty();
                        } else {
                            theView.getGetHint().setText(Integer.toString(theModel.getHints()));
                        }


                    }


                }
            });
        });
    }
}
    