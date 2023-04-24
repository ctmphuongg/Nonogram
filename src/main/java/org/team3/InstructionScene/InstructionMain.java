package org.team3.InstructionScene;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.gameMenu.MainMenuView;

public class InstructionMain extends Application {
    private InstructionView theView;

    public void init() throws Exception{
        super.init();
        this.theView = new InstructionView();
    }

    public static void main(String[] args) {
        launch(args);
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
