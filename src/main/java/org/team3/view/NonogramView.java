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

import javafx.beans.property.BooleanProperty;
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
import javafx.scene.text.Text;
import org.team3.controller.NonogramController;
import org.team3.model.PLAYING_MODE;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;

import java.util.ArrayList;
import java.util.List;

public class NonogramView {

    private Round theModel;
    private NonogramController theController;
    private VBox root;
    private BorderPane puzzle;
    private GridPane matrix;
    private HBox livesBox;
    private ArrayList<ImageView> livesArray;
    private HBox numbers_row;
    private VBox numbers_column;
    private HBox choices;
    private List<Button> gridButton;

    private PuzzleFactory puzzleSpace;
    private ToggleButton cross;
    private ToggleButton choose;
    private ToggleButton getHint;
    private ToggleGroup playMode;
    private HBox toggleGroup;
    public VBox getRoot() { return root;}

    public List<Button> getGridButton() {
        return gridButton;
    }

    public ToggleButton getCross() {
        return cross;
    }

    public ToggleButton getChoose() {
        return choose;
    }

    public ToggleButton getGetHint() {
        return getHint;
    }

    public ToggleGroup getPlayMode() {
        return playMode;
    }

    public ArrayList<ImageView> getLivesArray() {
        return livesArray;
    }

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
            VBox hintContainerVBox = new VBox();
            hintContainerVBox.setPrefSize(30, 30);
            hintContainerVBox.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-border-color: ALICEBLUE");
            hintContainerVBox.getStyleClass().add("number_box");



            // Starting from the second rectangle
            if (i>0){
                ArrayList<Integer> text_content = column_hint_data[i-1];
                for (Integer num : text_content) {
                    Text text = new Text(num.toString());
                    text.setFill(Color.WHITE);
                    Rectangle smallHint = new Rectangle(30, 10);
                    smallHint.setStyle("-fx-fill: #176285");
                    StackPane hintBox = new StackPane();
                    hintBox.getChildren().addAll(smallHint, text);
                    hintContainerVBox.getChildren().add(hintBox);

                }


            }
            numbers_row.getChildren().add(hintContainerVBox);



        }

        // HINT ON LEFT
        // Get the row_hint_data
        ArrayList<Integer>[] row_hint_data = puzzleSpace.getRowHint();
        // Create a column contain numbers that represent number of boxes being colored (HINT
        numbers_column = new VBox();
        puzzle.setLeft(numbers_column);
        for (int i = 0; i < 5; i++){

            HBox hintContainerHBox = new HBox();
            hintContainerHBox.setPrefSize(30, 30);
            hintContainerHBox.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-border-color: ALICEBLUE");
            hintContainerHBox.getStyleClass().add("number_box");

            //Create a StackPane for rectangle and text
            StackPane stackPane = new StackPane();

            ArrayList<Integer> text_content = row_hint_data[i];
            for (Integer num : text_content) {
                Text text = new Text(num.toString());
                text.setFill(Color.WHITE);
                Rectangle smallHint = new Rectangle(10, 30);
                smallHint.setStyle("-fx-fill: #176285");
                StackPane hintBox = new StackPane();
                hintBox.getChildren().addAll(smallHint, text);
                hintContainerHBox.getChildren().add(hintBox);


            }


//            // Add Rectangle and Text into the StackPane
//            stackPane.getChildren().addAll(numberColorBoxRow, text);


            numbers_column.getChildren().add(hintContainerHBox);
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

        // Playing mode


        // Playing mode
        cross = new ToggleButton();
        cross.setText("X");
        cross.getStyleClass().add("choiceX");


        choose = new ToggleButton();
        choose.setText("  ");
        choose.getStyleClass().add("choiceColor");


        ImageView btnGetHintImage = new ImageView(new Image(getClass().getResourceAsStream("/pic/lightbulb.png")));
        btnGetHintImage.setFitHeight(30);
        btnGetHintImage.setFitWidth(30);
        getHint = new ToggleButton(Integer.toString(theModel.getHints()),btnGetHintImage);


        playMode = new ToggleGroup();
        cross.setToggleGroup(playMode);
        choose.setToggleGroup(playMode);
        getHint.setToggleGroup(playMode);


        toggleGroup = new HBox();
        toggleGroup.setAlignment(Pos.CENTER);
        toggleGroup.getStyleClass().add("button-group");


        toggleGroup.getChildren().addAll(cross,choose,getHint);
        root.getChildren().add(toggleGroup);

    }



    private void initStyling() {
        root.setAlignment(Pos.CENTER);

    }

    public BooleanProperty isChoose() {
        return choose.selectedProperty();
    }

    public BooleanProperty isCross() {
        return cross.selectedProperty();
    }


    public NonogramView(Round theModel) {

//        theModel = new Nonogram();
//        theModel.initNewGame();
//        puzzleSpace = new PuzzleFactory();
        this.theModel = theModel;
        puzzleSpace = theModel.getPuzzleFactory();
//        theModel.initNewRound();
//        theModel.displayMatrix();

        initScene();
        initStyling();
//        theController.initEventHandler();
//        initBindings();
    }
}
    