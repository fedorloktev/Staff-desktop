package com.company;

public class Stuff {
    // Поля класса
    public int idstuff;

    public String name;

    public String age;

    public String position;

    // Конструктор
    public Stuff(int idstuff, String name, String age, String position) {
        this.idstuff = idstuff;
        this.name = name;
        this.age = age;
        this.position = position;
    }

    // Выводим информацию по продукту
    @Override
    public String toString() {
        return String.format(" %s             Возраст: %s              Должность: %s;",
                this.name, age, position);
    }
}