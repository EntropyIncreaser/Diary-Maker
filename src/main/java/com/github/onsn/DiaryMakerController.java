package com.github.onsn;

import com.github.onsn.data.Diary;
import com.github.onsn.data.DiaryPage;
import com.github.onsn.data.JsonConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    /**
     * Default resource bundle.
     */
    public static ResourceBundle resources = ResourceBundle.getBundle("com/github/onsn/resources/lang/diarymaker");

    /* ------------------------------------------ */
    /* --- Current Diary, DiaryPage, and File --- */
    /* ------------------------------------------ */
    public static Diary currentDiary;
    public static DiaryPage currentDiaryPage;
    public static File currentDiaryFile;

    /* ----------------- */
    /* --- GUI nodes --- */
    /* ----------------- */
    @FXML public Menu menuFile;
    @FXML public MenuItem menuItemFileNew;
    @FXML public MenuItem menuItemFileOpen;
    @FXML public MenuItem menuItemFileSave;

    @FXML public Menu menuHelp;
    @FXML public MenuItem menuItemHelpAbout;

    @FXML public TextArea areaContent;

    @FXML public Label labelDate;
    @FXML public Label labelTitle;

    @FXML public TextField fieldDate;
    @FXML public TextField fieldTitle;

    @FXML public ListView<String> listDiary;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateLang(DiaryMakerController.resources);
        listDiary.selectionModelProperty().getValue().selectedItemProperty().addListener((observable -> updatePage())); //Set the select listener.
        update();
    }

    /**
     * Update the gui language with appointed resources bundle.
     */
    public void updateLang(ResourceBundle resources) {
        menuFile.setText(resources.getString(menuFile.getText()));
        menuItemFileNew.setText(resources.getString(menuItemFileNew.getText()));
        menuItemFileOpen.setText(resources.getString(menuItemFileOpen.getText()));
        menuItemFileSave.setText(resources.getString(menuItemFileSave.getText()));

        menuHelp.setText(resources.getString(menuHelp.getText()));
        menuItemHelpAbout.setText(resources.getString(menuItemHelpAbout.getText()));

        labelDate.setText(resources.getString(labelDate.getText()));
        labelTitle.setText(resources.getString(labelTitle.getText()));
    }
    /**
     * Update the list with current diary.
     */
    public void updateList() {
        if (currentDiary == null) return;
        int select = listDiary.selectionModelProperty().getValue().getSelectedIndex();
        listDiary.setItems(FXCollections.observableArrayList(currentDiary.getAllPageTitles()));
        listDiary.selectionModelProperty().getValue().select(select);
    }
    /**
     * Update the page scene with current diary page.
     */
    public void updatePage() {
        int selectedIndex = listDiary.selectionModelProperty().getValue().getSelectedIndex();
        DiaryPage selectedPage;
        if (selectedIndex != -1) {
            selectedPage = currentDiary.getPage(selectedIndex);
        } else {
            selectedPage = new DiaryPage("", "", "");
        }

        currentDiaryPage = selectedPage;

        fieldDate.setText(selectedPage.getTime());
        fieldTitle.setText(selectedPage.getTitle());
        areaContent.setText(selectedPage.getContent());
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

    @FXML public void onTimeButtonAction() {
        fieldTitle.setText(timeFormatter.format(LocalDateTime.now()));
    }

    @FXML public void onNewAction() {
        currentDiary = new Diary();
        changeTitleFile(resources.getString("unnamed"));
        update();
    }

    @FXML public void onOpenAction() {
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

    @FXML public void onSaveAction() {
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

    @FXML public void onAddAction() {
        currentDiaryPage = new DiaryPage();
        currentDiary.addPage(currentDiaryPage);

        int i = currentDiary.indexOf(currentDiaryPage);

        update();

        listDiary.selectionModelProperty().get().select(i);

        update();
    }

    @FXML public void onSubAction() {
        int selectedIndex = listDiary.selectionModelProperty().getValue().getSelectedIndex();
        if (selectedIndex == -1) return;
        currentDiary.remove(selectedIndex);

        update();
    }

    @FXML public void onApplyAction() {
        currentDiaryPage.setTitle(fieldTitle.getText());
        currentDiaryPage.setTime(fieldDate.getText());
        currentDiaryPage.setContent(areaContent.getText());

        update();
    }

    @FXML public void onShowAboutAction() {
        AboutDialog.show();
    }
}
