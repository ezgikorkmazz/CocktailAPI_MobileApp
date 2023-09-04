package com.example.project;

public class FavoriteModel {
    private String title;
    private String body1;
    private String body2;
    private String img;

    public FavoriteModel(String title, String body1, String body2, String img) {
        this.title = title;
        this.body1 = body1;
        this.body2 = body2;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody1() {
        return body1;
    }

    public void setBody1(String body1) {
        this.body1 = body1;
    }

    public String getBody2() {
        return body2;
    }

    public void setBody2(String body2) {
        this.body2 = body2;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
