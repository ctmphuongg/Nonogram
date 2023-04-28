package org.team3.gameMenu;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The main program that initializes all classes for Main Menu scene
 */
public class MainMenuMain extends Application {
    /**
     * Our standard main program for a JavaFX application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** The view of the scene */
    private MainMenuView theView;

    public void init() throws Exception{
        super.init();
        this.theView = new MainMenuView();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(theView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("/Nonogram.css").toExternalForm());

        primaryStage.setTitle("Nonogram");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    /**
     * Gets the root and controller attachment, then return the root
     * @return - the root of the level menu
     */
    public static Parent getRoot(){
        MainMenuView theView = new MainMenuView();
        MainMenuController theController = new MainMenuController(theView);
        return theView.getRoot();
    }

}
