package com.studiofive.myedu.classes.helper_class;

public class CategoriesHelperClass {
    int image;
    String title;

    public CategoriesHelperClass(int image,  String title) {
        this.image = image;
//        this.gradient = gradient;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

//    public int getGradient() {
//        return gradient;
//    }

    public String getTitle() {
        return title;
    }
}
