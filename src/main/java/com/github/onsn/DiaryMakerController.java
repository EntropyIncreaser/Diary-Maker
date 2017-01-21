package com.github.onsn;

import com.github.onsn.data.Diary;
import com.github.onsn.data.DiaryPage;
import com.github.onsn.data.JsonConverter;
import javafx.collections.FXCollections;
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
    public static DiaryPage currentDiaryPage;
    public static File currentDiaryFile;

    @FXML
    public final Label timeLabel;
    @FXML
    public final TextField timeField;

    @FXML
    public final Label titleLabel;
    @FXML
    public final TextField titleField;

    @FXML
    public final TextArea contentArea;

    @FXML
    public final ListView<String> list;
    public Label contentLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resources = DiaryMakerController.resources;
        timeLabel.setText(resources.getString("field.time"));
        titleLabel.setText(resources.getString("field.title"));

        list.selectionModelProperty().getValue().selectedItemProperty().addListener((observable -> updatePage()));

        update();
    }

    /**
     * Update the list with current diary.
     */
    public void updateList() {
        if (currentDiary == null) return;
        int select = list.selectionModelProperty().getValue().getSelectedIndex();
        list.setItems(FXCollections.observableArrayList(currentDiary.getAllPageTitles()));
        list.selectionModelProperty().getValue().select(select);
    }

    /**
     * Update the page scene with current diary page.
     */
    public void updatePage() {
        int selectedIndex = list.selectionModelProperty().getValue().getSelectedIndex();
        DiaryPage selectedPage;
        if (selectedIndex != -1) {
            selectedPage = currentDiary.getPage(selectedIndex);
        } else {
            selectedPage = new DiaryPage("", "", "");
        }

        currentDiaryPage = selectedPage;

        timeField.setText(selectedPage.getTime());
        titleField.setText(selectedPage.getTitle());
        contentArea.setText(selectedPage.getContent());
    }

    /**
     * Update the scene.
     */
    public void update() {
        updateList();
        updatePage();
    }

    /**
     * Set the stage title like this:<br/>
     * '$value$ - DiaryMaker v$X$.$x$'
     */
    public void changeTitleFile(String value) {
        DiaryMaker.getPrimaryStage().setTitle(value + " - " + resources.getString("title"));
    }

    /* ----------------------- */
    /* --- Action Listeners --- */
    /* ----------------------- */

    @FXML
    public void onTimeButtonAction() {
        timeField.setText(timeFormatter.format(LocalDateTime.now()));
    }

    @FXML
    public void onNewAction() {
        currentDiary = new Diary();
        changeTitleFile(resources.getString("unnamed"));
        update();
    }

    @FXML
    public void onOpenAction() {
        FileChooser fc = new FileChooser();
        fc.setTitle(resources.getString("chooseFile"));
        File open = fc.showOpenDialog(DiaryMaker.getPrimaryStage());
        if (open == null) return;
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
        update();
    }

    @FXML
    public void onSaveAction() {
        if (currentDiaryFile == null) {
            FileChooser fc = new FileChooser();
            fc.setTitle(resources.getString("chooseFile"));
            fc.setInitialFileName(resources.getString("newFile"));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Json", ".json", "*.json"));

            File save = fc.showSaveDialog(DiaryMaker.getPrimaryStage());
            if (save == null) return;
            System.out.println("is not null");
            System.out.println(save.getAbsoluteFile());
            try {
                System.out.println("is file");
                if (!save.exists()) {
                    System.out.println("is not ex");
                    boolean newFile = save.createNewFile();
                    if (!newFile) throw new IOException("Can't create " + save.getAbsolutePath());
                }
                Files.write(save.toPath(), JsonConverter.toJson(currentDiary).getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentDiaryFile = save;
            changeTitleFile(save.getName());
        } else {
            try {
                System.out.println("is file");
                if (!currentDiaryFile.exists()) {
                    System.out.println("is not ex");
                    boolean newFile = currentDiaryFile.createNewFile();
                    if (!newFile) throw new IOException("Can't create " + currentDiaryFile.getAbsolutePath());
                }
                Files.write(currentDiaryFile.toPath(), JsonConverter.toJson(currentDiary).getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            changeTitleFile(currentDiaryFile.getName());
        }

        update();
    }

    @FXML
    public void onAddAction() {
        currentDiaryPage = new DiaryPage();
        currentDiary.addPage(currentDiaryPage);

        int i = currentDiary.indexOf(currentDiaryPage);

        update();

        list.selectionModelProperty().get().select(i);

        update();
    }

    @FXML
    public void onSubAction() {
        int selectedIndex = list.selectionModelProperty().getValue().getSelectedIndex();
        if (selectedIndex == -1) return;
        currentDiary.remove(selectedIndex);

        update();
    }

    @FXML
    public void onApplyAction() {
        currentDiaryPage.setTitle(titleField.getText());
        currentDiaryPage.setTime(timeField.getText());
        currentDiaryPage.setContent(contentArea.getText());

        update();
    }
}
