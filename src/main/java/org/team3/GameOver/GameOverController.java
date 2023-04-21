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
import org.team3.model.PuzzleFactory;

public class GameOverController {
    GameOverView theView;

    public GameOverController(GameOverView theView) {
        this.theView = theView;
        initEventHandler();
    }

    private void initEventHandler(){
        Button btnAgain = theView.getBtnAgain();
        Button btnNextRound = theView.getBtnNextRound();
        Button btnRound = theView.getBtnRound();
        btnNextRound.setOnAction(e->{
            PuzzleFactory.increaseRound();
            SceneManager.addNewGame();
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_MENU);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            SceneManager.addNewGame();
            btnNextRound.getScene().setRoot(scene);
        });

        btnAgain.setOnAction(e->{
            SceneManager.addNewGame();
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            btnAgain.getScene().setRoot(scene);
        });

        btnRound.setOnAction(e->{
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_ROUND);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            btnRound.getScene().setRoot(scene);
        });
    }
}
