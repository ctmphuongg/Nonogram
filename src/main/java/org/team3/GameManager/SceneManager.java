/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023
 * Instructor: Brian King
 * Section: 9 am
 * Name: An Ngo
 * Date: 2023
 * Lab / Assignment: lab
 * Description:
 * * *****************************************/

package org.team3.GameManager;


import javafx.scene.Parent;
import org.team3.GameOver.GameOverMain;
import org.team3.NonogramGame.NonogramMain;
import org.team3.RoundScene.RoundMain;
import org.team3.gameMenu.MainMenuMain;

import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Scene manager: loads the scenes (more specifically: roots) of
 * the scenes in the game
 */
public class SceneManager {
    // holds the names of the Roots that will be put onto the GameManager scene
    public static final String GAME_MENU = "game menu";
    public static final String GAME_SCREEN = "game screen";
    public static final String GAME_LOSER = "game loser screen";
    public static final String GAME_WINNER= "game winner screen";

    public static final String GAME_ROUND= "game round screen";
    // place to store roots
    private static HashMap<String, Parent> roots = new HashMap<>();

    /**
     * Instantiates the first root - the game menu
     */
    public SceneManager(){
        Parent GameMenuRoot = MainMenuMain.getRoot();
        addSceneRoots(GAME_MENU, GameMenuRoot);
    }


    /**
     * Populate all the other scenes (level menu, instructions, ...)
     */
    public static void populateOtherScenes() throws URISyntaxException {
        addNewGame();
        Parent gameOverRoot = GameOverMain.getRoot("You Lost");
        addSceneRoots(GAME_LOSER, gameOverRoot);
        gameOverRoot = GameOverMain.getRoot("You Win");
        addSceneRoots(GAME_WINNER, gameOverRoot);
        Parent roundRoot = RoundMain.getRoot();
        addSceneRoots(GAME_ROUND, roundRoot);
    }

    /**
     * Add scene root into hashmap
     * @param sceneName - name of the scene root
     * @param sceneRoot - the root itself
     */
    public static void addSceneRoots(String sceneName, Parent sceneRoot){
        roots.put(sceneName, sceneRoot);
    }

    /**
     * Gets a scene root based on the name
     * @param rootName - name of the scene root (as stored in the hashmap)
     * @return - scene root
     */
    public static Parent getSceneRoot(String rootName){
        return roots.get(rootName);
    }

    public static void addNewGame() throws URISyntaxException {
        Parent LevelMenuRoot = NonogramMain.getRoot();
        addSceneRoots(GAME_SCREEN , LevelMenuRoot);
    }
}
