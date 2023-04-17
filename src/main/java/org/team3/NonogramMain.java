package org.team3;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team3.view.NonogramView;

public class NonogramMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private NonogramView theView;

    @Override
    public void init() throws Exception{
        super.init();
        this.theView = new NonogramView();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(theView.getRoot());
        scene.getStylesheets().add(
                getClass().getResource("/Nonogram.css").toExternalForm());

        primaryStage.setTitle("Nonogram");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static Parent getRoot(){
        NonogramView rootScene = new NonogramView();
        return rootScene.getRoot();
    }
}
