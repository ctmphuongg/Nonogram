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

import java.io.File;
import java.net.URL;


public class RoundView {
    private FlowPane root;

    public FlowPane getRoot() { return root;}

    private void initScene (){
        root = new FlowPane(80,30);

        root.setAlignment(Pos.CENTER_LEFT);
        root.setPrefWrapLength(300); // set the preferred width of the pane


        for (int i = 0; i<5; i++){
            Button round = new Button("Round " + (i+1));
            root.getChildren().add(round);

        }
        // Get the number of file in the dataset folder
//        URL resourceUrl = RoundView.class.getResource("/datasets");
//
//
//        // create a File object for the folder
//        File folder = new File(resourceUrl.getPath());
//
//        // get the list of files in the folder
//        File[] files = folder.listFiles();
//
//        // count the number of files
//        int numFiles = files.length;
//
//        System.out.println("Number of files in folder: " + numFiles);




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