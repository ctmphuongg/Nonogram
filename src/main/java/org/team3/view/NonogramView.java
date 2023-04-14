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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
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
    private HBox choices;
    private List<Button> gridButton;
    private Round theModel;
    private PuzzleFactory puzzleSpace;
    private ToggleButton cross;
    private ToggleButton choose;
    private ToggleGroup playMode;
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

        // HINT ON TOP
        // Get the column hint data
        ArrayList<Integer>[] column_hint_data = puzzleSpace.getColumnHint();



        // Create a row contain numbers that represent number of boxes being colored (HINT ON TOP)
        numbers_row = new HBox();
        puzzle.setTop(numbers_row);

        for (int i = 0; i < 6; i++){
            //Create a StackPane for rectangle and text
            StackPane stackPane = new StackPane();


            // Create Rectangle object
            Rectangle numberColorBoxRow = new Rectangle(30, 30, Color.LIGHTSTEELBLUE);
            numberColorBoxRow.setStroke(Color.ALICEBLUE);
            numberColorBoxRow.getStyleClass().add("number_box");


            // Starting from the second rectangle
            if (i>0){
                stackPane.getChildren().add(numberColorBoxRow);
                // Create a Text
                ArrayList<Integer> text_content = column_hint_data[i-1];
                for (Integer num : text_content) {
                    Text text = new Text(num.toString());
                    Rectangle smallHint = new Rectangle(30, 10, Color.YELLOW);
                    StackPane hintBox = new StackPane();
                    hintBox.getChildren().addAll(smallHint, text);
//                    HBox hintBox = new HBox(smallHint, text);
//                    stackPane.getChildren().add(hintBox);
                    stackPane.getChildren().add(hintBox);
                }





            }else{
                // Create a Text
                Text text = new Text("");
                // Add Rectangle and Text into the StackPane
                stackPane.getChildren().addAll(numberColorBoxRow, text);
            }
            numbers_row.getChildren().add(stackPane);



        }

        // HINT ON LEFT
        // Get the row_hint_data
        ArrayList<Integer>[] row_hint_data = puzzleSpace.getRowHint();
        // Create a column contain numbers that represent number of boxes being colored (HINT
        numbers_column = new VBox();
        puzzle.setLeft(numbers_column);
        for (int i = 0; i < 5; i++){

            //Create a StackPane for rectangle and text
            StackPane stackPane = new StackPane();

            // Create Rectangle
            Rectangle numberColorBoxRow = new Rectangle(30, 30, Color.LIGHTSTEELBLUE);
            numberColorBoxRow.setStroke(Color.ALICEBLUE);
            numberColorBoxRow.getStyleClass().add("number_box");


            // Create a Text
            Text text = new Text(row_hint_data[i].toString());
            // Add Rectangle and Text into the StackPane
            stackPane.getChildren().addAll(numberColorBoxRow, text);


            numbers_column.getChildren().add(stackPane);
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
        cross = new ToggleButton();
        cross.setText("X");
        cross.getStyleClass().add("choiceX");
        choose = new ToggleButton();
        choose.setText("  ");
        choose.getStyleClass().add("choiceColor");
        playMode = new ToggleGroup();
        cross.setToggleGroup(playMode);
        choose.setToggleGroup(playMode);
        toggleGroup = new HBox();
        toggleGroup.setAlignment(Pos.CENTER);
        toggleGroup.getChildren().addAll(cross,choose);
        root.getChildren().add(toggleGroup);
    }



    private void initStyling() {
        root.setAlignment(Pos.CENTER);

    }

    private void initEventHandler() {
        cross.selectedProperty().addListener(event -> {
            cross.setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
            choose.setStyle(null);
                }
        );

        choose.selectedProperty().addListener(event -> {
                    choose.setStyle("-fx-background-color: #95abc4; -fx-border-color: #185c7a");
                    cross.setStyle(null);
                }
        );

        gridButton.stream().forEach(button -> {
            button.setOnMouseClicked(event -> {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                theModel.setPlayingMode(PLAYING_MODE.SQUARE);
                if (theModel.checkValidGuess(row,column)) {
                    boolean correct = theModel.guessEvaluator(row, column);

                    if (correct) {
                        button.setStyle("-fx-background-color: #185c7a;");
                        button.applyCss();
                        button.layout();
                    } else {
                        button.setText("X");
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
    