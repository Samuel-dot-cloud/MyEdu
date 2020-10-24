package com.studiofive.myedu.classes;

public class Quiz {
    private int image;
    private String Title;

    public Quiz() {
    }

    public Quiz(int image, String title) {
        this.image = image;
        Title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
