package org.pacman.pacmanfx_migration;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.pacman.pacmanfx_migration.Pages.DashboardController;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class Library {
    Clip clip;
    public void playSFX(String MusicName)
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/SDAT PROJECT/PacmanFX_Migration/src/main/resources/org/pacman/pacmanfx_migration/GameSfx/" + MusicName + ".wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the sound continuously
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void callSFX(String MusicName)
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:/SDAT PROJECT/PacmanFX_Migration/src/main/resources/org/pacman/pacmanfx_migration/GameSfx/" + MusicName + ".wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start(); // Play the sound
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void stopSFX() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop the sound
            clip.close(); // Close the clip
        }
    }

    public void closeClip() {
        if (clip != null) {
            clip.stop(); // Stop the sound
            clip.close(); // Close the clip
            clip = null; // Set the clip to null
        }
    }

    public void loadMainMenu(Label label)
    {
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
                Stage stage = (Stage) label.getScene().getWindow();

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
    }
}
