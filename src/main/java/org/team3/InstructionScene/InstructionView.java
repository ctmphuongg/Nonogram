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
package org.team3.InstructionScene;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

public class InstructionView {
    public VBox getRoot() {
        return root;
    }

    private VBox root;

    private Label colorModeText, crossModeText, getHintText;

    private HBox rule1, rule2, rule3;

    private Label playingModelbl;

    private ToggleButton getHintbtn, crossbtn, choosebtn;

    private Label backToMainlbl;



    private void initScene() {
        root = new VBox();
        rule1 = new HBox();
        rule1.setPrefSize(100,50);
        rule2 = new HBox();
        rule2.setPrefSize(100,50);
        rule3 = new HBox();
        rule3.setPrefSize(100,50);

        playingModelbl = new Label("Playing Mode");
        playingModelbl.getStyleClass().add("greeting");


        // Playing mode choose
        choosebtn = new ToggleButton();
        choosebtn.setText("  ");
        choosebtn.getStyleClass().add("choiceColor");
        colorModeText = new Label("Select this button to color a square");
        rule1.getStyleClass().add("wide-blue-text");
        colorModeText.getStyleClass().add("white-text");
        rule1.getChildren().addAll(choosebtn,colorModeText);
        rule1.setAlignment(Pos.CENTER_LEFT);
        rule1.setSpacing(20);

        // Playing mode cross
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

        ImageView btnGetHintImage = new ImageView(new Image(getClass().getResourceAsStream("/pic/lightbulb.png")));
        btnGetHintImage.setFitHeight(30);
        btnGetHintImage.setFitWidth(30);
        getHintbtn = new ToggleButton("3",btnGetHintImage);
        getHintText = new Label("Select this button and a square to get a hint. You have 3 hints each round");
        getHintText.getStyleClass().add("white-text");
        rule3.getStyleClass().add("wide-blue-text");
        rule3.getChildren().addAll(getHintbtn,getHintText);
        rule3.setAlignment(Pos.CENTER_LEFT);
        rule3.setSpacing(20);

        ImageView imageView = new ImageView(new Image("/pic/back.png"));
        backToMainlbl = new Label();
        // Set the size of the image view to fit within the label
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        // Set the image view as the label's graphic
        backToMainlbl.setGraphic(imageView);

        root.getChildren().addAll(playingModelbl,rule1,rule2,rule3,backToMainlbl);

    }

    public InstructionView(){
        initScene();
    }

    public Label getBackToMainlbl() {
        return backToMainlbl;
    }
}
