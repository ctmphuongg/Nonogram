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
package org.team3.InstructionScene.HowToPlayScene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class HowToPlayView {
    public VBox getRoot() {
        return root;
    }

    private VBox root;


    /**
     * Initialize the entire Game Instructions scene
     */
    private void initScene() {
        root = new VBox();
        ImageView imageView = new ImageView(new Image("/pic/Instructions1.gif"));
        root.getChildren().add(imageView);
    }

    public HowToPlayView(){
        initScene();
    }

}
