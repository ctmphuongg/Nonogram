package org.team3.gameMenu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.team3.view.NonogramView;

public class gameMenuMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private MainMenuView theView;

    public void init() throws Exception{
        super.init();
        this.theView = new MainMenuView();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(theView.getRoot());
//        scene.getStylesheets().add(
//                getClass().getResource("/Nonogram.css").toExternalForm());

        primaryStage.setTitle("Nonogram Main Menu");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }
}
