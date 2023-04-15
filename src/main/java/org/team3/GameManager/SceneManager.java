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

import java.util.HashMap;

/**
 * Scene manager: loads the scenes (more specifically: roots) of
 * the scenes in the game
 */
public class SceneManager {
    // holds the names of the Roots that will be put onto the GameManager scene
    public static final String GAME_MENU = "game menu";
    public static final String LEVEL_MENU = "level menu";
    public static final String INSTRUCTIONS = "instructions";
    public static final String GAMEPLAY = "gameplay";
    public static final String SETTINGS = "settings";

    // place to store roots
    private static HashMap<String, Parent> roots = new HashMap<>();

    /**
     * Instantiates the first root- the game menu
     */
    public SceneManager(){
//        Parent GameMenuRoot = GameMenuMain.getRoot();
//        addSceneRoots(GAME_MENU, GameMenuRoot);
    }


    /**
     * Populate all the other scenes (level menu, instructions, ...)
     */
    public static void populateOtherScenes(){
//        Parent LevelMenuRoot = LevelMenuMain.getRoot();
//        addSceneRoots(LEVEL_MENU, LevelMenuRoot);
//
//        Parent InstructionRoot = InstructionsMain.getRoot();
//        addSceneRoots(INSTRUCTIONS, InstructionRoot);
//
//        Parent GameplayRoot = BabaIsYouGameGUIMain.getRoot();
//        addSceneRoots(GAMEPLAY, GameplayRoot);
//
//        Parent SettingsRoot = SettingsMain.getRoot();
//        addSceneRoots(SETTINGS, SettingsRoot);
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
}
