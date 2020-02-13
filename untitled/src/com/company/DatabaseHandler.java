package com.company;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHandler  {
    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:C:/SQLite/stuff.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DatabaseHandler instance = null;

    public static synchronized DatabaseHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DatabaseHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DatabaseHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public List<Stuff> getAllStuffs() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Stuff> stuffs = new ArrayList<Stuff>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT idstuff, name, age, position FROM stuff");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                stuffs.add(new Stuff(resultSet.getInt("idstuff"),
                        resultSet.getString("name"),
                        resultSet.getString("age"),
                        resultSet.getString("position")));
            }
            // Возвращаем наш список
            return stuffs;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление продукта в БД
    public void addStuff(Stuff stuff) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Stuff(`name`, `age`, `position`) " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, stuff.name);
            statement.setObject(2, stuff.age);
            statement.setObject(3, stuff.position);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление продукта по id
    public void deleteStuff(String name) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Stuff WHERE name = ?")) {
            statement.setObject(1, name);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void editStuff(String name, Stuff stuff) {

        try (PreparedStatement dstatement = this.connection.prepareStatement(
                "DELETE FROM Stuff WHERE name = ?")) {
            dstatement.setObject(1, name);
            // Выполняем запрос
            dstatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();}


            try (PreparedStatement statement = this.connection.prepareStatement(
                    "INSERT INTO Stuff(`name`, `age`, `position`) " +
                            "VALUES(?, ?, ?)")) {
                statement.setObject(1, stuff.name);
                statement.setObject(2, stuff.age);
                statement.setObject(3, stuff.position);
                // Выполняем запрос
                statement.execute();
            } catch (SQLException ew) {
                ew.printStackTrace();
            }

        // потом создаем
    }
}