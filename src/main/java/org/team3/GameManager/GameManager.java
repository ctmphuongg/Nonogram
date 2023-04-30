package org.team3.GameManager;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import org.team3.model.PuzzleFactory;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.team3.MusicPlayer;

import java.net.URISyntaxException;

/**
 * Master application for the entire game itself, running this class begins the game and
 * loads the user in the way the game is intended to be played.
 */
public class GameManager extends Application {
    /** Map for controlling rounds */
    public static HashMap<Integer, SimpleBooleanProperty> ROUNDMAP;

    /** Scene manager that instantiates all possible scenes beforehand for performance */
    private SceneManager manager;

    /** stage for different scenes to access */
    public static Stage gamePrimaryStage;

    /** scene for different roots to access */
    public static Scene scene;

    /** background music for game */
    public static MusicPlayer musicPlayer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        ROUNDMAP = new HashMap<>(); //round map that store simple boolean property whether each round has been won
        //populate the round map
        for (int i=0;i<PuzzleFactory.getPuzzleNumber();++i) {
            ROUNDMAP.put(i, new SimpleBooleanProperty(false));
        }
        // creates the default scene
        manager = new SceneManager();
        musicPlayer = new MusicPlayer();

    }

    /**
     * Starts the game
     * @param primaryStage -  stage for the scene to show itself
     */
    @Override
    public void start(Stage primaryStage) throws URISyntaxException {

        // instantiate the gamePrimaryStage for other scenes to access
        gamePrimaryStage = primaryStage;

        // get starting game root
        scene = new Scene(SceneManager.getSceneRoot("game menu"));

        // add styling for this scene
        scene.getStylesheets().add(
                getClass().getResource("/Nonogram.css").toExternalForm());

        // populate the other scenes before user clicks
        SceneManager.populateOtherScenes();

        // Add background music
        musicPlayer.play();

        // set scene to stage
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Nonogram");
        primaryStage.show();

    }


    /**
     * @return Scene - the scene of the game
     */
    public Scene getScene() {
        return scene;
    }
}