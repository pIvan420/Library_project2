package ru.pivan.Project2Boot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;


@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;


    @Column(name = "name")
    @NotEmpty(message = "Название не может быть пустым")
    private String name;

    @Column(name = "author")
    @Size(min = 1, max = 200, message = "Имя автора может включать до 200 символов")
    private String author;


    @Column(name = "publish_year")
    @Min(value = 867, message = "Год не может быть меньше 867")
    @Max(value = 2023, message = "Год не может быть больше 2023")
    private int publishYear;

    @Column(name = "date_received")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReceived;

    @Transient
    private boolean isOverdue;

    public Book(String name, String author, int publishYear) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    @Override
    public String toString() {
        return "Book{" +
                "person=" + person +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishYear=" + publishYear +
                '}';
    }
}
