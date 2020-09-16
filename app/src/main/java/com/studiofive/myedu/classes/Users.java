package com.studiofive.myedu.classes;

public class Users {
    public  String name, personalMantra, image, gender;

    public Users() {
    }

    public Users(String name, String personalMantra, String image, String gender) {
        this.name = name;
        this.personalMantra = personalMantra;
        this.image = image;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
