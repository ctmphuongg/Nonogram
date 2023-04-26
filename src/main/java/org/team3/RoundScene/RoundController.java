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
package org.team3.RoundScene;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.team3.GameManager.GameManager;
import org.team3.GameManager.SceneManager;
import org.team3.model.PuzzleFactory;

import java.net.URISyntaxException;

public class RoundController {
    RoundView theView;

    public RoundController(RoundView theView) {
        this.theView = theView;
        InitEventHandler();
    }

    private void InitEventHandler(){
        for (int i=0;i<PuzzleFactory.getPuzzleNumber();++i){
            int k = i;
            theView.buttonRoundAtIndex(i).setOnAction(event -> {
                PuzzleFactory.setRound(k);
                try {
                    SceneManager.addNewGame();
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
                Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN);
                scene.getStylesheets().add(
                        getClass().getResource("/Nonogram.css").toExternalForm());
                theView.buttonRoundAtIndex(k).getScene().setRoot(scene);
            });
            GameManager.ROUNDMAP.get(i).addListener(((observable, oldValue, newValue) -> {
                theView.buttonRoundAtIndex(k).setStyle("-fx-background-color: white");
            }));
        }

        Label backToMainMenuLbl = theView.getBackToMainMenuLbl();
        backToMainMenuLbl.setOnMouseClicked(event -> {
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_MENU);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            backToMainMenuLbl.getScene().setRoot(scene);
        });

    }
}
