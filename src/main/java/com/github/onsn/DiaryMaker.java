package com.github.onsn;


import com.github.onsn.support.Resources;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by OnSN on 2017/1/9.
 *
 * @author OnSN
 * @version 1.0
 */
public class DiaryMaker extends Application {
    /**
     * The primary stage of the whole application.
     */
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Resources.get("/com/github/onsn/resources/gui/DiaryMaker.fxml"));
        Scene primaryScene;

        BorderPane root = null;

        try {
            root = fxmlLoader.load(); //Load FXML.
        } catch (IOException e) {
            System.err.println("Can't load: " + fxmlLoader.getLocation());
            e.printStackTrace();
        } finally {
            if (Objects.isNull(root)) {
                System.err.println("The root parent is null.");
                System.exit(1);
            }
        }

        primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle(DiaryMakerController.resources.getString("title"));

        stage = primaryStage;
        primaryStage.show();
    }

    /**
     * Get the primary stage.
     */
    public static Stage getPrimaryStage() {
        return stage;
    }
}
