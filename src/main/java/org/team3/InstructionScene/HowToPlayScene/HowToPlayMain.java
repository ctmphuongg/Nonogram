package org.team3.InstructionScene.HowToPlayScene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.InstructionScene.InstructionView;

public class HowToPlayMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private HowToPlayView theView;

    public void init() throws Exception{
        super.init();
        this.theView = new HowToPlayView();
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
}
