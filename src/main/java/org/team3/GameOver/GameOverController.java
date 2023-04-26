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
import javafx.scene.control.Label;
import org.team3.GameManager.SceneManager;
import org.team3.model.PuzzleFactory;

import java.net.URISyntaxException;

public class GameOverController {
    GameOverView theView;

    public GameOverController(GameOverView theView) {
        this.theView = theView;
        initEventHandler();
    }

    private void initEventHandler(){
        Label lblAgain = theView.getLblAgain();

        Label lblNextRound = theView.getLblNextRound();
        Label lblBacktoRound = theView.getLblBacktoRound();

        lblNextRound.setOnMouseClicked(e->{
            PuzzleFactory.increaseRound();
            try {
                SceneManager.addNewGame();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            try {
                SceneManager.addNewGame();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            lblNextRound.getScene().setRoot(scene);
            try {
                SceneManager.addNewGame();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            lblNextRound.getScene().setRoot(scene);
        });

        lblAgain.setOnMouseClicked(e->{
            try {
                SceneManager.addNewGame();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            lblAgain.getScene().setRoot(scene);
        });

        lblBacktoRound.setOnMouseClicked(e->{
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_ROUND);
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            lblBacktoRound.getScene().setRoot(scene);
        });
    }
}
