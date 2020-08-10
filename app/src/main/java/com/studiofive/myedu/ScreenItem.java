package com.studiofive.myedu;

public class ScreenItem {
String Title, Description;
int Screening;

    public ScreenItem(String title, String description, int screening) {
        Title = title;
        Description = description;
        Screening = screening;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getScreening() {
        return Screening;
    }

    public void setScreening(int screening) {
        Screening = screening;
    }
}
