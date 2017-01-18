package com.github.onsn;

import com.github.onsn.data.Diary;
import com.github.onsn.data.DiaryPage;
import com.github.onsn.data.JsonConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Created by OnSN on 2017/1/14.
 *
 * @author OnSN
 * @version 1.0
 */
public class DiaryMakerController implements Initializable {
    /**
     * A date time formatter of the pattern: 'YYYY-MM-dd a hh:mm:ss'.
     */
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd a hh:mm:ss");
    public static final ResourceBundle resources = ResourceBundle.getBundle("com/github/onsn/resources/lang/diarymaker");

    /**
     * The current diary.
     */
    public static Diary currentDiary;
    public static File currentDiaryFile;

    @FXML public Label timeLabel;
    @FXML public TextField timeField;

    @FXML public Label titleLabel;
    @FXML public TextField titleField;

    @FXML public TextArea contentArea;

    @FXML public ListView<String> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resources = DiaryMakerController.resources;
        timeLabel.setText(resources.getString("field.time"));
        titleLabel.setText(resources.getString("field.title"));
        currentDiary = new Diary(new DiaryPage("2017.1.5", "ADASD", "AAAAA"), new DiaryPage("2017.1.1", "ADASD", "AAAAA"), new DiaryPage("2017.1.2", "ADASD", "AAAAA"));
        updateScene();
    }

    @FXML public void onTimeButtonAction() {
        timeField.setText(timeFormatter.format(LocalDateTime.now()));
    }

    @FXML public void onNewAction() {
        currentDiary = new Diary();
        changeTitleFile(resources.getString("unnamed"));
    }

    @FXML public void onOpenAction() {
        FileChooser fc = new FileChooser();
        fc.setTitle(resources.getString("choosefile"));
        File open = fc.showOpenDialog(DiaryMaker.getPrimaryStage());
        if (open.isFile() && open.exists()) {
            try {
                byte[] bytes = Files.readAllBytes(open.toPath());
                currentDiaryFile = open;
                currentDiary = JsonConverter.fromJson(new String(bytes, "utf-8"));
                changeTitleFile(currentDiaryFile.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML public void onSaveAction() {
        FileChooser fc = new FileChooser();
        fc.setTitle(resources.getString("choosefile"));
        File save = fc.showSaveDialog(DiaryMaker.getPrimaryStage());
        try {
            if (save.isFile()) {
                if (!save.exists()) {
                    boolean newFile = save.createNewFile();
                    if (!newFile) throw new IOException("Can't create " + save.getAbsolutePath());
                }
                Files.write(save.toPath(), JsonConverter.toJson(currentDiary).getBytes("utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateScene() {
        if (currentDiary == null) return;
        list.getItems().addAll(currentDiary.getAllPageTitles());
    }


    /**
     * Set the stage title like this:<br/>
     * '$value$ - DiaryMaker v$X$.$x$'
     */
    public void changeTitleFile(String value) {
        DiaryMaker.getPrimaryStage().setTitle(value + " - " + resources.getString("title"));
    }
}
