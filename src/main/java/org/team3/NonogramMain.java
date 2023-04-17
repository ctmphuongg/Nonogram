package org.team3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.controller.NonogramController;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;
import org.team3.view.NonogramView;

public class NonogramMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private Round theModel;
    private NonogramView theView;
    private NonogramController theController;

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
    public void start(Stage primaryStage) {
        Scene scene = new Scene(theView.getRoot());

        scene.getStylesheets().add(
                getClass().getResource("/Nonogram.css").toExternalForm());

        this.theController = new NonogramController(theModel, theView);

        primaryStage.setTitle("Nonogram");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

}
