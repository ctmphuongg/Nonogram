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

/**
 * MVC controller class for Game Over scene
 */
public class GameOverController {
    /** the view of the scene */
    GameOverView theView;

    /**
     * Construct a controller that handles events for Game Over scene
     * @param theView
     */
    public GameOverController(GameOverView theView) {
        this.theView = theView;
        initEventHandler();
    }

    /**
     * An internal helper method to initialize the event handlers
     */
    private void initEventHandler(){
        // Get the play again, play next round, and back to round list labels
        Label lblAgain = theView.getLblAgain();
        Label lblNextRound = theView.getLblNextRound();
        Label lblBacktoRound = theView.getLblBacktoRound();

        // Handle event for play next round label
        lblNextRound.setOnMouseClicked(e->{
            PuzzleFactory.increaseRound(); // Increase the number of round
            try {
                SceneManager.addNewGame();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }

            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN); // Set up new scene for a new round
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

        // Handle event for play again label
        lblAgain.setOnMouseClicked(e->{
            try {
                SceneManager.addNewGame();
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_SCREEN); // Set up new scene to play again
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            lblAgain.getScene().setRoot(scene);
        });

        // Handle event for back to round list label
        lblBacktoRound.setOnMouseClicked(e->{
            Parent scene = SceneManager.getSceneRoot(SceneManager.GAME_ROUND); // Change scene to round list
            scene.getStylesheets().add(
                    getClass().getResource("/Nonogram.css").toExternalForm());
            lblBacktoRound.getScene().setRoot(scene);
        });
    }
}
