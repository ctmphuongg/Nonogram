package org.team3.InstructionScene.GameDemoScene;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameDemoMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private GameDemoView theView;

    public void init() throws Exception{
        super.init();
        this.theView = new GameDemoView();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(theView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("/Nonogram.css").toExternalForm());

        primaryStage.setTitle("Nonogram How to Play");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    /**
     * Gets the root and controller attachment, then return the root
     * @return - the root of the scene
     */
    public static Parent getRoot(){
        GameDemoView theView = new GameDemoView();
        GameDemoController theController = new GameDemoController(theView);
        return theView.getRoot();
    }
}
