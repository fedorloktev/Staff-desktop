package com.company;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class AddStuff {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button can;

    @FXML
    private TextField name;

    @FXML
    private TextField position;

    @FXML
    private AnchorPane generalwindow;

    @FXML
    private TextField age;

    @FXML
    void initialize() throws SQLException {

        DatabaseHandler dbHandler =  DatabaseHandler.getInstance();
add.setOnAction(event -> {
// Добавляем запись
    String sName = name.getText();
    String sAge = age.getText();
    String sPosition = position.getText();
    if (sAge.isEmpty() || sName.isEmpty() || sPosition.isEmpty())
        JOptionPane.showMessageDialog(null,"Проверьте, все ли поля заполнены!", "Ошибка",JOptionPane.ERROR_MESSAGE);
    else{
    dbHandler.addStuff(new Stuff(0,sName, sAge, sPosition));
    JOptionPane.showMessageDialog(null,"Данные внесены", "Уведомление",2);
    add.getScene().getWindow().hide();
    // Получаем все записи и выводим их на консоль
//    List<Stuff> stuffs = dbHandler.getAllStuffs();
//    for (Stuff stuff : stuffs) {
//        System.out.println(stuff.toString());
//    }
    try{
        Stage stage = new Stage();
        Parent root3 = FXMLLoader.load(getClass().getResource("StuffList.fxml"));
        stage.setTitle("Список сотрудников");
        stage.setScene(new Scene(root3, 534, 280));
        stage.show();
    } catch (IOException e){ }}
});
can.setOnAction(event -> {
    can.getScene().getWindow().hide();
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
