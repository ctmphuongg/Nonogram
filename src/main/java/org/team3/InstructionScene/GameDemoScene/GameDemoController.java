/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 29/04/2023
 * Time: 20:26
 *
 * Project: csci205_final_project
 * Package: org.team3.InstructionScene.GameDemoScene
 * Class: GameDemoController
 *
 * Description:
 *
 * *****************************************/
package org.team3.InstructionScene.GameDemoScene;

import javafx.scene.control.Label;
import org.team3.GameManager.SceneManager;

public class GameDemoController {
    /** the view of the scene */
    private GameDemoView theView;

    public GameDemoController(GameDemoView theView) {
        this.theView = theView;
        initEventHandler();
    }

    /**
     * An internal helper method to initialize the event handlers
     */
    public void initEventHandler(){
        // get back to playing mode instructions label and attach root transition event
        Label lblBack = theView.getLblBack();
        lblBack.setOnMouseClicked(event -> {
            lblBack.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.GAME_PlAYING_MODE_RULE));
        });

        // get back to playing mode instructions label and attach root transition event
        Label lblPlay = theView.getLblPlay();
        lblPlay.setOnMouseClicked(event -> {
            lblPlay.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.GAME_ROUND));
        });




    }
}
