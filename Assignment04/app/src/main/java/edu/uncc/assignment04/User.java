package edu.uncc.assignment04;

import java.io.Serializable;

public class User implements Serializable {

    String name,email,role,education,Mstatus,Lstatus,Income;

    public User() {
    }

    public User(String name, String email, String role, String education, String mstatus, String lstatus, String income) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.education = education;
        Mstatus = mstatus;
        Lstatus = lstatus;
        Income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMstatus() {
        return Mstatus;
    }

    public void setMstatus(String mstatus) {
        Mstatus = mstatus;
    }

    public String getLstatus() {
        return Lstatus;
    }

    public void setLstatus(String lstatus) {
        Lstatus = lstatus;
    }

    public String getIncome() {
        return Income;
    }

    public void setIncome(String income) {
        Income = income;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", education='" + education + '\'' +
                ", Mstatus='" + Mstatus + '\'' +
                ", Lstatus='" + Lstatus + '\'' +
                ", Income='" + Income + '\'' +
                '}';
    }
}
