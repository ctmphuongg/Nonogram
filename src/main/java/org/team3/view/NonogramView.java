/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023
 * Instructor: Prof. Brian King
 *
 * Name: MINHPHUONG CAO
 * Section: Section 01
 * Date: 4/5/23
 * Time: 9:48 AM
 *
 * Project: csci205_final_project
 * Package: view
 * Class: nonogramView*
 * Description:
 *
 * *****************************************/

package org.team3.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import org.team3.model.PLAYING_MODE;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;

import java.util.ArrayList;
import java.util.List;

public class NonogramView {

    private VBox root;
    private BorderPane puzzle;
    private GridPane matrix;
    private HBox livesBox;
    private ArrayList<ImageView> livesArray;
    private HBox numbers_row;
    private VBox numbers_column;
    private List<Button> gridButton;
    private Round theModel;
    private PuzzleFactory puzzleSpace;
    private ToggleButton btnCross;
    private ToggleButton btnChoose;
    private ToggleButton btnGetHint;
    private ToggleGroup playModeToggleGroup;
    private HBox toggleGroup;
    public VBox getRoot() { return root;}

    private void initScene() {
        root = new VBox();

        livesBox = new HBox();

        // create line of three heart represent lives
        root.getChildren().add(livesBox);
        livesBox.getStyleClass().add("hearts");

        this.livesArray = new ArrayList<>();
        for (int i = 0; i < theModel.getLives();i++){
            Image live = new Image(getClass().getResource("/pic/heart.png").toExternalForm());
            ImageView imageView = new ImageView(live);
            imageView.getStyleClass().add("heart-shape");
            this.livesArray.add(imageView);

            // Cannot change to css
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
        }

        livesBox.getChildren().addAll(livesArray);

        // Create all the puzzle math
        puzzle = new BorderPane();
        HBox center = new HBox();
        VBox center2 = new VBox();
        center.getChildren().add(center2);
        center2.getChildren().add(puzzle);
        center2.getStyleClass().add("puzzle");
        center.getStyleClass().add("puzzle");
        root.getChildren().add(center);


        // Create a row contain numbers that represent number of boxes being colored
        numbers_row = new HBox();
        puzzle.setTop(numbers_row);

        for (int i = 0; i < 6; i++){
            Rectangle numberColorBoxRow = new Rectangle(29, 29, Color.LIGHTSTEELBLUE);
            numberColorBoxRow.setStroke(Color.ALICEBLUE);
            numberColorBoxRow.getStyleClass().add("number_box");
            numbers_row.getChildren().add(numberColorBoxRow);
        }

        // Create a column contain numbers that represent number of boxes being colored
        numbers_column = new VBox();
        puzzle.setLeft(numbers_column);
        for (int i = 0; i < 5; i++){
            Rectangle numberColorBoxRow = new Rectangle(30, 30, Color.LIGHTSTEELBLUE);
            numberColorBoxRow.setStroke(Color.ALICEBLUE);
            numberColorBoxRow.getStyleClass().add("number_box");
            numbers_column.getChildren().add(numberColorBoxRow);
        }


        // Real puzzle matrix
        matrix = new GridPane();
        puzzle.setCenter(matrix);

        gridButton = new ArrayList<>();

        for (int row=0; row < 5; row++) {
            for (int col=0; col < 5; col++) {
                Button box = new Button();
                box.getStyleClass().add("box");
                matrix.add(box, row, col);

                gridButton.add(box);
            }
        }

        // User choice of color or X
//        choices = new HBox();
//        root.getChildren().add(choices);
//        choices.getStyleClass().add("choices");
//
//        Button choiceX = new Button("X");
//        choiceX.getStyleClass().add("choiceX");
//
//        Button choiceColor = new Button("  ");
//        choiceColor.getStyleClass().add("choiceColor");

//        choices.getChildren().add(choiceX);
//        choices.getChildren().add(choiceColor);

        // Playing mode
        btnCross = new ToggleButton();
        btnCross.setText("X");
        btnCross.getStyleClass().add("choiceX");

        btnChoose = new ToggleButton();
        btnChoose.setText("  ");
        btnChoose.getStyleClass().add("choiceColor");

        btnGetHint = new ToggleButton();
        btnGetHint.setText(Integer.toString(theModel.getHints()));
        btnGetHint.getStyleClass().add("choiceGetHint");

        playModeToggleGroup = new ToggleGroup();
        btnCross.setToggleGroup(playModeToggleGroup);
        btnChoose.setToggleGroup(playModeToggleGroup);
        btnGetHint.setToggleGroup(playModeToggleGroup);

        toggleGroup = new HBox();
        toggleGroup.setAlignment(Pos.CENTER);
        toggleGroup.getChildren().addAll(btnCross,btnChoose,btnGetHint);
        root.getChildren().add(toggleGroup);
    }



    private void initStyling() {
        root.setAlignment(Pos.CENTER);

    }

    private void initEventHandler() {
        btnCross.selectedProperty().addListener(event -> {
            btnCross.setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
            btnChoose.setStyle(null);
            btnGetHint.setStyle(null);

                }
        );

        btnChoose.selectedProperty().addListener(event -> {
            btnChoose.setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
            btnCross.setStyle(null);
            btnGetHint.setStyle(null);
                }
        );

        btnGetHint.selectedProperty().addListener(event -> {
            btnGetHint.setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
            btnCross.setStyle(null);
            btnChoose.setStyle(null);
                }
        );


        gridButton.stream().forEach(button -> {
            button.setOnMouseClicked(event -> {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);

                Toggle selectedButton = playModeToggleGroup.getSelectedToggle();

                if (selectedButton == btnChoose) {
                    theModel.setPlayingMode(PLAYING_MODE.SQUARE);
                    boolean correct = theModel.guessEvaluator(row, column);


                    if (correct) {
                        button.setStyle("-fx-background-color: #185c7a;");
                        button.applyCss();
                        button.layout();
                    } else {
                        button.setText("X");
                    }

                } else if (selectedButton == btnCross) {
                    theModel.setPlayingMode(PLAYING_MODE.CROSS);
                    boolean correct = theModel.guessEvaluator(row, column);


                    if (correct) {
                        button.setText("X");
                    } else {
                        button.setStyle("-fx-background-color: #185c7a;");
                        button.applyCss();
                        button.layout();
                    }
                } else if ((selectedButton == btnGetHint) && (theModel.getHints() >0)) {
                    theModel.setPlayingMode(PLAYING_MODE.HINT);
                    boolean isColoredSquare = theModel.isColored(row,column);
                    if (isColoredSquare){
                        button.setStyle("-fx-background-color: #185c7a;");
                        button.applyCss();
                        button.layout();
                    } else {
                        button.setText("X");
                    }

                    if (theModel.getHints() <0) {
                        btnGetHint.setSelected(false);
                        btnGetHint.disabledProperty();
                    } else {
                        btnGetHint.setText(Integer.toString(theModel.getHints()));
                    }

                }

            });
        });

    }

    /**
     * Bindings for the models
     */
    private void initBindings(){
        //bindings for lives
        for (int i = 0; i<livesArray.size(); ++i){
            livesArray.get(i).visibleProperty().bind(theModel.getLivesValueArray(i));
        }

    }

    public NonogramView() {

//        theModel = new Nonogram();
//        theModel.initNewGame();
        puzzleSpace = new PuzzleFactory();
        theModel = new Round(puzzleSpace);
        theModel.initNewRound();
        theModel.displayMatrix();


        initScene();
        initStyling();
        initEventHandler();
        initBindings();
    }
}
    