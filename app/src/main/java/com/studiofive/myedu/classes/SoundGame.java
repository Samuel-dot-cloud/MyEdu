package com.studiofive.myedu.classes;

public class SoundGame {
    private int image;
    private int sound;
    private String text;

    public SoundGame(int image, int sound, String text) {
        this.image = image;
        this.sound = sound;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
