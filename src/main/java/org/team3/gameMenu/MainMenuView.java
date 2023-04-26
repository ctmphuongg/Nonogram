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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.team3.GameManager.GameManager;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;
import org.w3c.dom.Text;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;

import static java.awt.Color.white;
import static java.awt.SystemColor.text;

public class MainMenuView {
    public VBox getRoot() {
        return root;
    }

    private VBox root;
    private Button newGameButton;
    private Slider backgroundMusicVolumeSlider;

    private Label newGamelbl;
    private Label instructionslbl;

    private Label exitlbl;

    private VBox options;

    private void initScene() {

        root = new VBox();
        Label greetinglbl = new Label("Welcome to Nonogram");
        StackPane greetingPane = new StackPane();
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/pic/heart_mainmenu.png")));
        greetinglbl.setGraphic(imageView);
        greetinglbl.getStyleClass().add("greeting");
        greetingPane.getChildren().addAll(imageView,greetinglbl);


        options = new VBox();
        newGamelbl = new Label("PLAY");
        instructionslbl = new Label("HOW TO PLAY");
        exitlbl = new Label("EXIT");
        options.getChildren().addAll(newGamelbl,instructionslbl,exitlbl);
        options.setAlignment(Pos.CENTER);

        // add styling to the buttons
        newGamelbl.getStyleClass().add("wide-blue-text");
        instructionslbl.getStyleClass().add("wide-blue-text");
        exitlbl.getStyleClass().add("wide-blue-text");

        // add to root
        root.getChildren().addAll(greetingPane,options);

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

    public Label getNewGamelbl() {
        return newGamelbl;
    }

    public Label getInstructionslbl() {
        return instructionslbl;
    }

    public Label getExitlbl() {
        return exitlbl;
    }


    public MainMenuView() {
        initScene();

    }

}
