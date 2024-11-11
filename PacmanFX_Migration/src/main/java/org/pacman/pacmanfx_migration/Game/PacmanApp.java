package org.pacman.pacmanfx_migration.Game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.embed.swing.SwingNode;

public class PacmanApp extends Application{
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        // Create a SwingNode to hold the Swing component
        SwingNode swingNode = new SwingNode();

        // Create an instance of your Swing class
        Board board = new Board(3);

        // Add the Swing component to the SwingNode
        swingNode.setContent(board);

        // Add the SwingNode to the JavaFX scene
        root.getChildren().add(swingNode);

        Scene scene = new Scene(root,900,900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
