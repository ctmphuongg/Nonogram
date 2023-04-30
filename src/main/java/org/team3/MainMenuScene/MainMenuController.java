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
package org.team3.MainMenuScene;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.team3.GameManager.SceneManager;

/**
 * MVC controller class for Main Menu scene
 */
public class MainMenuController {

    /** the view of the scene */
    private MainMenuView theView;

    /**
     * Construct a controller that handles events for Main Menu scene
     * @param theView
     */
    public MainMenuController(MainMenuView theView) {
        this.theView = theView;
        initEventHandler();
    }

    /**
     * An internal helper method to initialize the event handlers
     */
    public void initEventHandler(){
        // Get new game label and attach root transition event
        Label newGamelbl = theView.getLblStartGame();
        newGamelbl.setOnMouseClicked(e->{
            newGamelbl.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.GAME_ROUND));
        });

        // Get view label and attach root transition event
        Label instructionlbl = theView.getLblInstructions();
        instructionlbl.setOnMouseClicked(e->{
            instructionlbl.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.GAME_PlAYING_MODE_RULE));
        });

        // Get exit label , and exit when clicked
        Label exitlbl = theView.getLblExit();
        exitlbl.setOnMouseClicked(e->{
            Platform.exit();
        });

    }
}
