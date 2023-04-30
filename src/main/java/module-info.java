module csci205_final_project{
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports org.team3.model;
    exports org.team3.MainMenuScene;
    exports org.team3.NonogramScene;
    exports org.team3.GameManager;
    exports org.team3.GameResultScene;
    exports org.team3.RoundScene;
    exports org.team3.InstructionScene.PlayingModeScene;
    exports org.team3.InstructionScene.GameDemoScene;

}