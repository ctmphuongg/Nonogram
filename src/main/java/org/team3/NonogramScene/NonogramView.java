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

package org.team3.NonogramScene;

import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.team3.model.PuzzleFactory;
import org.team3.model.RoundFactory;

import java.util.ArrayList;
import java.util.List;
/**
 * MVC view class for Nonogram main game scene
 */
public class NonogramView {
    /** Vbox container for root */
    private VBox root;

    /** Round model */
    private RoundFactory theModel;

    /** Puzzle factory to get row and column hints*/
    private PuzzleFactory puzzleSpace;

    /** Gridepane container for game matrix */
    private GridPane matrix;

    /** Hbox container for lives */
    private HBox livesBox;

    /** Array list of all image views for number of  lives*/
    private ArrayList<ImageView> livesArray;

    /** Buttons for each square in the matrix */
    private List<Button> gridButton;

    /** Restart the game button */
    private Button btnRestart;

    /** Round number label */
    private Label lblRoundIndex;

    /** Button for each playing mode */
    private ToggleButton btnCross; ToggleButton btnChoose; ToggleButton btnGetHint; ToggleGroup btnPlayMode;

    /** Hbox container for all buttons */
    private HBox toggleGroup;

    /** Borderpane container for puzzle matrix, row and column hints*/
    private BorderPane puzzle;



    /**
     * Initialize the entire Game Instructions scene
     */
    private void initScene() {
        // Set up Vbox container for root
        root = new VBox();
        root.setAlignment(Pos.CENTER);


        // Set up the label to display round number
        HBox roundLabel = new HBox();
        lblRoundIndex = new Label("Round " + (PuzzleFactory.getRound() + 1));
        lblRoundIndex.setStyle("-fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-font-size: 25px; -fx-text-fill: white;");
        roundLabel.getChildren().add(lblRoundIndex);
        roundLabel.setAlignment(Pos.CENTER);
        lblRoundIndex.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        root.getChildren().add(roundLabel);

        // Set up view for number of lives
        initLives();

        // Set up view for puzzle matrix
        HBox center = new HBox();
        puzzle = new BorderPane(); //Main puzzle matrix
        VBox center2 = new VBox();
        center.getChildren().add(center2);
        center2.getChildren().add(puzzle); //Wrap the puzzle in a Vbox container
        center2.getStyleClass().add("puzzle");
        center.getStyleClass().add("puzzle");
        root.getChildren().add(center);

        // Set up view for row and column hints
        createColumnHint();
        createRowHint();

        // Set up the view for puzzle game (including main matrix, row and column hints)
        initPuzzleContent();

        // Set up the playing mode when clicked to each square
        setPlayingMode();

        // Set up the restart button
        HBox btnRestartBox = new HBox();
        btnRestart = new Button("Restart");
        btnRestartBox.getChildren().add(btnRestart);
        btnRestartBox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().add(btnRestartBox);

    }

    /**
     * Set playing mode when click to each square
     */
    private void setPlayingMode() {
        // Playing mode btnCross
        btnCross = new ToggleButton();
        btnCross.setText("X");
        btnCross.getStyleClass().add("choiceX");

        // Playing mode btnChoose
        btnChoose = new ToggleButton();
        btnChoose.setText("  ");
        btnChoose.getStyleClass().add("choiceColor");

        // Mode get hint
        ImageView btnGetHintImage = new ImageView(new Image(getClass().getResourceAsStream("/pic/lightbulb.png")));
        btnGetHintImage.setFitHeight(30);
        btnGetHintImage.setFitWidth(30);
        btnGetHint = new ToggleButton(Integer.toString(theModel.getHints()),btnGetHintImage);

        // Add all to toggle group
        btnPlayMode = new ToggleGroup();
        btnCross.setToggleGroup(btnPlayMode);
        btnChoose.setToggleGroup(btnPlayMode);
        btnGetHint.setToggleGroup(btnPlayMode);

        // Add Toggle group to scene
        toggleGroup = new HBox();
        toggleGroup.setAlignment(Pos.CENTER);
        toggleGroup.getStyleClass().add("button-group");
        toggleGroup.getChildren().addAll(btnCross, btnChoose, btnGetHint);
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

        // Create a button for each square
        for (int row=0; row < 5; row++) {
            for (int col=0; col < 5; col++) {
                Button box = new Button();
                box.getStyleClass().add("square");
                matrix.add(box, row, col);
                gridButton.add(box); // add the button to the array list
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
        VBox numbers_column = new VBox();
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
        /** */
        HBox numbers_row = new HBox();
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
     * @return the root of the scene
     */
    public VBox getRoot() { return root;}

    /**
     * @return restart button
     */
    public Button getBtnRestart() {return btnRestart;}

    /**
     * @return the list of all buttons for squares in matrix
     */
    public List<Button> getGridButton() {return gridButton;}

    /**
     * @return cross playing mode button
     */
    public ToggleButton getBtnCross() {return btnCross;}

    /**
     * @return color playing mode button
     */
    public ToggleButton getBtnChoose() {return btnChoose;}

    /**
     * @return get hint playing mode button
     */
    public ToggleButton getBtnGetHint() {return btnGetHint;}

    /**
     * @return Togglegroup for all playing mode buttons
     */
    public ToggleGroup getBtnPlayMode() {return btnPlayMode;}

    /**
     * @return array list of all image views of number of lives
     */
    public ArrayList<ImageView> getLivesArray() {return livesArray;}


    /**
     * Function for choosing the colored button
     * @return if colored button is being chosen
     */
    public BooleanProperty isChoose() {
        return btnChoose.selectedProperty();
    }

    /**
     * Function for choosing the crossed button
     * @return if colored button is being chosen
     */
    public BooleanProperty isCross() {
        return btnCross.selectedProperty();
    }


    /**
     * init the Nonogramview
     * @param theModel
     */
    public NonogramView(RoundFactory theModel) {
        this.theModel = theModel;
        puzzleSpace = theModel.getPuzzleFactory();
        initScene();
    }
}
    