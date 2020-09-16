package com.studiofive.myedu.classes;

public class Users {
    public  String userID, userName, personalMantra, profileImage, gender, email;

    public Users() {
    }

    public Users(String userID, String userName, String personalMantra, String profileImage, String gender, String email) {
        this.userName = userName;
        this.personalMantra = personalMantra;
        this.profileImage = profileImage;
        this.gender = gender;
        this.userID = userID;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonalMantra() {
        return personalMantra;
    }

    public void setPersonalMantra(String personalMantra) {
        this.personalMantra = personalMantra;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
