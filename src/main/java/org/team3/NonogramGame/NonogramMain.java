package org.team3.NonogramGame;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.NonogramGame.controller.NonogramController;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;

import java.net.URISyntaxException;

/**
 * The main program that initializes all classes for Nonogram mane game scene
 */
public class NonogramMain extends Application {

    /**
     * Our standard main program for a JavaFX application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** MVC classes for the scene */
    private static Round theModel;
    private static NonogramView theView;
    private static NonogramController theController;

    @Override
    public void init() throws Exception{
        super.init();
        PuzzleFactory puzzleSpace = new PuzzleFactory();
        theModel = new Round(puzzleSpace);
        theModel.initNewRound();
        theModel.displayMatrix();

        this.theView = new NonogramView(theModel);

    }

    @Override
    public void start(Stage primaryStage) throws URISyntaxException {
        Scene scene = new Scene(theView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("/Nonogram.css").toExternalForm());

        this.theController = new NonogramController(theModel, theView);

        primaryStage.setTitle("Nonogram");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Gets the root and controller attachment, then return the root
     * @return - the root of the level menu
     */
    public static Parent getRoot() throws URISyntaxException {

        PuzzleFactory puzzleSpace = new PuzzleFactory();
        theModel = new Round(puzzleSpace);
        theModel.initNewRound(); // create new round

        theView = new NonogramView(theModel);
        theController = new NonogramController(theModel, theView);
        return theView.getRoot();
    }
}
