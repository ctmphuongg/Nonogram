package org.team3.InstructionScene.PlayingModeScene;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main program that initializes all classes for Instructions scene
 */
public class PlayingModeMain extends Application {
    /** the view of the scene */
    private PlayingModeView theView;

    public void init() throws Exception{
        super.init();
        this.theView = new PlayingModeView();
    }


    /**
     * Our standard main program for a JavaFX application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(theView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("/Nonogram.css").toExternalForm());

        primaryStage.setTitle("Nonogram How to Play");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    /**
     * Gets the root and controller attachment, then return the root
     * @return - the root of the scene
     */
    public static Parent getRoot(){
        PlayingModeView theView = new PlayingModeView();
        PlayingModeController theController = new PlayingModeController(theView);
        return theView.getRoot();
    }


}
