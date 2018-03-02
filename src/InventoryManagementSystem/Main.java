package InventoryManagementSystem;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    //The default size of the stage, X value
    int stageInitialSizeX = 1000;
    //The default size of the stage, Y value
    int stageInitialSizeY = 500;
    
    /**
     * @param primaryStage
     * @throws IOException if the resources are not available when accessed.
     * Usage: Starts the JavaFX program, sets the stage, and opens first scene.
     */
    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Views/Main.fxml"));
        primaryStage.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
        Scene mainScene = new Scene(root, stageInitialSizeX, stageInitialSizeY);
        primaryStage.setScene(mainScene);
        mainScene.getRoot().requestFocus();
        primaryStage.show();
    }
    
    /**
     * @param args 
     * Usage: Entry point of execution.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
