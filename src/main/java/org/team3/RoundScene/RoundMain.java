package org.team3.RoundScene;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main program that initializes all classes for Round lists board scene
 */
public class RoundMain extends Application {

    /** the view of the model */
    private RoundView theView;

    /**
     * Our standard main program for a JavaFX application
     * @param args
     */
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(this.theView.getRoot());
        scene.getStylesheets().add(getClass().getResource("/Nonogram.css").toExternalForm());
        primaryStage.setTitle("Round view");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
        this.theView = new RoundView();
    }

    /**
     * Gets the root and controller attachment, then return the root
     * @return - the root of the scene
     */
    public static Parent getRoot(){
        RoundView theView = new RoundView();
        RoundController theController = new RoundController(theView);
        return theView.getRoot();
    }
}
