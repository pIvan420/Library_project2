package ru.pivan.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "Имя не может быть пустым")
    //Фамилия Имя Отчество
    //@Pattern(regexp = "[А-Я]\\w+, [А-Я]\\w+, [А-Я]\\w+")
    private String fullName;

    @Column(name = "birth_year")
    @Min(value = 1910, message = "Год не может быть меньше 1910")
    @Max(value = 2023, message = "Год не может быть больше 2023")
    private int birthYear;

    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public Person(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
