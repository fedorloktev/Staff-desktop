package com.company;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class Desktop {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text clock2;

    @FXML
    private MenuItem note;

    @FXML
    private AnchorPane generalwindow;

    @FXML
    private MenuItem stuffList;
    private int minute;
    private int hour;
    @FXML
    void initialize() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, event -> {
            minute = LocalDateTime.now().getMinute();
            hour = LocalDateTime.now().getHour();
            if (minute<10) clock2.setText(hour +":0"+(minute));
            else clock2.setText(hour +":"+(minute));
        }),
                new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        note.setOnAction(event -> {
            try{
            Stage stage = new Stage();
                Parent root2 = FXMLLoader.load(getClass().getResource("Note.fxml"));
            stage.setTitle("Блокнот");
            stage.setScene(new Scene(root2, 454, 265));
            stage.show();
            } catch (IOException e){ }
        });

        stuffList.setOnAction(event -> {
            try{
                Stage stage = new Stage();
                Parent root3 = FXMLLoader.load(getClass().getResource("StuffList.fxml"));
                stage.setTitle("Список сотрудников");
                stage.setScene(new Scene(root3, 534, 280));
                stage.show();
            } catch (IOException e){ }
        });
    }
}
