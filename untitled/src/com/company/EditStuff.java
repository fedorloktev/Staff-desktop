package com.company;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class EditStuff {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button can;

    @FXML
    private TextField name;

    @FXML
    private Button save;

    @FXML
    private TextField position;

    @FXML
    private AnchorPane generalwindow;

    @FXML
    private TextField age;

    @FXML
    void initialize() throws SQLException {

// Получение возраста без пробелов
//        StuffList stuffList = new StuffList();
//
//        stuffList.list.getSelectionModel().getSelectedItems();
//        ObservableList<String> obs = stuffList.list.getSelectionModel().getSelectedItems();
//        String focus = obs.toString();
//        String name1 = focus.substring(focus.lastIndexOf("Возраст:")+9);
//        String name = name1.substring(0, focus.indexOf("  "));
//        name=name.trim();
//        System.out.println(name);
//        // Получаем имя сотрудника
//        String fname = focus.substring(2, focus.indexOf("    "));
//        System.out.println(fname);
//        // Получаем должность сотрудника
//        String position = focus.substring(focus.lastIndexOf("Должность: ")+11);
//        String pos2 = position.substring(0, position.indexOf(';'));
//        System.out.println(pos2 + "What tak ");
        StuffList st = new StuffList();

        String stuffName = st.fname1;
        String din = st.fname1;
        String stuffAge = st.age1;
        String stuffPosition = st.pos1;

        name.setText(stuffName);
        age.setText(stuffAge);
        position.setText(stuffPosition);

        DatabaseHandler dbHandler = DatabaseHandler.getInstance();

        save.setOnAction(event -> {

            String actName=name.getText();
            String actAge=age.getText();
            String actPos = position.getText();
            if (actAge.isEmpty() || actName.isEmpty() || actPos.isEmpty())
                JOptionPane.showMessageDialog(null,"Проверьте, все ли поля заполнены!", "Ошибка",JOptionPane.ERROR_MESSAGE);
            else{
            dbHandler.editStuff(din, new Stuff(0,actName,actAge,actPos));
            JOptionPane.showMessageDialog(null,"Данные изменены", "Уведомление",2);
            try{
                Stage stage = new Stage();
                Parent root3 = FXMLLoader.load(getClass().getResource("StuffList.fxml"));
                stage.setTitle("Список сотрудников");
                stage.setScene(new Scene(root3, 534, 280));
                stage.show();
                save.getScene().getWindow().hide();
            } catch (IOException e){ }}
        });



can.setOnAction(event -> {
    try{
        Stage stage = new Stage();
        Parent root3 = FXMLLoader.load(getClass().getResource("StuffList.fxml"));
        stage.setTitle("Список сотрудников");
        stage.setScene(new Scene(root3, 534, 280));
        stage.show();
    } catch (IOException e){ }
    can.getScene().getWindow().hide();
});

    }
}
