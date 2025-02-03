package com.matchmaker.user_mgt.model.dto;

import com.matchmaker.user_mgt.model.Gender;
import com.matchmaker.user_mgt.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserRequest {

    private String email;
    private String password;
    private String fullName;
    private Gender gender;

    public UserRequest() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public UserRequest(String password, String email, String fullName, Gender gender) {
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
