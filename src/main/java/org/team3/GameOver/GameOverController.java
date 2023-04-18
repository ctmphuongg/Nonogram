/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 17/04/2023
 * Time: 09:15
 *
 * Project: csci205_final_project
 * Package: org.team3.GameResult
 * Class: GameResultController
 *
 * Description:
 *
 * *****************************************/
package org.team3.GameOver;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.team3.GameManager.SceneManager;

public class GameOverController {
    GameOverView theView;

    public GameOverController(GameOverView theView) {
        this.theView = theView;
        initEventHandler();
    }

    private void initEventHandler(){
        Button btnYes = theView.getBtnYes();
        Button btnNo = theView.getBtnNo();

        btnYes.setOnAction(e->{
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            SceneManager.addNewGame();
            btnYes.getScene().setRoot(scene);
        });

        btnNo.setOnAction(e->{
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_MENU);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            btnNo.getScene().setRoot(scene);
        });
    }
}
