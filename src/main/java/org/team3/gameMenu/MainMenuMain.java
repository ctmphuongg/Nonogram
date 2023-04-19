package org.team3.gameMenu;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuMain extends Application {

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
        scene.getStylesheets().add(
                getClass().getResource("/MainMenu.css").toExternalForm());

        primaryStage.setTitle("Nonogram Main Menu");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    public static Parent getRoot(){
        MainMenuView theView = new MainMenuView();
        MainMenuController theController = new MainMenuController(theView);
        return theView.getRoot();
    }

}
