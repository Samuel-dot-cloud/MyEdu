package com.studiofive.myedu.classes;

public class Users {
    public  String userID, userName, personalMantra, profileImage, gender, email;
    public int totalScore;

    public Users() {
    }

    public Users(String userID, String userName, String personalMantra, String profileImage, String gender, String email, int totalScore) {
        this.userID = userID;
        this.userName = userName;
        this.personalMantra = personalMantra;
        this.profileImage = profileImage;
        this.gender = gender;
        this.email = email;
        this.totalScore = totalScore;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
