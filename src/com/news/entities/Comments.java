package com.news.entities;

import java.sql.Timestamp;

public class Comments {

    private Long id;
    private String comment;
    private Timestamp post_date;
    private Users author;  //автор комментария
    private News_Content blog;

    public Comments() {}
    public Comments(Long id, String comment, Timestamp post_date, Users author, News_Content blog) {
        this.id = id;
        this.comment = comment;
        this.post_date = post_date;
        this.author = author;
        this.blog = blog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPost_date() {
        return post_date;
    }

    public void setPost_date(Timestamp post_date) {
        this.post_date = post_date;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public News_Content getBlog() {
        return blog;
    }

    public void setBlog(News_Content blog) {
        this.blog = blog;
    }
}
