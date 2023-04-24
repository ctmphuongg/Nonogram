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
import javafx.scene.control.Label;
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



    private void initScene() {
        root = new VBox();
        rule1 = new HBox();
        rule2 = new HBox();
        rule3 = new HBox();

        playingModelbl = new Label("Playing Mode");
        playingModelbl.getStyleClass().add("greeting");

        ImageView colorMode = new ImageView( new Image(getClass().getResource("/pic/colorMode.png").toExternalForm(),40,40,false,false));
        colorModeText = new Label("Select this button to color a square");
        rule1.getStyleClass().add("wide-blue-text");
        colorModeText.getStyleClass().add("white-text");
        rule1.getChildren().addAll(colorMode,colorModeText);
        rule1.setAlignment(Pos.CENTER_LEFT);

        ImageView crossMode = new ImageView( new Image(getClass().getResource("/pic/crossMode.png").toExternalForm(),40,40,false,false));
        crossModeText = new Label("Select this button to cross a square");
        rule2.getStyleClass().add("wide-blue-text");
        crossModeText.getStyleClass().add("white-text");
        rule2.getChildren().addAll(crossMode,crossModeText);
        rule2.setAlignment(Pos.CENTER_LEFT);

        ImageView getHintMode = new ImageView( new Image(getClass().getResource("/pic/getHintMode.png").toExternalForm(),40,40,false,false));
        getHintText = new Label("Select this button and a square to get a hint. You have 3 hints each round");
        getHintText.getStyleClass().add("white-text");
        rule3.getStyleClass().add("wide-blue-text");
        rule3.getChildren().addAll(getHintMode,getHintText);
        rule3.setAlignment(Pos.CENTER_LEFT);


        root.getChildren().addAll(playingModelbl,rule1,rule2,rule3);

    }

    public InstructionView(){
        initScene();
    }
}
