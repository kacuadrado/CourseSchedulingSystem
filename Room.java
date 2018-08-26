/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crss.project;

import java.util.ArrayList;

public class Room {
    // Instance variables
    private int number;
    private ArrayList<Course> courseL;
    private int[] timeSlot;
    
    // No-argument constructor
    public Room(){
        number = 0;
        courseL  = new ArrayList<>();
        timeSlot = new int[8];
    }
    
    // Constructor
    public Room(int n, ArrayList<Course> c, int[] t){
        number = n;
        courseL = c;
        timeSlot = t;
    }
    
    // Getters
    public int getNumber(){
        return number;
    }
    
    public ArrayList<Course> getCourseList(){
        return courseL;
    }
    
    public int[] getTimeSlot(){
        return timeSlot;
    }
    
    // Setters
    
    // Add time slot
    public void addTime(int t){
        if((t>8) && (t<13)){
            t = t-9;
        }
        else if((t>0) &&(t<6)){
            t = t + 3;
        }
        timeSlot[t-1] =t;
    }
    
    // Add course
    public void addCourse(Course c){
        courseL.add(c);
    }
    
    // String of object
    public String toString(){
        return String.format("Room #: %d", getNumber());
    }
}
