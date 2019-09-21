package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Main extends Application {
    private RunParser runParser;
    private String pathFile;
    private Button openPathFile = new Button("Путь файла");
    private Button start = new Button("Старт");
    private Button stop = new Button("Стоп");
    private TextField search1 = new TextField();
    private TextField search2 = new TextField();
    private TextField searchId1 = new NumberTextField();
    private TextField searchId2 = new NumberTextField();
    private Label lbl1 = new Label("введи слово №-1");
    private Label lbl2 = new Label("введи слово №-2");
    private Label lbl3 = new Label("введи число №-1");
    private Label lbl4 = new Label("введи число №-2");
    private Label lbl5 = new Label("путь файла: отсутствует!!!");
    private Label lbl6 = new Label();
    private DirectoryChooser directoryChooser = new DirectoryChooser();
    private GridPane root = new GridPane();
    private ColumnConstraints columnConstraints = new ColumnConstraints();
    private ColumnConstraints columnConstraints1 = new ColumnConstraints();
    private Scene scene = new Scene(root, 400, 300);

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) {
        openPathFile.setPrefSize(100, 10);
        directoryChooser.setTitle("Open Document");
        start.setPrefSize(100, 10);
        stop.setPrefSize(100, 10);
        lbl5.setTextFill(Color.web("#FF0000"));
        lbl5.setWrapText(true);
        root.setGridLinesVisible(false);
        root.add(lbl1, 0, 1);
        root.add(search1, 0, 2);
        root.add(lbl2, 0, 3);
        root.add(search2, 0, 4);
        root.add(lbl3, 0, 5);
        root.add(searchId1, 0, 6);
        root.add(lbl4, 0, 7);
        root.add(searchId2, 0, 8);
        root.add(openPathFile, 2, 1);
        root.add(start, 2, 2);
        root.add(stop, 2, 3);
        root.add(lbl5, 0, 9);
        root.add(lbl6, 2, 5);
        columnConstraints.setPrefWidth(270.0);
        columnConstraints1.setPercentWidth(20);
        root.getColumnConstraints().addAll(columnConstraints, columnConstraints1);
        stage.setTitle("Поиск в Vk");
        stage.setScene(scene);
        stage.show();

        openPathFile.setOnAction(event -> {
            pathFile = directoryChooser.showDialog(stage).getPath();
            lbl5.setTextFill(Color.web("#000000"));
            lbl5.setText("путь файла: " + pathFile + "/ParseVk.txt");
        });

        start.setOnAction(actionEvent -> {
            lbl6.setText("START!!!");
            int idPage1 = Integer.parseInt(String.valueOf(searchId1.getCharacters()));
            int idPage2 = Integer.parseInt(String.valueOf(searchId2.getCharacters()));
            String search11 = String.valueOf(search1.getCharacters());
            String search22 = String.valueOf(search2.getCharacters());
            runParser = new RunParser(pathFile, idPage1, idPage2, search11, search22);
            Thread thread = new Thread(runParser);
            thread.start();

            new Thread(() -> {
                try {
                    thread.join();
                    Platform.runLater(() -> lbl6.setText("STOP!"));
                    this.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });

        stop.setOnAction(event -> {
            if (runParser != null) {
                runParser.disable();
                lbl6.setText("STOP!");
            }
        });

        stage.setOnCloseRequest(e -> {
            if (runParser != null) {
                runParser.disable();
            }
            Platform.exit();
            System.exit(0);
        });
    }
}