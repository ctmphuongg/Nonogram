package org.team3.RoundScene;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.GameOver.GameOverController;
import org.team3.GameOver.GameOverView;
import org.team3.model.Round;


public class RoundMain extends Application {

    private RoundView theView;

    public static void main(String[] args) {
        launch(args);
    }

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
//        this.theController = new TempConverterController(theModel, theView);
    }

    public static Parent getRoot(){
        RoundView theView = new RoundView();
        RoundController theController = new RoundController(theView);
        return theView.getRoot();
    }
}
