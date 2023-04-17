package org.team3.GameResult;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.model.Round;

public class GameResultMain extends Application {
    private GameResultView theView;
    private GameResultController theController;
    private Round theModel;

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed, but before the
     * start() method is invoked.
     */
    @Override
    public void init() throws Exception {
        super.init();
        this.theView = new GameResultView();
//        this.theController = new TempConverterController(theModel, theView);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(this.theView.getRoot());
//        scene.getStylesheets().add(getClass().getResource("/lab10/tempconvertermvc.css").toExternalForm());
        primaryStage.setTitle("Game Result");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();


    }
}
