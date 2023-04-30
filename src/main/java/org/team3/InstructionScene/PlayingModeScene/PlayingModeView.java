/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 24/04/2023
 * Time: 07:56
 *
 * Project: csci205_final_project
 * Package: org.team3.InstructionScene
 * Class: InstructionView
 *
 * Description:
 *
 * *****************************************/
package org.team3.InstructionScene.PlayingModeScene;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * MVC view class for Instructions scene
 */
public class PlayingModeView {

    /** Vbox container for root */
    private VBox root;

    /** Labels for instructions on each playing mode */
    private Label colorModeText, crossModeText, getHintText;

    /** Label for instructions on playing mode */
    private Label playingModelbl;

    /** Demo buttons of playing modes to be displayed */
    private ToggleButton getHintbtn, crossbtn, choosebtn;

    /** Label for back to main menu */
    private Label backToMainlbl;

    /** Label for next instructions */
    private Label nextInstructionslbl;



    /**
     * Initialize the entire Game Instructions scene
     */
    private void initScene() {
        // Set up containers for each rule
        root = new VBox();
        HBox rule1 = new HBox();
        rule1.setPrefSize(100,50);
        rule1.setMaxSize(500,50);
        HBox rule2 = new HBox();
        rule2.setPrefSize(100,50);
        rule2.setMaxSize(500,50);
        HBox rule3 = new HBox();
        rule3.setPrefSize(100,50);
        rule3.setMaxSize(500,50);

        // Set up heading label for the scene
        playingModelbl = new Label("Playing Mode Instructions");
        playingModelbl.getStyleClass().add("greeting");


        // Rule 1: Playing mode choose
        choosebtn = new ToggleButton();
        choosebtn.setText("  ");
        choosebtn.getStyleClass().add("choiceColor");
        colorModeText = new Label("Select this button to color a square");
        rule1.getStyleClass().add("wide-blue-text");
        colorModeText.getStyleClass().add("white-text");
        rule1.getChildren().addAll(choosebtn,colorModeText);
        rule1.setAlignment(Pos.CENTER_LEFT);
        rule1.setSpacing(20);

        // Rule 2: Playing mode cross
        crossbtn = new ToggleButton();
        crossbtn.setText("X");
        crossbtn.getStyleClass().add("choiceX");
        crossbtn.disabledProperty();
        crossModeText = new Label("Select this button to cross a square");
        rule2.getStyleClass().add("wide-blue-text");
        crossModeText.getStyleClass().add("white-text");
        rule2.getChildren().addAll(crossbtn,crossModeText);
        rule2.setAlignment(Pos.CENTER_LEFT);
        rule2.setSpacing(20);

        //Rule 3: get hint
        ImageView btnGetHintImage = new ImageView(new Image(getClass().getResourceAsStream("/pic/lightbulb.png")));
        btnGetHintImage.setFitHeight(30);
        btnGetHintImage.setFitWidth(30);
        getHintbtn = new ToggleButton("3",btnGetHintImage);
        getHintText = new Label("Select this button and a square to get a hint");
        getHintText.getStyleClass().add("white-text");
        rule3.getStyleClass().add("wide-blue-text");
        rule3.setHgrow(getHintText, Priority.ALWAYS);
        rule3.getChildren().addAll(getHintbtn,getHintText);
        rule3.setAlignment(Pos.CENTER_LEFT);
        rule3.setSpacing(20);

        //Container for next and back label
        HBox lblContainer = new HBox();
        lblContainer.setAlignment(Pos.BOTTOM_CENTER);
        lblContainer.setSpacing(15);

        //Back to main menu label
        ImageView backImageView = new ImageView(new Image("/pic/back.png"));
        backToMainlbl = new Label();
        backImageView.setFitWidth(30); // Set the size of the image view to fit within the label
        backImageView.setFitHeight(30);
        backToMainlbl.setGraphic(backImageView); // Set the image view as the label's graphic

        //Next to next instructions
        ImageView nextImageView = new ImageView(new Image("/pic/next.png"));
        nextInstructionslbl = new Label();
        nextImageView.setFitWidth(30); // Set the size of the image view to fit within the label
        nextImageView.setFitHeight(30);
        nextInstructionslbl.setGraphic(nextImageView); // Set the image view as the label's graphic

        lblContainer.getChildren().addAll(backToMainlbl,nextInstructionslbl);

        root.getChildren().addAll(playingModelbl,rule1,rule2,rule3,lblContainer);

    }

    /**
     * @return the root of the scene
     */
    public VBox getRoot() {
        return root;
    }

    /**
     * @return back to main menu label
     */
    public Label getBackToMainlbl() {
        return backToMainlbl;
    }

    /**
     * @return next instructions label
     */
    public Label getNextInstructionslbl() {return nextInstructionslbl;}



    public PlayingModeView(){
        initScene();
    }

}
