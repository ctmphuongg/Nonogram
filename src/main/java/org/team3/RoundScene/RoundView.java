/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 *
 * Name: NGA VU
 * Section: 01
 * Date: 4/17/2023
 * Time: 9:46 AM
 *
 * Project: csci205_final_project
 * Package: org.team3.RoundScene
 * Class: RoundView
 *
 * Description:
 *
 * *****************************************/
package org.team3.RoundScene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.team3.model.PuzzleFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

/**
 * MVC view class for Round lists board scene
 */
public class RoundView {
    /** Vbox container for root */
    private VBox root;

    /** Flowpane container for all round buttons */
    private FlowPane roundButtons;

    /** Arraylist of buttons for each round */
    private ArrayList<Button> listRoundButtons;

    /** Back to main menu label */
    private Label backToMainMenuLbl;


    private void initScene (){
        //Set up the Vbox container for the root
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));



        //Set up the Flowpane wrapper for all buttons
        roundButtons = new FlowPane(80,30);
        roundButtons.setAlignment(Pos.CENTER);
        listRoundButtons = new ArrayList<>();
        roundButtons.setPrefWrapLength(300); // set the preferred width of the pane


        for (int i = 0; i < PuzzleFactory.getPuzzleNumber(); i++){
            Button round = new Button();
            round.getStyleClass().add("round-chosen-btn");
            this.listRoundButtons.add(round); //Add round button to the arraylist

            // Make the button to have star shape
            StackPane roundBtnImage = new StackPane();
            Label roundBtnLbl = new Label("Round " + (i+1));
            roundBtnLbl.setPrefWidth(93);
            roundBtnLbl.setPrefHeight(93);
            roundBtnLbl.getStyleClass().add("round-btn-lbl");
            ImageView imageView = new ImageView(new Image("pic/star_original.png"));
            imageView.setFitWidth(130);
            imageView.setFitHeight(130);
            roundBtnImage.getChildren().addAll(imageView,roundBtnLbl);

            round.setStyle("-fx-background-color: transparent;");
            round.setGraphic(roundBtnImage);

            //Add button to the scene
            roundButtons.getChildren().add(round);
        }

        root.getChildren().add(roundButtons);

        ImageView imageView = new ImageView(new Image("/pic/back.png"));
        backToMainMenuLbl = new Label();
        // Set the size of the image view to fit within the label
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        // Set the image view as the label's graphic
        backToMainMenuLbl.setGraphic(imageView);
        root.getChildren().add(backToMainMenuLbl);

    }



    /**
     * @return the root of the scene
     */
    public VBox getRoot() { return root;}

    /**
     * @param index - the position of the round button
     * @return the round button at position index
     */
    public Button buttonRoundAtIndex(int index){return listRoundButtons.get(index);}

    /**
     * @return label to back to main menu
     */
    public Label getBackToMainMenuLbl() {return backToMainMenuLbl;}



    public RoundView() {
        initScene();
    }


}