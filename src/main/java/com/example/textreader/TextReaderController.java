package com.example.textreader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReaderController {

    private File loadedFileReference;

    @FXML
    public TextArea textArea;

    @FXML
    public void chooseFile(ActionEvent ae) {
        File fileToLoad = getFileChooser().showOpenDialog(null);
        if (fileToLoad != null) {
            loadFileToTextArea(fileToLoad);
            this.loadedFileReference = fileToLoad;
            TextReaderApplication.getPrimaryStage().setTitle(loadedFileReference.getName());
        }
    }

    @FXML
    public void saveFile() {
        if (loadedFileReference == null) {
            saveAsFile();
        } else {
            saveStringToFile(textArea.getText(), loadedFileReference);
        }

    }

    @FXML
    public void saveAsFile() {

        File file = getFileChooser().showSaveDialog(null);

        if (file != null) {
            loadedFileReference = file;
            saveStringToFile(textArea.getText(), file);
            TextReaderApplication.getPrimaryStage().setTitle(loadedFileReference.getName());
        }
    }

    @FXML
    public void onClose() {
        System.exit(0);
    }

    private void loadFileToTextArea(File fileToLoad) {
        try {
            Stream<String> lines = Files.lines(fileToLoad.toPath(), StandardCharsets.UTF_8);
            textArea.setText(lines.collect(Collectors.joining("\n")));
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveStringToFile(String content, File file) {
        try (BufferedReader reader = new BufferedReader(new StringReader(content));
             PrintWriter writer = new PrintWriter(new FileWriter(file, StandardCharsets.UTF_8))
        ) {
            reader.lines().forEach(writer::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }

}