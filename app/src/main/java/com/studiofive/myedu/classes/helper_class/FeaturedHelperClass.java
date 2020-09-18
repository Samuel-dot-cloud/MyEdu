package com.studiofive.myedu.classes.helper_class;

public class FeaturedHelperClass {
    int image;
    String title, description;
    float rating;

    public FeaturedHelperClass(int image, String title, String description, float rating) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }
}
