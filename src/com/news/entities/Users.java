package com.news.entities;

public class Users {

    private Long id;
    private String email;
    private String password;
    private String full_name;
    private int role_id;  //если 1 то это админ, если 2 то простой пользователь

    public Users() {}
    public Users(Long id, String email, String password, String full_name, int role_id) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.role_id = role_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
