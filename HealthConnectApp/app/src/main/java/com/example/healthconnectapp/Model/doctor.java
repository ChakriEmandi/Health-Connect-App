package com.example.healthconnectapp.Model;

public class doctor {
    public  int Id;
    public String Name, Gender, Specialization, Email;

    public doctor(int Id,String Name,String Gender,String Specialization,String Email) {
        this.Id = Id;
        this.Name = Name;
        this.Gender = Gender;
        this.Specialization = Specialization;
        this.Email = Email;
    }

    public doctor() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
