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
import javafx.scene.layout.VBox;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;
import org.w3c.dom.Text;

import static java.awt.Color.white;
import static java.awt.SystemColor.text;

public class MainMenuView {
    public VBox getRoot() {
        return root;
    }

    private VBox root;
    private Button newGameButton;

    private Label newGamelbl, instructionslbl, exitlbl;

    private VBox options;

    private void initScene() {

        root = new VBox();
        Label greeting = new Label("Welcome to Nonogram");
        greeting.getStyleClass().add("greeting");


//        root.getChildren().add(greeting);
//        newGameButton = new Button("New game");
//        root.getChildren().add(newGameButton);
        options = new VBox();
        newGamelbl = new Label("START GAME");
        instructionslbl = new Label("GAME INSTRUCTIONS");
        exitlbl = new Label("EXIT");
        options.getChildren().addAll(newGamelbl,instructionslbl,exitlbl);
        options.setAlignment(Pos.CENTER);

        // add styling to the buttons
        newGamelbl.getStyleClass().add("wide-blue-text");
        instructionslbl.getStyleClass().add("wide-blue-text");
        exitlbl.getStyleClass().add("wide-blue-text");

        // add to root
        root.getChildren().addAll(greeting,options);

    }

    public MainMenuView() {
        initScene();

    }

    public Button getNewGameButton() {
        return newGameButton;
    }
}
