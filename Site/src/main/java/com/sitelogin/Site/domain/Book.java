package com.sitelogin.Site.domain;

import javax.persistence.*;

@Entity
@Table(name = "book_tbl", uniqueConstraints=@UniqueConstraint(columnNames={"id"}))
public class Book {



    @Column(name="id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    private String title;
    private String author;

    @Column(name="publishing_house")
    private String publishingHouse;

    @Column(name="year_of_release")
    private int yearOfRelease;
    private int cantity;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public int getCantity() {
        return cantity;
    }

    public void setCantity(int cantity) {
        this.cantity = cantity;
    }

    public Book(String title, String author, String publishingHouse, int yearOfRelease, int cantity) {
        this.title = title;
        this.author = author;
        this.cantity = cantity;
        this.publishingHouse = publishingHouse;
        this.yearOfRelease = yearOfRelease;
    }
    public Book(){}
}
