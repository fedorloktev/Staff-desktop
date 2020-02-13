package com.company;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.util.calendar.CalendarDate;
import sun.util.resources.cldr.ru.CalendarData_ru_RU;

import javax.swing.JOptionPane;

public class StartAdmin {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text helpJoin;

    @FXML
    private Button back;

    @FXML
    private Text helpPass;

    @FXML
    private Button join_admin;

    @FXML
    private AnchorPane generalwindow;

    @FXML
    private PasswordField admin_password;

    @FXML
    private Text helpLogin;

    @FXML
    private TextField admin_login;

    @FXML
    private Button spravka;

    @FXML
    void initialize(ActionEvent event) {
    }

    @FXML
    void initialize() {


        back.setOnAction((event) -> {
back.getScene().getWindow().hide();
        });
        join_admin.setOnAction((event) -> {
            String loginText = admin_login.getText().trim();
            String passwordText = admin_password.getText().trim();
            if (!loginText.isEmpty() && !passwordText.isEmpty()) {
                if (loginText.equals("admin") && passwordText.equals("admin")) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("Desktop.fxml"));
                        Scene scene = new Scene((Parent)fxmlLoader.load(), 580, 400);
                        Stage stage = new Stage();
                        stage.setTitle("NET-Work - Рабочая область");
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();
                        //stage.setResizable(false);
                        join_admin.getScene().getWindow().hide();
                    } catch (IOException var7) {
                    }
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Неверный логин или пароль!", "Ошибка", 0);
                }
            } else {
                JOptionPane.showMessageDialog((Component)null, "Введите логин и пароль!", "Ошибка", 0);
            }
        });
        FadeTransition hL = new FadeTransition(Duration.seconds(1.0), helpLogin);
        hL.setFromValue(1.0);
        hL.setToValue(0.1);
        hL.setCycleCount(-1);
        hL.setAutoReverse(true);
        hL.play();

        FadeTransition hP = new FadeTransition(Duration.seconds(1.0), helpPass);
        hP.setFromValue(1.0);
        hP.setToValue(0.1);
        hP.setCycleCount(-1);
        hP.setAutoReverse(true);
        hP.play();

        FadeTransition hJ = new FadeTransition(Duration.seconds(1.0), helpJoin);
        hJ.setFromValue(1.0);
        hJ.setToValue(0.1);
        hJ.setCycleCount(-1);
        hJ.setAutoReverse(true);
        hJ.play();
        helpPass.setVisible(true);

        admin_login.setOnKeyTyped((event) -> {
            if (admin_login.getText().isEmpty()) {
                helpLogin.setVisible(true);
            } else {helpLogin.setVisible(false);}

            if (!admin_password.getText().isEmpty() && !admin_login.getText().isEmpty()){
                helpJoin.setVisible(true);
            }else{helpJoin.setVisible(false);}
        });

        admin_password.setOnKeyTyped((event) -> {
            if (admin_password.getText().isEmpty()) {
                helpPass.setVisible(true);
            } else {helpPass.setVisible(false);}

            if (!admin_password.getText().isEmpty() && !admin_login.getText().isEmpty()){
                helpJoin.setVisible(true);
            }else{helpJoin.setVisible(false);}
        });
    }
}
