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

import javafx.scene.layout.VBox;
import org.team3.model.PuzzleFactory;
import org.team3.model.Round;

public class MainMenuView {
    public VBox getRoot() {
        return root;
    }

    private VBox root;

    private void initScene() {
        root = new VBox();
    }

    public MainMenuView() {
        initScene();
    }

}
