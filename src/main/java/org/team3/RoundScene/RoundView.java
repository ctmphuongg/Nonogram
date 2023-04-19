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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.team3.model.PuzzleFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;


public class RoundView {
    private FlowPane root;
    private ArrayList<Button> listRoundButtons;

    public FlowPane getRoot() { return root;}

    private void initScene (){
        root = new FlowPane(80,30);
        listRoundButtons = new ArrayList<>();
        root.setAlignment(Pos.CENTER_LEFT);
        root.setPrefWrapLength(300); // set the preferred width of the pane


        for (int i = 0; i < PuzzleFactory.getPuzzleNumber(); i++){
            Button round = new Button("Round " + (i+1));
            this.listRoundButtons.add(round);
            root.getChildren().add(round);
        }
    }

    public Button buttonRoundAtIndex(int index){
        return listRoundButtons.get(index);
    }


    private void initStyling(){
        root.setAlignment(Pos.TOP_LEFT);
        root.setPadding(new Insets(10));
    }

    private void initEventHandler(){

    }

    public RoundView() {
        initScene();
        initStyling();
        initEventHandler();
    }


}