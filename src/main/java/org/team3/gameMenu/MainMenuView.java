/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 15/04/2023
 * Time: 16:09
 *
 * Project: csci205_final_project
 * Package: org.team3.gameMenu
 * Class: MainMenuView
 *
 * Description:
 *
 * *****************************************/
package org.team3.gameMenu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.team3.GameManager.GameManager;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;
import org.w3c.dom.Text;

public class MainMenuView {
    public VBox getRoot() {
        return root;
    }

    private VBox root;
    private Button newGameButton;
    private Slider backgroundMusicVolumeSlider;

    private void initScene() {

        root = new VBox();
        Label greeting = new Label("Welcome to Nonogram");
        greeting.getStyleClass().add("greeting");


        root.getChildren().add(greeting);
        newGameButton = new Button("New game");
        root.getChildren().add(newGameButton);

        backgroundMusicVolumeSlider = new Slider(0.0, 1.0, 0.5);
        backgroundMusicVolumeSlider.setShowTickMarks(true);
        backgroundMusicVolumeSlider.setShowTickLabels(true);
        backgroundMusicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            GameManager.musicPlayer.setVolume(newValue.doubleValue());
        });

        Label backgroundMusicVolumeLabel = new Label("Volume:");

        HBox musicSetting = new HBox();
        musicSetting.getChildren().add(backgroundMusicVolumeLabel);
        musicSetting.getChildren().add(backgroundMusicVolumeSlider);
        musicSetting.setAlignment(Pos.CENTER);
        root.getChildren().add(musicSetting);

    }

    public MainMenuView() {
        initScene();

    }

    public Button getNewGameButton() {
        return newGameButton;
    }
}
