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

package org.team3.NonogramGame;

import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.team3.NonogramGame.controller.NonogramController;
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

        initLives();

        initPuzzle();

        createColumnHint();

        createRowHint();

        initPuzzleContent();

        setPlayingMode();

    }

    /**
     * Set Playing mode when click to each cell
     */
    private void setPlayingMode() {
        // Playing mode cross
        cross = new ToggleButton();
        cross.setText("X");
        cross.getStyleClass().add("choiceX");

        // Playing mode choose
        choose = new ToggleButton();
        choose.setText("  ");
        choose.getStyleClass().add("choiceColor");

        // Mode get hint
        ImageView btnGetHintImage = new ImageView(new Image(getClass().getResourceAsStream("/pic/lightbulb.png")));
        btnGetHintImage.setFitHeight(30);
        btnGetHintImage.setFitWidth(30);
        getHint = new ToggleButton(Integer.toString(theModel.getHints()),btnGetHintImage);

        // Add all to toggle group
        playMode = new ToggleGroup();
        cross.setToggleGroup(playMode);
        choose.setToggleGroup(playMode);
        getHint.setToggleGroup(playMode);

        // Add Toggle group to scene
        toggleGroup = new HBox();
        toggleGroup.setAlignment(Pos.CENTER);
        toggleGroup.getStyleClass().add("button-group");


        toggleGroup.getChildren().addAll(cross, choose, getHint);
        root.getChildren().add(toggleGroup);
    }

    /**
     * Initiate the content of the puzzle space
     */
    private void initPuzzleContent() {
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
    }

    /**
     * Create hint for the rows
     */
    private void createRowHint() {
        // HINT ON LEFT
        // Get the row_hint_data
        ArrayList<Integer>[] row_hint_data = puzzleSpace.getRowHint();

        // Create a column contain numbers that represent number of boxes being colored (HINT
        numbers_column = new VBox();
        puzzle.setLeft(numbers_column);

        for (int i = 0; i < 5; i++){
            // Create HBox covering each square of hints
            HBox hintContainerHBox = new HBox();
            hintContainerHBox.setPrefSize(31, 31);
            hintContainerHBox.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-border-color: ALICEBLUE");
            hintContainerHBox.getStyleClass().add("number_box_left");

            // Take row hint data for the row we are dealing with at index i
            ArrayList<Integer> text_content = row_hint_data[i];

            // Add a hintBox including each number of a hint data
            for (Integer num : text_content) {
                // Set text to hint data
                Text text = new Text(num.toString());
                text.setFill(Color.WHITE);

                // Format the square
                Rectangle smallHint = new Rectangle(10, 29);
                StackPane hintBox = getHintBox(text, smallHint);
                hintContainerHBox.getChildren().add(hintBox);

            }

            // Add whole hint container to puzzle
            numbers_column.getChildren().add(hintContainerHBox);
        }
    }

    /**
     * Design the hint box
     * @param text - the hint number
     * @param smallHint - the square for the hint number
     * @return hintBox - a Stackpane containing the square and the hint number
     */
    private static StackPane getHintBox(Text text, Rectangle smallHint) {
        smallHint.setStyle("-fx-fill: #176285");

        // Set the font size dynamically based on the size of the rectangle
        double fontSize = Math.min(smallHint.getWidth() / text.getText().length(), smallHint.getHeight());
        text.setFont(Font.font(fontSize));

        // Add hint to scene
        StackPane hintBox = new StackPane();
        hintBox.getChildren().addAll(smallHint, text);
        return hintBox;
    }

    /**
     * Create hint for the column (TOP ROW HINT)
     */
    private void createColumnHint() {
        // Get the column hint data
        ArrayList<Integer>[] column_hint_data = puzzleSpace.getColumnHint();

        // Create a row contain numbers that represent number of boxes being colored (HINT ON TOP)
        numbers_row = new HBox();
        puzzle.setTop(numbers_row);

        for (int i = 0; i < 6; i++){
            // Create VBox covering each square of hints
            VBox hintContainerVBox = new VBox();
            hintContainerVBox.setPrefSize(30, 30);
            hintContainerVBox.setStyle("-fx-background-color: LIGHTSTEELBLUE; -fx-border-color: ALICEBLUE");
            hintContainerVBox.getStyleClass().add("number_box_top");
            hintContainerVBox.setSpacing(-3);
            hintContainerVBox.setPadding(new Insets(0, 0, 0, 0));
            hintContainerVBox.setAlignment(Pos.CENTER);

            // Starting from the second rectangle
            if (i>0){
                ArrayList<Integer> text_content = column_hint_data[i-1];
                for (Integer num : text_content) {
                    // Set text to hint data
                    Text text = new Text(num.toString());
                    text.setFill(Color.WHITE);

                    // Format the square
                    Rectangle smallHint = new Rectangle(29,10);
                    StackPane hintBox = getHintBox(text, smallHint);
                    hintBox.setAlignment(Pos.BOTTOM_CENTER);
                    hintContainerVBox.getChildren().add(hintBox);

                }

            }
            numbers_row.getChildren().add(hintContainerVBox);

        }
    }

    /**
     * Initiate the wrapper of puzzle
     */
    private void initPuzzle() {
        // Create all the puzzle math
        puzzle = new BorderPane();
        HBox center = new HBox();
        VBox center2 = new VBox();
        center.getChildren().add(center2);
        center2.getChildren().add(puzzle);
        center2.getStyleClass().add("puzzle");
        center.getStyleClass().add("puzzle");
        root.getChildren().add(center);
    }

    /**
     * Initiate 3 lives for the round
     */
    private void initLives() {
        livesBox = new HBox();

        // create line of three heart represent lives
        root.getChildren().add(livesBox);
        livesBox.getStyleClass().add("hearts");

        // Add 3 hearts as lives
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
    }


    /**
     * Styling for the puzzle
     */
    private void initStyling() {
        root.setAlignment(Pos.CENTER);

    }

    /**
     * Function for choosing the colored button
     * @return if colored button is being chosen
     */
    public BooleanProperty isChoose() {
        return choose.selectedProperty();
    }

    /**
     * Function for choosing the crossed button
     * @return if colored button is being chosen
     */
    public BooleanProperty isCross() {
        return cross.selectedProperty();
    }


    /**
     * init the Nonogramview
     * @param theModel
     */
    public NonogramView(Round theModel) {
        this.theModel = theModel;
        puzzleSpace = theModel.getPuzzleFactory();
        initScene();
        initStyling();
    }
}
    