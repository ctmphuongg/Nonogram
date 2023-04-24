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
package org.team3.gameMenu;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.team3.GameManager.SceneManager;

public class MainMenuController {
    private MainMenuView theView;

    public MainMenuController(MainMenuView theView) {
        this.theView = theView;
        initEventHandler();
    }

    public void initEventHandler(){
        // get new game label and attach root transition event
        Label newGamelbl = theView.getNewGamelbl();

        newGamelbl.setOnMouseClicked(e->{
            newGamelbl.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.GAME_ROUND));
        });

        // get view label and attach root transition event
//        Label instructionlbl = theView.getInstructionslbl();
//
//        instructionlbl.setOnMouseClicked(e->{
//            instructionlbl.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.INSTRUCTIONS));
//        });


        // get exit label , and exit when clicked
        Label exitlbl = theView.getExitlbl();

        exitlbl.setOnMouseClicked(e->{
            Platform.exit();
        });


//        Button button = theView.getNewGameButton();
//
//
//        button.setOnAction(e-> {
//            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_ROUND);
//            scene.getStylesheets().add(
//                    getClass().getResource("/Nonogram.css").toExternalForm());
//            button.getScene().setRoot(scene);
//        });
    }
}
