package com.studiofive.myedu.classes;

public class Quiz {
    private String image;
    private String Title;

    public Quiz() {
    }

    public Quiz(String image, String title) {
        this.image = image;
        Title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
