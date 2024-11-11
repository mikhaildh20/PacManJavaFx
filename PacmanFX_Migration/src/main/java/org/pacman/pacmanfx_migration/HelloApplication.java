package org.pacman.pacmanfx_migration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.pacman.pacmanfx_migration.Pages.LoadingController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Page/Loading.fxml"));
        Parent root = loader.load();

        LoadingController controller = loader.getController();
        Scene scene = new Scene(root);
        scene.getRoot().cursorProperty().set(Cursor.NONE);
        controller.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(String.valueOf(HelloApplication.class.getResource("Element/pacicon.png"))));
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}