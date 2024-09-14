/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
// Data Transfer Object
// Data class
//
//Model
//POJO: Plain Old Java Object
// bean - java bean

/**
 *
 * @author hamad
 */
public class Contact {
    private int ID;
    private String FName;
    private String MName;
    private String LName;

    public int getID() {
        return ID;
    }

    public String getFName() {
        return FName;
    }
    
    public Contact()
    {
        
    }
    public Contact(int ID, String FName, String MName, String LName, String Mobile, String email) {
        this.ID = ID;
        this.FName = FName;
        this.MName = MName;
        this.LName = LName;
        this.Mobile = Mobile;
        this.email = email;
    }

    public String getMName() {
        return MName;
    }

    public String getLName() {
        return LName;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setMName(String MName) {
        this.MName = MName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String Mobile;
    private String email;
  
}
