/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 25/04/2023
 * Time: 19:47
 *
 * Project: csci205_final_project
 * Package: org.team3.InstructionScene
 * Class: InstructionController
 *
 * Description:
 *
 * *****************************************/
package org.team3.InstructionScene;

import javafx.scene.control.Label;
import org.team3.GameManager.SceneManager;

public class InstructionController {
    private InstructionView theView;

    public InstructionController(InstructionView theView) {
        this.theView = theView;
        initEventHandler();
    }

    public void initEventHandler(){
        // get back to main label and attach root transition event
        Label backToMainlbl = this.theView.getBackToMainlbl();
        backToMainlbl.setOnMouseClicked(event -> {
            backToMainlbl.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.GAME_MENU));

        });
    }
}
