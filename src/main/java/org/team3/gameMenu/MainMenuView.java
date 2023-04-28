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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.team3.GameManager.GameManager;

/**
 * MVC view class for Main Menu scene
 */
public class MainMenuView {
    /** Vbox container for root */
    private VBox root;

    /** Background music volume slider */
    private Slider backgroundMusicVolumeSlider;

    /** Label for music volume */
    private Label backgroundMusicVolumeLabel;

    /** Labels for new game, instructions, and exit */
    private Label lblStartGame; Label lblInstructions; Label lblExit;

    /** Vbox container for main menu options */
    private VBox options;

    /** Label for name game */
    private Label greetinglbl;


    /**
     * Initialize the entire Main Menu scene
     */
    private void initScene() {
        // Set up the Vbox container for the root
        root = new VBox();

        // Set up Stackpane for game name and game image
        StackPane greetingPane = new StackPane();
        Image image = new Image("/pic/heart_mainmenu.png");
        double newWidth = 100; // desired width
        double newHeight = 100; // desired height
        Image resizedImage = new Image(image.getUrl(), newWidth, newHeight, true, true);
        ImageView imageView = new ImageView(resizedImage);

        greetinglbl = new Label("Welcome to Nonogram"); // Label for game name
        greetinglbl.setGraphic(imageView);
        greetinglbl.getStyleClass().add("greeting");
        greetingPane.getChildren().addAll(imageView,greetinglbl);

        // Set up Vbox container for main menu options and lebels for each option
        options = new VBox();
        lblStartGame = new Label("PLAY");
        lblInstructions = new Label("HOW TO PLAY");
        lblExit = new Label("EXIT");
        options.getChildren().addAll(lblStartGame, lblInstructions, lblExit);
        options.setAlignment(Pos.CENTER);

        // Add styling to the buttons
        lblStartGame.getStyleClass().add("wide-blue-text");
        lblInstructions.getStyleClass().add("wide-blue-text");
        lblExit.getStyleClass().add("wide-blue-text");

        // Set up background music slider
        backgroundMusicVolumeSlider = new Slider(0.0, 1.0, 0.5);
        backgroundMusicVolumeSlider.setShowTickMarks(false); //Not show tick mark for slider
        backgroundMusicVolumeSlider.setShowTickLabels(false); //Not show label for slider
        backgroundMusicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            GameManager.musicPlayer.setVolume(newValue.doubleValue());
        });

        // Set up music volume label
        backgroundMusicVolumeLabel = new Label("Volume:");
        backgroundMusicVolumeLabel.getStyleClass().add("white-text"); //Add styling for text display for slider

        // Set up Hbox container for music slider and label
        HBox musicSetting = new HBox();
        musicSetting.getStyleClass().add("music_setting");
        musicSetting.getChildren().add(backgroundMusicVolumeLabel);
        musicSetting.getChildren().add(backgroundMusicVolumeSlider);
        musicSetting.setAlignment(Pos.CENTER);

        // add to root
        root.getChildren().addAll(greetingPane,options,musicSetting);

    }

    /**
     * @return start game label
     */
    public Label getLblStartGame() {
        return lblStartGame;
    }

    /**
     * @return how to play label
     */
    public Label getLblInstructions() {
        return lblInstructions;
    }

    /**
     * @return exit game label
     */
    public Label getLblExit() {
        return lblExit;
    }

    /**
     * @return the root of the scene
     */
    public VBox getRoot() {
        return root;
    }



    public MainMenuView() {
        initScene();

    }

}
