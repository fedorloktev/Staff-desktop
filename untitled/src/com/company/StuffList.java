package com.company;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class StuffList {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private Button del;

    @FXML
    private AnchorPane generalwindow;

    @FXML
    private ListView<String> list;
    public static String fname1;
    public static String age1;
    public static String pos1;

    @FXML
    void initialize() {
        list = new ListView();
        list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list.setMaxSize(770, 200);
        list.setPrefSize(478, 200);
        list.setLayoutX(26);
        list.setLayoutY(14);
        generalwindow.getChildren().addAll(new Node[]{this.list});

        add.setOnAction(event -> {
    try{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddStuff.fxml"));
        stage.setTitle("Добавление сотрудника");
        stage.setScene(new Scene(root, 512, 113));
        stage.setResizable(false);
        stage.show();
        add.getScene().getWindow().hide();
    } catch (IOException e){ }
        });

        del.setOnAction(event -> {
        list.getSelectionModel().getSelectedItems();
            ObservableList<String> obs = list.getSelectionModel().getSelectedItems();
            String focus = obs.toString();
            System.out.println(focus);
// режем строку от пробела вначале до отступа вконце. получаем ИМЯ сотрудника
            String name = focus.substring(2, focus.indexOf("    "));
            // Удаление записи с именем
            DatabaseHandler dbHandler = null;
            try {
                dbHandler = DatabaseHandler.getInstance();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbHandler.deleteStuff(name);
            // Получаем все записи и выводим их на консоль
            List<Stuff> stuffs = dbHandler.getAllStuffs();
            list.getItems().clear();
            for (Stuff stuff : stuffs) {
                list.getItems().add(stuff.toString());
            }

        });
        //-----norm code end


// ----- DB
        try {
            // Создаем экземпляр по работе с БД
            DatabaseHandler dbHandler = DatabaseHandler.getInstance();

            // Получаем все записи и выводим их на консоль
            List<Stuff> stuffs = dbHandler.getAllStuffs();
            for (Stuff stuff : stuffs) {
                list.getItems().add(stuff.toString());
            }

           ////////////////////////////////////////////////////// list = Lis
            // Удаление записи с id = 8
            //dbHandler.deleteProduct(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // --- BD
    edit.setOnAction(event -> {
        try{
            list.getSelectionModel().getSelectedItems();
            ObservableList<String> obs = list.getSelectionModel().getSelectedItems();
            String focus = obs.toString();
            // Получаем имя сотрудника
            this.fname1 = focus.substring(2, focus.indexOf("    "));
            //System.out.println(fname1);


         //Получение возраста без пробелов
        //list.getSelectionModel().getSelectedItems();
        //ObservableList<String> obs = list.getSelectionModel().getSelectedItems();
        //String focus = obs.toString();
        String name1 = focus.substring(focus.lastIndexOf("Возраст:")+9);
        String name = name1.substring(0, focus.indexOf("  "));
        this.age1=name.trim();
        //System.out.println(name);
       // Получаем имя сотрудника
        String fname = focus.substring(2, focus.indexOf("    "));
       // System.out.println(fname);
        // Получаем должность сотрудника
        String position = focus.substring(focus.lastIndexOf("Должность: ")+11);
        this.pos1 = position.substring(0, position.indexOf(';'));


            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("EditStuff.fxml"));
            stage.setTitle("Изменение данных - "+ fname1);
            stage.setScene(new Scene(root, 512, 113));
            stage.setResizable(false);
            stage.show();
            add.getScene().getWindow().hide();
        } catch (IOException e){ }
        //System.out.println(fname1+age1+pos1);
    });

    }
}
