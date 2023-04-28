package org.team3.GameResultScene;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.model.RoundFactory;

/**
 * The main program that initializes all classes for Game Over scene
 */
public class GameResultMain extends Application {
    /** the view of the scene */
    private GameResultView theView;

    /** the controller of the scene */
    private GameResultController theController;

    /** the model of the scene */
    private RoundFactory theModel;

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
        GameResultView theView = new GameResultView(message,roundState);
        GameResultController theController = new GameResultController(theView);
        return theView.getRoot();
    }
}
