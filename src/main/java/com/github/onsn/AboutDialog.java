package com.github.onsn;

import com.github.onsn.support.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by OnSN on 2017/1/23.
 *
 * @author OnSN
 * @version 1.0
 */
public class AboutDialog {
    public static AboutDialog singleton;
    private Stage dialogStage;

    private AboutDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Resources.get("/com/github/onsn/resources/gui/AboutDialog.fxml"));
        StackPane s;

        s = loader.load();

        dialogStage = new Stage();
        dialogStage.setScene(new Scene(s, 400, 170));
        dialogStage.setTitle("About");
        dialogStage.setResizable(false);
    }

    public static void show() {
        if (singleton == null) {
            try {
                singleton = new AboutDialog();
            } catch (IOException e) {
                return;
            }
        }
        singleton.dialogStage.show();
    }
}
