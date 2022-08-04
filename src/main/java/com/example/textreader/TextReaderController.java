package com.example.textreader;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;

public class TextReaderController {

    private File loadedFileReference;

    private TextReaderService textReaderService;

    @FXML
    public TextArea textArea;

    @FXML
    public void chooseFile() {

        File fileToLoad = getFileChooser().showOpenDialog(null);
        if(fileToLoad != null){
            loadFileToTextArea(fileToLoad);
        }
    }

    @FXML
    public void saveFile() {
        try {
            FileWriter myWriter = new FileWriter(loadedFileReference);
            myWriter.write(textArea.getText());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAsFile(ActionEvent actionEvent) {

        File file = getFileChooser().showSaveDialog(null);

        if (file != null) {
            saveTextToFile(textArea.getText(), file);
        }
    }

    @FXML
    public void onClose() {
        System.exit(0);
    }

    private void loadFileToTextArea(File fileToLoad) {
        Task<TextFile> loadTask = fileLoaderTask(fileToLoad);
        loadTask.run();
    }

    private Task<TextFile> fileLoaderTask(File fileToLoad){

        Task<TextFile> loadFileTask = new Task<>() {
            @Override
            protected TextFile call() throws Exception {
                List<String> ls;
                try (Stream<String> stream = Files.lines(fileToLoad.toPath())){
                    ls = stream.collect(Collectors.toList());
                }

                return new TextFile(fileToLoad.getPath(), ls);
            }
        };


        loadFileTask.setOnSucceeded(workerStateEvent -> {
            try {
                textArea.setText(String.join("", loadFileTask.get().getContent()));
                textReaderService.setTextFile(loadFileTask.get());
            } catch (InterruptedException | ExecutionException e) {
                textArea.setText("Невозможно открыть файл:\n " + textReaderService.getTextFile().getPath());
            }
        });

        loadFileTask.setOnFailed(workerStateEvent -> {
            textArea.setText("Невозможно открыть файл:\n " + textReaderService.getTextFile().getPath());

        });
        return loadFileTask;
    }



    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private FileChooser getFileChooser(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }

}