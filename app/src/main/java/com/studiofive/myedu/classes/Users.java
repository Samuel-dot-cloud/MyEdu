package com.studiofive.myedu.classes;

public class Users {
    public  String name, personalMantra, image;

    public Users() {
    }

    public Users(String name, String personalMantra, String image) {
        this.name = name;
        this.personalMantra = personalMantra;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalMantra() {
        return personalMantra;
    }

    public void setPersonalMantra(String personalMantra) {
        this.personalMantra = personalMantra;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
