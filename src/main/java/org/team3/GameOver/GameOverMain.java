package org.team3.GameOver;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.gameMenu.MainMenuController;
import org.team3.gameMenu.MainMenuView;
import org.team3.model.Round;

/**
 * The main program that initializes all classes for Game Over scene
 */
public class GameOverMain extends Application {
    /** the view of the scene */
    private GameOverView theView;

    /** the controller of the scene */
    private GameOverController theController;

    /** the model of the scene */
    private Round theModel;

    @Override
    public void init() throws Exception {
        super.init();
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
        Scene scene = new Scene(this.theView.getRoot());
        primaryStage.setTitle("Nonogram");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Gets the root and controller attachment, then return the root
     * @param - the message to be displayed and roundState - whether win or not
     * @return - the root of the scene
     */
    public static Parent getRoot(String message,boolean roundState){
        GameOverView theView = new GameOverView(message,roundState);
        GameOverController theController = new GameOverController(theView);
        return theView.getRoot();
    }
}
