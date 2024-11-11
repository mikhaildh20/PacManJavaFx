package org.pacman.pacmanfx_migration.Pages;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.pacman.pacmanfx_migration.HelloApplication;
import org.pacman.pacmanfx_migration.Library;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

public class LoadingController extends Library implements Initializable {
    @FXML
    private AnchorPane Canvas;

    @FXML
    private ImageView EnterTitle;

    @FXML
    private ImageView LoadBox;
    @FXML
    private ImageView PacLeft;

    @FXML
    private ImageView PacRight;

    @FXML
    private ImageView bar1;

    @FXML
    private ImageView bar10;

    @FXML
    private ImageView bar11;

    @FXML
    private ImageView bar2;

    @FXML
    private ImageView bar3;

    @FXML
    private ImageView bar4;

    @FXML
    private ImageView bar5;

    @FXML
    private ImageView bar6;

    @FXML
    private ImageView bar7;

    @FXML
    private ImageView bar8;

    @FXML
    private ImageView bar9;

    private ImageView[] bars;
    private int entered = 0;

    public void LoadingPerformed()
    {
        PacLeft.setTranslateY(168); // set initial position above the screen
        PacRight.setTranslateY(168);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), PacLeft);
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(2),PacRight);

        translateTransition.setFromY(-220);
        translateTransition1.setFromY(-220);

        translateTransition.setToY(0);
        translateTransition1.setToY(0);

        translateTransition.setCycleCount(1);
        translateTransition1.setCycleCount(1);

        translateTransition.setAutoReverse(false);
        translateTransition1.setAutoReverse(false);

        translateTransition.play();
        translateTransition1.play();

        // initialize the array
        bars = new ImageView[] {bar1, bar2, bar3, bar4, bar5, bar6, bar7, bar8, bar9, bar10, bar11};

        Timeline timeline = new Timeline();

        for (int i = 0; i < bars.length; i++) {
            final int index = i;
            KeyValue keyValue = new KeyValue(bars[index].visibleProperty(), true); // or false, depending on what you want
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i + 1), keyValue);
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setOnFinished(event -> {
            try {
                // Pause for 1 second
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(pauseEvent -> {
                    // Load the new FXML file
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Page/Dashboard.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // Get the current stage
                    Stage stage = (Stage) bars[0].getScene().getWindow();

                    // Get the current scene
                    Scene currentScene = stage.getScene();

                    // Get the window of the current scene
                    Window window = currentScene.getWindow();

                    // Close the window (which will close the LoadingController)
                    ((Stage) window).close();

                    // Set the new scene
                    Scene newScene = new Scene(root);
                    newScene.getRoot().cursorProperty().set(Cursor.NONE);
                    DashboardController controller = loader.getController();
                    controller.setScene(newScene);

                    // Set the initial opacity of the new scene to 0
                    root.setOpacity(0);

                    // Create a FadeTransition to animate the opacity property of the new scene
                    FadeTransition transition = new FadeTransition(Duration.seconds(1), root);
                    transition.setToValue(1);

                    // Play the transition
                    transition.play();

                    // Set the new scene
                    stage.setScene(newScene);

                    // Show the new scene
                    stage.show();
                    stopSFX();
                });
                pause.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        timeline.play();
    }

    public void setScene(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER)
            {
                if (entered == 0) {
                    entered = 1;
                    if (event.getCode() == KeyCode.ENTER) {
                        EnterTitle.setVisible(false);
                        LoadBox.setVisible(true);
                        PacLeft.setVisible(true);
                        PacRight.setVisible(true);
                        LoadingPerformed(); // Call the initialize method when Enter is pressed
                    } else if (event.getCode() == KeyCode.ESCAPE) {
                        Platform.exit(); // Quit the application when Escape is pressed
                    }
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playSFX("StartMenu");
    }
}
