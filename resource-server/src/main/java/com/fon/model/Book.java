package com.fon.model;

@SuppressWarnings("unused")
public class Book {

    private String title;
    private String author;
    private String description;
    private String imgUrl;

    public Book(String title, String author, String description, String imgUrl) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
