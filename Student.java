/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crss.project;

/**
 *
 * @author kacua
 */
public class Student {
    // Instance variables
    private String firstName;
    private String lastName;
    private long phoneNum;
    
    // Constructor
    public Student(String f, String l, long p){
        firstName = f;
        lastName = l;
        phoneNum = p;
    }
    
    // Getters
    public String getFName(){
        return firstName;
    }
    
    public String getLName(){
        return lastName;
    }
    
    public long getPhoneNum(){
        return phoneNum;
    }
    
    public String toString(){
        return String.format("Name: %s %s Phone #: %d", getFName(), getLName(), getPhoneNum());
    }
}
