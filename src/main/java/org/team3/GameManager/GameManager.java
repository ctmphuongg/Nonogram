package org.team3.GameManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Master application for the entire game itself, running this class begins the game and
 * loads the user in the way the game is intended to be played.
 */
public class GameManager extends Application {
    /**
     * Global game width, game height
     */
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    // Scene manager that instantiates all possible scenes beforehand for
    // performance
    private SceneManager manager;

    // stage for different scenes to access
    public static Stage gamePrimaryStage;

    // scene for different roots to access
    public static Scene scene;

    // Stylesheet for the game menus
    public static String styleSheet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        // creates the default scene
        manager = new SceneManager();
    }

    /**
     * Starts the game
     * @param primaryStage -  stage for the scene to show itself
     */
    @Override
    public void start(Stage primaryStage) {

        System.out.println("javafx.runtime.version: " + System.getProperties().get("javafx.runtime.version"));
        // instantiate the gamePrimaryStage for other scenes to access
        gamePrimaryStage = primaryStage;

        // get starting game root
        scene = new Scene(SceneManager.getSceneRoot("game menu"));

        // add styling for this scene
        styleSheet = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(styleSheet);

        // populate the other scenes before user clicks
        SceneManager.populateOtherScenes();

        // set scene to stage
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Rule.Baba is Us");
        primaryStage.show();

    }


    /**
     * @return Scene - the scene of the game
     */
    public Scene getScene() {
        return scene;
    }
}