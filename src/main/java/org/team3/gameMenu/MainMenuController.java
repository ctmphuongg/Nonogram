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

import javafx.scene.control.Button;
import org.team3.GameManager.SceneManager;
import org.team3.NonogramMain;

public class MainMenuController {
    private MainMenuView theView;

    public MainMenuController(MainMenuView theView) {
        this.theView = theView;
        initEventHandler();
    }

    public void initEventHandler(){
        Button button = theView.getNewGameButton();
        button.setOnAction(e->{
            button.getScene().setRoot(SceneManager.getSceneRoot(SceneManager.GAME_SCREEN));
        });
    }
}
