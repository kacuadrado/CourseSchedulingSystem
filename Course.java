/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crss.project;

import java.util.ArrayList;

public class Course {
    // Instance variables
    private String name;
    private Room r;
    private int time;
    private ArrayList<Student> s;
    
    // Constructor
    public Course(String n, Room rm, int t, ArrayList<Student> stu){
        name = n;
        r = rm;
        time = t;
        s = stu;
    }
    
    // Getters
    public String getName(){
        return name;
    }
    
    public Room getRoom(){
        return r;
    }
    
    public int getTime(){
        return time;
    }
    
    public ArrayList<Student> getStudentL(){
        return s;
    }
    
    // Setters
    public void setRoom(Room rm){
        r= rm;
    }
    
    public void setName(String n){
        name = n;
    }
    
    
    // Add student
    public void addStudent(Student stu){
        s.add(stu);
    }
    
    // Remove student
    public void removeStudent(Student stu){
        s.remove(stu);
    }
    
    // String of object
    public String toString(){
        return String.format("Course: %s Room #: %d Time: %d", getName(), getRoom().getNumber(), getTime());
    }
}
