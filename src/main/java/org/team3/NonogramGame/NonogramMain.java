package org.team3.NonogramGame;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.NonogramGame.controller.NonogramController;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;

import java.net.URISyntaxException;

public class NonogramMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static Round theModel;
    private static NonogramView theView;
    private static NonogramController theController;
    private PuzzleFactory puzzleSpace;

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

    //
    public static Parent getRoot() throws URISyntaxException {

        PuzzleFactory puzzleSpace = new PuzzleFactory();
        theModel = new Round(puzzleSpace);
        theModel.initNewRound();
        theModel.displayMatrix();

        theView = new NonogramView(theModel);
        theController = new NonogramController(theModel, theView);
        return theView.getRoot();
    }
}
