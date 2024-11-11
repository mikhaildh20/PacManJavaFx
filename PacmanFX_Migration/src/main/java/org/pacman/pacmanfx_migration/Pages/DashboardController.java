package org.pacman.pacmanfx_migration.Pages;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.pacman.pacmanfx_migration.Connection.Database;
import org.pacman.pacmanfx_migration.HelloApplication;
import org.pacman.pacmanfx_migration.Library;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController extends Library implements Initializable {
    @FXML
    private ImageView difficulty_option;
    @FXML
    private ImageView pressX;
    @FXML
    private Label easy_name1;

    @FXML
    private Label easy_name10;

    @FXML
    private Label easy_name2;

    @FXML
    private Label easy_name3;

    @FXML
    private Label easy_name4;

    @FXML
    private Label easy_name5;

    @FXML
    private Label easy_name6;

    @FXML
    private Label easy_name7;

    @FXML
    private Label easy_name8;

    @FXML
    private Label easy_name9;

    @FXML
    private Label easy_score1;

    @FXML
    private Label easy_score10;

    @FXML
    private Label easy_score2;

    @FXML
    private Label easy_score3;

    @FXML
    private Label easy_score4;

    @FXML
    private Label easy_score5;

    @FXML
    private Label easy_score6;

    @FXML
    private Label easy_score7;

    @FXML
    private Label easy_score8;

    @FXML
    private Label easy_score9;

    @FXML
    private ImageView ghost1;

    @FXML
    private Label hard_name1;

    @FXML
    private Label hard_name10;

    @FXML
    private Label hard_name2;

    @FXML
    private Label hard_name3;

    @FXML
    private Label hard_name4;

    @FXML
    private Label hard_name5;

    @FXML
    private Label hard_name6;

    @FXML
    private Label hard_name7;

    @FXML
    private Label hard_name8;

    @FXML
    private Label hard_name9;

    @FXML
    private Label hard_score1;

    @FXML
    private Label hard_score10;

    @FXML
    private Label hard_score2;

    @FXML
    private Label hard_score3;

    @FXML
    private Label hard_score4;

    @FXML
    private Label hard_score5;

    @FXML
    private Label hard_score6;

    @FXML
    private Label hard_score7;

    @FXML
    private Label hard_score8;

    @FXML
    private Label hard_score9;

    @FXML
    private ImageView howtoplay;

    @FXML
    private ImageView howtoplay_arrow;

    @FXML
    private ImageView htp_ghost;

    @FXML
    private ImageView htp_pac;

    @FXML
    private ImageView htp_page;

    @FXML
    private ImageView leaderboard;

    @FXML
    private ImageView leaderboard_arrow;

    @FXML
    private ImageView leaderboard_header;

    @FXML
    private Label medium_name1;

    @FXML
    private Label medium_name10;

    @FXML
    private Label medium_name2;

    @FXML
    private Label medium_name3;

    @FXML
    private Label medium_name4;

    @FXML
    private Label medium_name5;

    @FXML
    private Label medium_name6;

    @FXML
    private Label medium_name7;

    @FXML
    private Label medium_name8;

    @FXML
    private Label medium_name9;

    @FXML
    private Label medium_score1;

    @FXML
    private Label medium_score10;

    @FXML
    private Label medium_score2;

    @FXML
    private Label medium_score3;

    @FXML
    private Label medium_score4;

    @FXML
    private Label medium_score5;

    @FXML
    private Label medium_score6;

    @FXML
    private Label medium_score7;

    @FXML
    private Label medium_score8;

    @FXML
    private Label medium_score9;

    @FXML
    private ImageView pacmodel;

    @FXML
    private ImageView pink_ghost;

    @FXML
    private ImageView play;

    @FXML
    private ImageView play_arrow;

    @FXML
    private ImageView quit;

    @FXML
    private ImageView quit_arrow;

    @FXML
    private ImageView red_ghost;

    @FXML
    private ImageView title;

    private Label[] easyPlayer;
    private Label[] mediumPlayer;
    private Label[] hardPlayer;

    private Label[] easyScore;
    private Label[] mediumScore;
    private Label[] hardScore;

    private int options = 1;
    private boolean onPage = false;
    private boolean choosediff = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        difficulty_option.setVisible(false);

        easyPlayer = new Label[] {easy_name1,easy_name2,easy_name3,easy_name4,easy_name5,easy_name6,easy_name7,easy_name8,easy_name9,easy_name10};
        mediumPlayer = new Label[] {medium_name1,medium_name2,medium_name3,medium_name4,medium_name5,medium_name6,medium_name7,medium_name8,medium_name9,medium_name10};
        hardPlayer = new Label[] {hard_name1,hard_name2,hard_name3,hard_name4,hard_name5,hard_name6,hard_name7,hard_name8,hard_name9,hard_name10};

        easyScore = new Label[] {easy_score1,easy_score2,easy_score3,easy_score4,easy_score5,easy_score6,easy_score7,easy_score8,easy_score9,easy_score10};
        mediumScore = new Label[] {medium_score1,medium_score2,medium_score3,medium_score4,medium_score5,medium_score6,medium_score7,medium_score8,medium_score9,medium_score10};
        hardScore = new Label[] {hard_score1,hard_score2,hard_score3,hard_score4,hard_score5,hard_score6,hard_score7,hard_score8,hard_score9,hard_score10};

        SFXPlay("fight_song");

        ElementPerformed();
    }

    public void ElementPerformed()
    {
        title.setTranslateY(0);
        ghost1.setTranslateY(57);
        pacmodel.setTranslateY(188);
        play.setTranslateX(0);
        leaderboard.setTranslateX(0);
        howtoplay.setTranslateX(0);
        quit.setTranslateX(0);

        TranslateTransition tTrans = new TranslateTransition(Duration.seconds(1), title);
        TranslateTransition gTrans = new TranslateTransition(Duration.seconds(3),ghost1);
        TranslateTransition pTrans = new TranslateTransition(Duration.seconds(3),pacmodel);
        TranslateTransition plTrans = new TranslateTransition(Duration.seconds(2),play);
        TranslateTransition lTrans = new TranslateTransition(Duration.seconds(3),leaderboard);
        TranslateTransition hTrans = new TranslateTransition(Duration.seconds(4),howtoplay);
        TranslateTransition qTrans = new TranslateTransition(Duration.seconds(5),quit);

        tTrans.setFromY(-390);
        gTrans.setFromY(-188);
        pTrans.setFromY(-595);
        plTrans.setFromX(-306);
        lTrans.setFromX(-306);
        hTrans.setFromX(-306);
        qTrans.setFromX(-306);

        tTrans.setToY(0);
        gTrans.setToY(57);
        pTrans.setToY(188);
        plTrans.setToX(0);
        lTrans.setToX(0);
        hTrans.setToX(0);
        qTrans.setToX(0);

        tTrans.play();
        gTrans.play();
        pTrans.play();
        plTrans.play();
        lTrans.play();
        hTrans.play();
        qTrans.play();

        qTrans.setOnFinished(event -> {
            play_arrow.setVisible(true);
        });
    }

    public void LeaderboardPerformed()
    {
        Database connect = new Database();
        try{
            String query = "SELECT TOP 10 * FROM LeaderBoard WHERE difficulty = 'EASY' ORDER BY score DESC";
            connect.stat = connect.conn.createStatement();
            connect.result = connect.stat.executeQuery(query);
            int i = 0;
            while (connect.result.next())
            {
                easyPlayer[i].setText(connect.result.getString("player"));
                easyScore[i].setText(connect.result.getString("score"));
                i++;
            }
            connect.stat.close();
            connect.result.close();
        }catch (SQLException ex){
            System.out.println(STR."Error: \{ex.getMessage()}");
        }

        try{
            String query = "SELECT TOP 10 * FROM LeaderBoard WHERE difficulty = 'MEDIUM' ORDER BY score DESC";
            connect.stat = connect.conn.createStatement();
            connect.result = connect.stat.executeQuery(query);
            int i = 0;
            while (connect.result.next())
            {
                mediumPlayer[i].setText(connect.result.getString("player"));
                mediumScore[i].setText(connect.result.getString("score"));
                i++;
            }
            connect.stat.close();
            connect.result.close();
        }catch (SQLException ex){
            System.out.println(STR."Error: \{ex.getMessage()}");
        }

        try{
            String query = "SELECT TOP 10 * FROM LeaderBoard WHERE difficulty = 'HARD' ORDER BY score DESC";
            connect.stat = connect.conn.createStatement();
            connect.result = connect.stat.executeQuery(query);
            int i = 0;
            while (connect.result.next())
            {
                hardPlayer[i].setText(connect.result.getString("player"));
                hardScore[i].setText(connect.result.getString("score"));
                i++;
            }
            connect.stat.close();
            connect.result.close();
        }catch (SQLException ex){
            System.out.println(STR."Error: \{ex.getMessage()}");
        }

        enableAllLeaderboard();

        pink_ghost.setTranslateY(44); // set initial position above the screen
        red_ghost.setTranslateY(24);

        TranslateTransition pink = new TranslateTransition(Duration.seconds(2), pink_ghost);
        TranslateTransition red = new TranslateTransition(Duration.seconds(2),red_ghost);

        pink.setFromY(-156);
        red.setFromY(-196);

        pink.setToY(44);
        red.setToY(24);

        pink.setCycleCount(1);
        red.setCycleCount(1);

        pink.setAutoReverse(false);
        red.setAutoReverse(false);

        pink.play();
        red.play();

    }

    public void disableAllLeaderboard()
    {
        pressX.setVisible(false);
        leaderboard_header.setVisible(false);

        red_ghost.setVisible(false);
        pink_ghost.setVisible(false);

        for (int i = 0;i<10;i++)
        {
            easyPlayer[i].setVisible(false);
            mediumPlayer[i].setVisible(false);
            hardPlayer[i].setVisible(false);

            easyScore[i].setVisible(false);
            mediumScore[i].setVisible(false);
            hardScore[i].setVisible(false);
        }
    }

    public void enableAllLeaderboard()
    {
        pressX.setVisible(true);
        leaderboard_header.setVisible(true);

        red_ghost.setVisible(true);
        pink_ghost.setVisible(true);

        for (int i = 0;i<10;i++)
        {
            easyPlayer[i].setVisible(true);
            mediumPlayer[i].setVisible(true);
            hardPlayer[i].setVisible(true);

            easyScore[i].setVisible(true);
            mediumScore[i].setVisible(true);
            hardScore[i].setVisible(true);
        }

        title.setVisible(false);
        ghost1.setVisible(false);
        pacmodel.setVisible(false);
        play.setVisible(false);
        leaderboard.setVisible(false);
        howtoplay.setVisible(false);
        quit.setVisible(false);
        play_arrow.setVisible(false);
        leaderboard_arrow.setVisible(false);
        howtoplay_arrow.setVisible(false);
        quit_arrow.setVisible(false);
    }

    public void loadGame(int choice) {
        SFXStop();
        try {
            PauseTransition pause = new PauseTransition(Duration.millis(500));
            pause.setOnFinished(event -> {
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Page/Game.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Get the GameController instance
                GameController gameController = loader.getController();

                // Set the difficulty and ghosts
                gameController.getGameDetails(choice);

                // Get the current stage
                Stage stage = (Stage) leaderboard_arrow.getScene().getWindow();

                // Get the current scene
                Scene currentScene = stage.getScene();

                // Get the window of the current scene
                Window window = currentScene.getWindow();

                // Close the window (which will close the LoadingController)
                ((Stage) window).close();

                // Set the new scene
                Scene newScene = new Scene(root);
                newScene.getRoot().cursorProperty().set(Cursor.NONE);

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
            });
            pause.play();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void HowToPlayPerformed()
    {
        title.setVisible(false);
        ghost1.setVisible(false);
        pacmodel.setVisible(false);
        play.setVisible(false);
        leaderboard.setVisible(false);
        howtoplay.setVisible(false);
        quit.setVisible(false);
        htp_ghost.setVisible(true);
        htp_pac.setVisible(true);
        htp_page.setVisible(true);
        howtoplay_arrow.setVisible(false);
    }

    public void disableAllArorow()
    {
        play_arrow.setVisible(true);
        leaderboard_arrow.setVisible(false);
        howtoplay_arrow.setVisible(false);
        quit_arrow.setVisible(false);
    }

    public void enableChooseGame()
    {
        difficulty_option.setVisible(true);
        play.setVisible(false);
        leaderboard.setVisible(false);
        howtoplay.setVisible(false);
        quit.setVisible(false);

        disableAllArorow();
        options = 1;
    }

    public void disableChooseGame()
    {
        difficulty_option.setVisible(false);
        play.setVisible(true);
        leaderboard.setVisible(true);
        howtoplay.setVisible(true);
        quit.setVisible(true);

        disableAllArorow();
        options = 1;
    }

    public void setScene(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (onPage && event.getCode() == KeyCode.X){
                callSFX("clicked");
                disableAllLeaderboard();
                options = 1;
                onPage = false;
                htp_ghost.setVisible(false);
                htp_pac.setVisible(false);
                htp_page.setVisible(false);
                title.setVisible(true);
                ghost1.setVisible(true);
                pacmodel.setVisible(true);
                play.setVisible(true);
                leaderboard.setVisible(true);
                howtoplay.setVisible(true);
                quit.setVisible(true);
                ElementPerformed();
                return;
            }

            if (onPage && event.getCode() != KeyCode.X)
            {
                return;
            }

            if(!choosediff){

                if (event.getCode() == KeyCode.ENTER){
                    callSFX("clicked");
                    switch (options)
                    {
                        case 1:
                            choosediff = true;
                            enableChooseGame();
                            break;
                        case 2:
                            onPage = true;
                            LeaderboardPerformed();
                            break;
                        case 3:
                            onPage = true;
                            HowToPlayPerformed();
                            break;
                        case 4:
                            SFXStop();
                            Platform.exit();
                            break;
                    }
                }

            }else {

                if (event.getCode() == KeyCode.ENTER){
                    callSFX("clicked");
                    switch (options)
                    {
                        case 1:
                            loadGame(1);
                            break;
                        case 2:
                            loadGame(2);
                            break;
                        case 3:
                            loadGame(3);
                            break;
                        case 4:
                            choosediff = false;
                            disableChooseGame();
                            break;
                    }
                }

            }

            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN){
                    if (event.getCode() == KeyCode.UP && options == 1 || event.getCode() == KeyCode.DOWN && options == 4){
                        return;
                    }

                    callSFX("hover_click");

                    if (event.getCode() == KeyCode.UP) options--;
                    else options++;

                    switch (options){
                        case 1:
                            play_arrow.setVisible(true);
                            leaderboard_arrow.setVisible(false);
                            howtoplay_arrow.setVisible(false);
                            quit_arrow.setVisible(false);
                            break;
                        case 2:
                            play_arrow.setVisible(false);
                            leaderboard_arrow.setVisible(true);
                            howtoplay_arrow.setVisible(false);
                            quit_arrow.setVisible(false);
                            break;
                        case 3:
                            play_arrow.setVisible(false);
                            leaderboard_arrow.setVisible(false);
                            howtoplay_arrow.setVisible(true);
                            quit_arrow.setVisible(false);
                            break;
                        case 4:
                            play_arrow.setVisible(false);
                            leaderboard_arrow.setVisible(false);
                            howtoplay_arrow.setVisible(false);
                            quit_arrow.setVisible(true);
                            break;
                    }
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
}
