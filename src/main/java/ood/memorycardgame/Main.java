package ood.memorycardgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main-menu.fxml"));

        double width = 1280,height =720;
        boolean fullScreen;

        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Memory GameModel - Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}