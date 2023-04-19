package org.team3.GameOver;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.gameMenu.MainMenuController;
import org.team3.gameMenu.MainMenuView;
import org.team3.model.Round;

public class GameOverMain extends Application {
    private GameOverView theView;
    private GameOverController theController;
    private Round theModel;

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed, but before the
     * start() method is invoked.
     */
    @Override
    public void init() throws Exception {
        super.init();
//        this.theView = new GameOverView();
//        this.theController = new TempConverterController(theModel, theView);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(this.theView.getRoot());
        primaryStage.setTitle("Game Over");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static Parent getRoot(String message){
        GameOverView theView = new GameOverView(message);
        GameOverController theController = new GameOverController(theView);
        return theView.getRoot();
    }
}
