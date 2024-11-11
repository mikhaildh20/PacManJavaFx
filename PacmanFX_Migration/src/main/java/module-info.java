module org.pacman.pacmanfx_migration {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;
    requires javafx.swing;
    requires java.sql;
    requires javafx.media;

    opens org.pacman.pacmanfx_migration to javafx.fxml;
    exports org.pacman.pacmanfx_migration;
    exports org.pacman.pacmanfx_migration.Game;
    opens org.pacman.pacmanfx_migration.Game to javafx.fxml;
    exports org.pacman.pacmanfx_migration.Connection;
    opens org.pacman.pacmanfx_migration.Connection to javafx.fxml;
    exports org.pacman.pacmanfx_migration.Pages;
    opens org.pacman.pacmanfx_migration.Pages to javafx.fxml;
}