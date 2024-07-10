package com.example.assignment03;

import java.io.Serializable;

public class IdentificationDetails implements Serializable {

    private String name, email, role, education, maritalStatus, livingStatus, income;

    public IdentificationDetails() {
    }

    private static IdentificationDetails identificationDetails=new IdentificationDetails();

    public static IdentificationDetails getInstance()
    {
        return identificationDetails;
    }


    public IdentificationDetails(String name, String email, String role, String education, String maritalStatus, String livingStatus, String income) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.education = education;
        this.maritalStatus = maritalStatus;
        this.livingStatus = livingStatus;
        this.income = income;
    }

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getEmail(){return email;}

    public void setEmail(String email){this.email=email;}

    public String getRole(){return role;}

    public void setRole(String role){this.role=role;}

    public String getEducation(){return education;}

    public void setEducation(String education){this.education=education;}

    public String getMaritalStatus(){return maritalStatus;}

    public void setMaritalStatus(String maritalStatus){this.maritalStatus=maritalStatus;}

    public String getLivingStatus(){return livingStatus;}

    public void setLivingStatus(String livingStatus){this.livingStatus=livingStatus;}

    public String getIncome(){return income;}

    public void setIncome(String income){this.income=income;}
}
