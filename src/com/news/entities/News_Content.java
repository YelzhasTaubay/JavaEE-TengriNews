package com.news.entities;

import java.sql.Timestamp;

public class News_Content {

    private Long id;
    private String title;
    private String content;
    private Timestamp post_date;
    private News_Category category;
    private News_Language language;
    private Users author;


    public News_Content() {}
    public News_Content(Long id, String title, String content, Timestamp post_date, News_Category category, News_Language language,Users author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.post_date = post_date;
        this.category = category;
        this.language = language;
        this.author=author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    public News_Category getCategory() {
        return category;
    }

    public void setCategory(News_Category category) {
        this.category = category;
    }

    public News_Language getLanguage() {
        return language;
    }

    public void setLanguage(News_Language language) {
        this.language = language;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public Users getAuthor() {
        return author;
    }
}
