package org.pacman.pacmanfx_migration.Pages;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.pacman.pacmanfx_migration.Connection.Database;
import org.pacman.pacmanfx_migration.Game.Board;
import org.pacman.pacmanfx_migration.Library;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameController extends Library implements Initializable,Board.ScoreUpdateListener {
    @FXML
    private Label FinalScore;

    @FXML
    private ImageView GameOverForm;

    @FXML
    private TextField txtName;
    @FXML
    private ImageView GameBoard;

    @FXML
    private AnchorPane GameCanvas;

    @FXML
    private Label difficultyLabel;

    @FXML
    private ImageView ghost;

    @FXML
    private ImageView life1;

    @FXML
    private ImageView life2;

    @FXML
    private ImageView life3;

    @FXML
    private ImageView pacmodel;

    @FXML
    private Label score;
    private ImageView lives[];

    private String difficulty;
    private int ghosts;

    public void getGameDetails(int choice){
        switch (choice)
        {
            case 1:
                difficulty = "EASY";
                this.ghosts = 9;
                break;
            case 2:
                difficulty = "MEDIUM";
                this.ghosts = 13;
                break;
            case 3:
                difficulty = "HARD";
                this.ghosts = 17;
                break;
        }
        difficultyLabel.setText(difficulty);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lives = new ImageView[] {life1,life2,life3};
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);
        GameCanvas.getChildren().add(swingNode);

        loadGame();
    }

    public void loadGame(){
        Platform.runLater(()->{
            SFXCall("game_start");

            pacmodel.setTranslateY(349);
            ghost.setTranslateY(100);
            for (int i=0;i<lives.length;i++)
            {
                lives[i].setTranslateY(149);
            }

            TranslateTransition pacman =  new TranslateTransition(Duration.seconds(1), pacmodel);
            TranslateTransition ghostT = new TranslateTransition(Duration.seconds(1.5),ghost);
            TranslateTransition live1 = new TranslateTransition(Duration.seconds(2.5),lives[0]);
            TranslateTransition live2 = new TranslateTransition(Duration.seconds(3),lives[1]);
            TranslateTransition live3 = new TranslateTransition(Duration.seconds(3.5),lives[2]);

            pacman.setFromY(-538);
            ghostT.setFromY(-381);
            live1.setFromY(-127);
            live2.setFromY(-127);
            live3.setFromY(-127);

            pacman.setToY(349);
            ghostT.setToY(100);
            live1.setToY(149);
            live2.setToY(149);
            live3.setToY(149);

            pacman.setCycleCount(1);
            ghostT.setCycleCount(1);
            live1.setCycleCount(1);
            live2.setCycleCount(1);
            live3.setCycleCount(1);

            pacman.setAutoReverse(false);
            ghostT.setAutoReverse(false);
            live1.setAutoReverse(false);
            live2.setAutoReverse(false);
            live3.setAutoReverse(false);

            pacman.play();
            ghostT.play();
            live1.play();
            live2.play();
            live3.play();

            live3.setOnFinished(actionEvent -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Handle the exception
                }
                SFXPlay("spider_dance");
            });
        });
    }

    public void heartGone(int idx){
        ImageView heart = lives[idx];

        // Set the heart to invisible after the blinking animation
        Platform.runLater(() -> {
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), heart);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setCycleCount(3); // Blink 3 times
            fadeTransition.setAutoReverse(true);

            // After the fade transition finishes, set the heart to invisible
            fadeTransition.setOnFinished(event -> heart.setVisible(false));

            // Start the fade transition
            fadeTransition.play();
        });
    }

    @Override
    public void updateScore(int score) {
        Platform.runLater(() -> updateScoreLabel(score));
    }

    public void updateScoreLabel(int score) {
        this.score.setText(String.valueOf(score));
    }



    private void createSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create a Swing component (e.g., a JButton)
                Board game = new Board(ghosts);

                // Set the preferred size of the Board
                game.setPreferredSize(new Dimension(900, 900)); // Set the size you want

                // Add the Swing component to the SwingNode
                swingNode.setContent(game);

                game.setPacmanHitListener(new Board.PacmanHitListener() {
                    @Override
                    public void pacmanHit(int idx) {
                        heartGone(idx); // Call the heartGone method with the index
                    }
                });
                game.setScoreUpdateListener(GameController.this);
                game.setGameStopper(new Board.GameStopper() {
                    @Override
                    public void gameStop() {
                        Platform.runLater(()->{
                            SFXStop();
                            loadMainMenu(score);
                        });
                    }
                });
                game.setDyingListener(new Board.DyingListener() {
                    @Override
                    public void pacmanDied() {
                        Platform.runLater(() -> {
                            SFXCall("determination");
                            SwingNode swingNode = (SwingNode) GameCanvas.getChildren().get(0);
                            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), swingNode);
                            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(4),pacmodel);
                            FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(4),ghost);
                            FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(4),GameBoard);
                            FadeTransition fadeTransition4 = new FadeTransition(Duration.seconds(4),difficultyLabel);
                            FadeTransition fadeTransition5 = new FadeTransition(Duration.seconds(4),score);
                            fadeTransition.setFromValue(1.0);
                            fadeTransition1.setFromValue(1.0);
                            fadeTransition2.setFromValue(1.0);
                            fadeTransition3.setFromValue(1.0);
                            fadeTransition4.setFromValue(1.0);
                            fadeTransition5.setFromValue(1.0);
                            fadeTransition.setToValue(0.0);
                            fadeTransition1.setToValue(0.0);
                            fadeTransition2.setToValue(0.0);
                            fadeTransition3.setToValue(0.0);
                            fadeTransition4.setToValue(0.0);
                            fadeTransition5.setToValue(0.0);
                            fadeTransition.setOnFinished(event -> {
                                swingNode.setContent(null); // Remove the Board component from the SwingNode
                                GameCanvas.getChildren().remove(0); // Remove the SwingNode from the GameCanvas

                                FinalScore.setText("FINAL SCORE: "+score.getText());

                                FadeTransition fadeTransition6 = new FadeTransition(Duration.seconds(4),GameOverForm);
                                FadeTransition fadeTransition7 = new FadeTransition(Duration.seconds(4),FinalScore);
                                FadeTransition fadeTransition8 = new FadeTransition(Duration.seconds(4),txtName);

                                fadeTransition6.setFromValue(0.0);
                                fadeTransition7.setFromValue(0.0);
                                fadeTransition8.setFromValue(0.0);

                                fadeTransition6.setToValue(1.0);
                                fadeTransition7.setToValue(1.0);
                                fadeTransition8.setToValue(1.0);

                                fadeTransition6.play();
                                fadeTransition7.play();
                                fadeTransition8.play();

                                fadeTransition7.setOnFinished(actionEvent -> {
                                    txtName.requestFocus();
                                });
                            });
                            fadeTransition.play();
                            fadeTransition1.play();
                            fadeTransition2.play();
                            fadeTransition3.play();
                            fadeTransition4.play();
                            fadeTransition5.play();
                        });
                    }
                });
                game.setMusicStopper(new Board.MusicStopper() {
                    @Override
                    public void musicStop() {
                        Platform.runLater(()->{
                            SFXStop();
                        });
                    }
                });
            }
        });
    }

    Clip clip;

    public void SFXPlay(String MusicName)
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

    public void SFXStop() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Stop the sound
            clip.close(); // Close the clip
        }
    }

    public void SFXCall(String MusicName)
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

    public void OnKeyPress(KeyEvent event) {
        if (event.getCode()!= KeyCode.DIGIT0 || txtName.getText().length() == 7) {
            event.consume();
        }

        if (event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.DIGIT0){
            callSFX("hover_click");
        }else{
            callSFX("clicked");
            if (event.getCode() == KeyCode.ENTER) {
                Database connect = new Database();
                try{
                    String query = "EXEC sp_inputLeaderBoard ?,?,?";
                    connect.pstat = connect.conn.prepareStatement(query);
                    connect.pstat.setString(1,txtName.getText());
                    connect.pstat.setString(2,difficultyLabel.getText());
                    connect.pstat.setInt(3,Integer.parseInt(score.getText()));
                    connect.pstat.executeUpdate();
                    connect.pstat.close();

                    SFXStop();
                    loadMainMenu(score);
                }catch (SQLException ex){
                    System.out.println("Error: "+ex.getMessage());
                }
            }

            if (event.getCode() == KeyCode.DIGIT0){
                SFXStop();
                loadMainMenu(score);
            }
        }
    }
}
