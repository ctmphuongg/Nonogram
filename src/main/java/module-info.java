module csci205_final_project{
    requires java.base;
    requires java.desktop;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports org.team3;
    exports org.team3.model;
    exports org.team3.view;
    exports org.team3.gameMenu;
    exports org.team3.GameManager;
}