/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 28/04/2023
 * Time: 14:30
 *
 * Project: csci205_final_project
 * Package: org.team3.InstructionScene.HowToPlayScene
 * Class: HowToPlayView
 *
 * Description:
 *
 * *****************************************/
package org.team3.InstructionScene.GameDemoScene;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameDemoView {
    /** VBox container for the root */
    private VBox root;

    /** Label heading for the view */
    private Label howToPlaylbl;

    /** Label to back to the previous scene */
    private Label lblBack;

    /** Label to play */
    private Label lblPlay;


    /**
     * Initialize the entire Game Instructions scene
     */
    private void initScene() {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        // Set up heading label for the scene
        howToPlaylbl = new Label("How to play");
        howToPlaylbl.getStyleClass().add("greeting");

        // Instructions gif
        ImageView imageView = new ImageView(new Image("/pic/instructions.gif"));
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        // Clue text
        VBox clueContainer = new VBox();
        clueContainer.setAlignment(Pos.CENTER);
        clueContainer.getStyleClass().add("instructions-container");

        Label lblClue = new Label("CLUES");
        lblClue.getStyleClass().add("instructions-header-lbl");

        Label lblTextClue = new Label("The numbers on the left row and top column of the grid are clues. " );
        Label lblTextClue2 = new Label(     "These numbers indicate the length and position of consecutive blocks of filled-in squares.");
//        Label lblTextClue2 = new Label("For example, if a column has the clue “2 1”,  there are two consecutive filled-in squares followed by at least one blank square " +
//                "and then one more filled-in squares");
//        lblTextClue2.setPrefWidth(200);

        lblTextClue.getStyleClass().add("instructions-lbl");
        clueContainer.getChildren().addAll(lblClue,lblTextClue, lblTextClue2);

        // Hint text
        VBox hintContainer = new VBox();
        hintContainer.setAlignment(Pos.CENTER);
        hintContainer.getStyleClass().add("instructions-container");

        Label lblHint = new Label("HINTS");
        lblHint.getStyleClass().add("instructions-header-lbl");

        Label lblTextHint = new Label("After clicking on the get hint button, select any square you want to get hint."+
                "You have 3 hints each round");
        lblTextHint.getStyleClass().add("instructions-lbl");
        hintContainer.getChildren().addAll(lblHint,lblTextHint);

        // Lives text
        VBox livesContainer = new VBox();
        livesContainer.setAlignment(Pos.CENTER);
        livesContainer.getStyleClass().add("instructions-container");

        Label lblLives = new Label("LIVES");
        lblLives.getStyleClass().add("instructions-header-lbl");

        Label lblTextLives = new Label("After selecting an incorrect square, you lose a life."+
                "You have 3 hints each round");
        lblTextHint.getStyleClass().add("instructions-lbl");
        livesContainer.getChildren().addAll(lblLives,lblTextLives);


        //VBox Container for back and next scene labels
        HBox lblContainer = new HBox();
        lblContainer.setAlignment(Pos.BOTTOM_CENTER);
        lblContainer.setSpacing(30);

        // HBox for back to main menu and play
//        HBox buttonContainer = new HBox();


        //Back to main menu label
        ImageView backImageView = new ImageView(new Image("/pic/back.png"));
        lblBack = new Label();
        backImageView.setFitWidth(30); // Set the size of the image view to fit within the label
        backImageView.setFitHeight(30);
        lblBack.setGraphic(backImageView); // Set the image view as the label's graphic

        //Play label
        lblPlay = new Label("Play");
        lblPlay.getStyleClass().add("play-label");

        lblContainer.getChildren().addAll(lblBack,lblPlay);

//        buttonContainer.getChildren().addAll(lblLives,lblTextLives);

        // Add to the root
        root.getChildren().addAll(howToPlaylbl, imageView, clueContainer, hintContainer, livesContainer, lblContainer);
    }

    /**
     * @return the root of the scene
     */
    public VBox getRoot() {return root;}

    /**
     * @return back to other instructions label
     */
    public Label getLblBack() {return lblBack;}

    /**
     * @return play label
     */
    public Label getLblPlay() {return lblPlay;}


    public GameDemoView(){
        initScene();
    }

}
