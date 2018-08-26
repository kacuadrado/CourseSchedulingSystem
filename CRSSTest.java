/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crss.project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CRSSTest {
    
    public static void main(String[] args){
        // Declare input object
        Scanner input = new Scanner(System.in);
        
        // Declare variable for number of rooms
        int numRooms = 0;
        // Declare varibale for menu selection
        int selection = 0;
        // Boolean to test valid input
        boolean flag1 = false;
        boolean flag2 = false;
        
        while(!flag1){
            try{
                // Ask user for how many rooms available
                System.out.print("\nSpecify the number of rooms available for classes: ");
       
                // Throw exception if not an integer input
                numRooms = input.nextInt();
                // Check if positive
                if(numRooms > 0){
                    flag1 = true;
                }
                else{
                    System.out.print("\nEnter a positive integer.");
                }
            }
            catch (InputMismatchException e){
                System.out.print("\nInvalid input. Try Again.");
                input.nextLine();
            }
        }
        
        // Create array list of rooms and variables to store user input
        ArrayList<Room> roomList= new ArrayList<Room>();
        int roomID = 0;
        
        // Create time slot
        int timeSlot[] = new int[8];
        
        // Create array list of courses
        ArrayList<Course> courseList = new ArrayList<Course>();
        
        // Create array list of students
        ArrayList<Student> studentList = new ArrayList<Student>();     
        
        // Create empty array list of courses
        ArrayList<Course> emptyCourseL = new ArrayList<Course>();
        
        // Variable to check numRooms
        int i = 0;
        
        // Enter menu - loop
        while(selection!=-1){
            // Reset flag
            flag1 = false;
            
            // Ask user what they would like to do
            while(!flag1){
                try{
                    System.out.print("\nEnter an option 1 through 12 or -1 to "
                            + "exit:\n1- Add a room\n2- Delete a room\n3- Add a"
                            + " student\n4- Delete a student\n5- Add a course\n"
                            + "6- Delete a course\n7- Add student to a course\n"
                            + "8- Remove student from a course\n9- Display all "
                            + "courses in day\n10- Display all courses in a roo"
                            + "m\n11- Display courses attended by a student\n12"
                            + "- Display courses in a time slot\nInput: ");
                    

                    selection = input.nextInt();
                    
                    // Check if valid
                    if((selection > 0 && selection < 13) || selection == -1){
                        flag1 = true;
                    }
                    else{
                        System.out.print("\nInvalid input. Try Again.");
                    }
                }
                catch (InputMismatchException e){
                    System.out.print("\nInvalid input. Try Again.");
                    input.nextLine();
                }
            }
            
            // Reset flags
            flag1 =false;
            flag2 =false;
            
            switch(selection){
                
                // Add room
                case 1:
                        try{
                            if(i == numRooms){
                                System.out.print("\nMax capacity of rooms reached.");
                            }
                            else{
                                System.out.printf("\nPlease enter room number: ");

                                // Throw exception if not an integer input
                                roomID = input.nextInt();

                                // Check if positive
                                if(roomID > 0){

                                    // Check if already exists
                                    for (Room rd : roomList){
                                        if(rd.getNumber() == roomID){
                                            flag1 = true;
                                        }
                                    }

                                    if(!flag1){
                                        // Create room object and add to list
                                        Room r = new Room(roomID, emptyCourseL, timeSlot);
                                        roomList.add(r);
                                        System.out.printf("\nRoom %d added.", roomID);
                                        // Add to count of current rooms
                                        i++;
                                    }
                                    else{
                                        System.out.printf("\nRoom %d already exists. Cannot add room.", roomID);
                                    }
                                }
                                else{
                                    System.out.print("\nEnter a positive integer.");
                                }
                            }
                        }
                        catch (InputMismatchException e){
                            System.out.print("\nInvalid input.");
                            input.nextLine();
                        }
                    break;
                    
                // Delete Room
                case 2:
                    try{
                        // Check if rooms exist
                        if(!roomList.isEmpty()){
                            System.out.printf("\nEnter number of room you would like to remove: ");
                            int j = 0;
                            
                            // List rooms available
                            for(Room rmm : roomList){
                                System.out.printf("\n%d- Room #%d", (j+1), rmm.getNumber());
                                j++;
                            }

                            System.out.printf("\nInput: ");
                            
                            // Throw exception if not a integer input
                            int num = input.nextInt();

                            // Validate input
                            if((num < 1) || (num>j)){
                                throw new InputMismatchException("\nThat is not an option.");
                            }

                            // Check if course assigned to room
                            for(Course c: courseList){
                                if (c.getRoom().getNumber() == roomList.get(num-1).getNumber()){
                                    flag1 = true;
                                }
                            }

                            if(flag1){
                                System.out.print("\nInvalid. Room has courses assigned. ");
                            }
                            else{
                                roomList.remove(num-1);
                                // Decrease number of current rooms
                                i--;
                                System.out.print("\nRoom has been removed.");
                            }
                        }    
                        else{
                            System.out.print("\nInvalid. Must add rooms before deleting rooms");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Add student
                case 3:
                    try{
                        System.out.printf("\nPlease enter first name of student to be added: ");
                        // Throw exception if not a string input
                        input.nextLine();
                        String fName = input.nextLine();
                        System.out.printf("\nPlease enter last name of student to be added: ");
                        String lName = input.nextLine();
                        // Check validity of names
                        if(fName.matches(".*\\d+.*") || lName.matches(".*\\d+.*")){
                            System.out.print("\nInvalid student name. No numbers or spaces allowed.");
                        }
                        else{
                            System.out.printf("\nPlease enter area code of phone number of student to be added in the format XXX: ");
                            int phoneArea = input.nextInt();

                            // Check if area code is invalid (ie. 911, 411, 555, 0xx, 1xx)
                            if((phoneArea == 555) || (phoneArea == 911) || (phoneArea == 411)|| (phoneArea > 999) || (phoneArea <200)){
                                System.out.print("\nInvalid area code. Cannot add student.");
                            }
                            else{
                                // check phone number validity
                                System.out.printf("\nPlease enter phone number of student to be added in the format XXXXXXX: ");
                                int phoneNum = input.nextInt();

                                if((phoneNum < 2000000) || (phoneNum > 9999999)){
                                    System.out.print("\nInvalid phone number. Cannot add student.");
                                }
                                else{
                                    // Check if student already exists
                                    for (Student stu : studentList){
                                        if((stu.getFName().equals(fName)) && (stu.getLName().equals(lName)) && (stu.getPhoneNum() == phoneNum)){
                                            flag1 = true;   // Reversed. 
                                        }
                                    }

                                    // Create object or not
                                    if(flag1){  // Student already exists
                                        System.out.print("\nStudent already exists. Cannot add student.");
                                    }
                                    else{
                                        //Create object and add to student list
                                        Student s = new Student(fName, lName, phoneNum);
                                        studentList.add(s);
                                        System.out.printf("\nStudent %s added.", fName);
                                    }
                                }
                            }
                        }
                    }
                    
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input. Cannot add student.");
                        input.nextLine();
                    }
                    break;
                    
                // Delete student
                case 4:
                    try{
                        // Check if students exist
                        if(!studentList.isEmpty()){
                            System.out.printf("\nEnter number of student you would like to remove: ");
                            int j = 0;
                            
                            // Print students available
                            for(Student stu : studentList){
                                System.out.printf("\n%d- %s %s %d", (j+1), stu.getFName(), stu.getLName(), stu.getPhoneNum());
                                j++;
                            }
                            
                            System.out.printf("\nInput: ");
                            
                            // Throw exception if not a integer input
                            int num = input.nextInt();

                            // Validate input
                            if((num < 1) || (num>j)){
                                throw new InputMismatchException("\nThat is not an option.");
                            }

                            // Check if student assigned to course
                            for(Course c : courseList){
                                // Check if student is in any course
                                if(c.getStudentL().contains(studentList.get(num-1))){
                                    flag1 = true;
                                }     
                            }

                            if(!flag1){ // If student not assigned to course
                                studentList.remove(num-1);
                                System.out.print("\nStudent has been removed");
                            }
                            else{
                                System.out.print("\nInvalid. Student is assigned to a course. Cannot remove student.");
                            }
                        }
                        else{ // If student list empty
                            System.out.print("\nInvalid. Must add students before deleting students.");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Add course
                case 5:
                    try{
                        if(!roomList.isEmpty()){
                            System.out.printf("\nEnter name of the course you would like to add: ");
                            // Throw exception if not string
                            input.nextLine();
                            String name = input.nextLine();
                            System.out.printf("\nEnter number of room for course: ");
                            int j = 0;
                            
                            // List rooms available
                            for(Room rmm : roomList){
                                System.out.printf("\n%d- Room #%d", (j+1), rmm.getNumber());
                                j++;
                            }

                            System.out.printf("\nInput: ");
                            
                            // Throw exception if not a integer input
                            int rmID = input.nextInt();
                            
                            System.out.printf("\nEnter time of the course you would like to add: ");
                            // Throw exception if not integer
                            int timeC = input.nextInt();

                            // Create empty array list for students
                            ArrayList<Student> emptyStuList = new ArrayList<Student>();

                            // Validate input
                            if(!((timeC>8 && timeC<12)||(timeC>0 && timeC <6))){
                                throw new InputMismatchException("\nThat is not an option.");
                            }

                            // Check if course already exists
                            for (Course c : courseList){
                                if((c.getName().equals(name)) && (c.getRoom().getNumber() == rmID) && (c.getTime() == timeC)){
                                    flag1 = true;
                                }
                            }

                            if(flag1){
                                System.out.print("\nCourse already exists.");
                            }

                            else{      
                                int indx = 0;
                                // Check if room entered exists
                                for (Room r2 : roomList){ 
                                    // Room exists and slot available
                                    if(r2.getNumber() == rmID){
                                        indx = roomList.indexOf(r2);
                                        flag1 = true;
                                    }
                                }

                                // Check if room is occuppied at that time
                                if(roomList.get(indx).getTimeSlot()[timeC-1] == timeC){
                                    flag2  = true;
                                }


                                if(flag1 && !flag2){
                                    Course c = new Course(name, roomList.get(indx), timeC, emptyStuList);
                                    // Add course to list
                                    courseList.add(c);
                                    // Add course to room
                                    roomList.get(indx).addCourse(c);
                                    // Separate time slot for room
                                    roomList.get(indx).addTime(timeC);

                                    System.out.print("\nCourse added.");
                                }
                                else if(!flag1){
                                    System.out.print("\nRoom does not exist. Add room, then add course.");
                                }
                                else if(flag1 && flag2){
                                    System.out.print("\nThe room is not available at the time specified.");
                                }
                            }
                        }
                        else{
                            System.out.print("\nInvalid. No rooms exist.");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Delete course
                case 6:
                    try{
                        // Check if courses exist
                        if(!courseList.isEmpty()){
                            System.out.printf("\nEnter number of course you would like to remove: ");
                            int j = 0;
                            
                            // List available courses
                            for(Course c : courseList){
                                System.out.printf("\n%d- %s Room: %d Time: %d", (j+1), c.getName(), c.getRoom().getNumber(), c.getTime());
                                j++;
                            }

                            System.out.printf("\nInput: ");
                            
                            // Throw exception if not a integer input
                            int num = input.nextInt();

                            // Validate input
                            if((num < 1) || (num>j)){
                                throw new InputMismatchException("\nThat is not an option.");
                            }

                            // Check if student assigned to course
                            if(courseList.get(num-1).getStudentL().isEmpty()){
                                flag1 = true;
                            };

                            if(flag1){  // If no students assigned
                                courseList.remove(num-1);
                                System.out.print("\nCourse has been removed.");
                            }
                            else{
                                System.out.print("\nInvalid. Course has students assigned.");
                                
                            }
                        }
                        else{
                            System.out.print("\nInvalid. Must add course to delete course.");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Add student to course
                case 7:
                    try{
                        // Check if students exist
                        if(!studentList.isEmpty()){
                            System.out.printf("\nEnter number of student you would like to add to a class: ");
                            int j = 0;
                            
                            // List available students
                            for(Student stu : studentList){
                                System.out.printf("\n%d- %s %s %d", (j+1), stu.getFName(), stu.getLName(), stu.getPhoneNum());
                                j++;
                            }
                            
                            System.out.printf("\nInput: ");

                            // Throw exception if not a integer input
                            int num1 = input.nextInt();
                            // Validate input
                            if((num1 < 1) || (num1>j)){
                                throw new InputMismatchException("\nThat is not an option.");
                            }
                            
                            // Check if courses exist
                            if(!courseList.isEmpty()){
                                System.out.printf("\nEnter number of course you would like to add student to: ");
                                j = 0;
                                
                                // List courses available
                                for(Course c : courseList){
                                    System.out.printf("\n%d- %s Room: %d Time: %d", (j+1), c.getName(), c.getRoom().getNumber(), c.getTime());
                                    j++;
                                }

                                System.out.printf("\nInput: ");
                                
                                // Throw exception if not a integer input
                                int num2 = input.nextInt();
                                // Validate input
                                if((num2 < 1) || (num2>j)){
                                    throw new InputMismatchException("\nThat is not an option.");
                                }
                                // Check if student not in class
                                if(!courseList.get(num2-1).getStudentL().contains(studentList.get(num1-1))){
                                    flag1 = true;
                                }
                                // Check if student busy at that time
                                for(Course c: courseList){
                                    for(Student s : c.getStudentL()){
                                        if(s.equals(studentList.get(num1-1))){
                                            if(c.getTime() == courseList.get(num2-1).getTime()){
                                                flag2 =true;
                                            }
                                        }
                                    }
                                }

                                if(flag1 && !flag2){    // Student is available at time and is in class
                                    courseList.get(num2-1).addStudent(studentList.get(num1-1));
                                    System.out.printf("\nStudent %s has been added to course %s.", studentList.get(num1-1).getFName(), courseList.get(num2-1).getName());
                                }
                                else if(!flag1){    // Student is already in class
                                    System.out.print("\nInvalid. Student is already assigned to course.");
                                }
                                else if (flag1 && flag2){   // Student is not available
                                    System.out.print("\nInvalid. Student is already assigned to course at that time.");
                                }
                            }
                            else{   // No courses available
                                System.out.printf("\nNo courses exist. Add course then add student to course. ");
                            }
                        }
                        else{   // No students available
                            System.out.printf("\nNo students exist. Add student then add student to course. ");
                        }
                                
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Remove student from course
                case 8:
                    try{
                        // Check if students exist
                        if(!studentList.isEmpty()){
                            System.out.printf("\nEnter number of student you would like to remove: ");
                            int j = 0;

                            // List available students
                            for(Student stu : studentList){
                                System.out.printf("\n%d- %s %s %d", (j+1), stu.getFName(), stu.getLName(), stu.getPhoneNum());
                                j++;
                            }
                            
                            System.out.printf("\nInput: ");
                            
                            // Throw exception if not a integer input
                            int num1 = input.nextInt();

                            // Validate input
                            if((num1 < 1) || (num1>j)){
                                throw new InputMismatchException("\nThat is not an option.");
                            }
                            
                            // Check is courses exist
                            if(!courseList.isEmpty()){
                                System.out.printf("\nEnter number of course you would like to remove student from: ");
                                j = 0;

                                // List courses available
                                for(Course c : courseList){
                                    System.out.printf("\n%d- %s Room: %d Time: %d", (j+1), c.getName(), c.getRoom().getNumber(), c.getTime());
                                    j++;
                                }

                                System.out.printf("\nInput: ");
                                
                                // Throw exception if not a integer input
                                int num2 = input.nextInt();
                                // Validate input
                                if((num2 < 1) || (num2>j)){
                                    throw new InputMismatchException("\nThat is not an option.");
                                }
                                
                                // Check if student already not in class
                                if(!courseList.get(num2-1).getStudentL().contains(studentList.get(num1-1))){
                                    flag1 = true;
                                }

                                if(!flag1){ // Student is available to be removed
                                    courseList.get(num2-1).removeStudent(studentList.get(num1-1));
                                    System.out.printf("\nStudent %s has been removed to course %s.", studentList.get(num1-1).getFName(), courseList.get(num2-1).getName());
                                }
                                else{   // Student not assigned to course
                                    System.out.print("\nInvalid. Student is already not assigned to course.");
                                }
                            }
                            else{   // No courses exist
                                System.out.printf("\nNo courses exist. Add course then add student to course then remove from course. ");
                            }
                        }
                        else{   // No students exist
                            System.out.print("\nInvalid. Must add students before removing students from courses.");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Display all courses in day
                case 9:
                    System.out.print("\nCourses today: ");
                    for(Course c: courseList){
                        System.out.printf("\n%s", c);
                    }
                    break;
                    
                // Display all courses in room
                case 10:
                    try{
                        // Check if rooms exist
                        if(!roomList.isEmpty()){
                            System.out.printf("\nEnter number of room you would like to display all courses for: ");
                            int j = 0;

                            // List rooms available
                            for(Room rmm : roomList){
                                System.out.printf("\n%d- Room #%d", (j+1), rmm.getNumber());
                                j++;
                            }
                            
                            System.out.printf("\nInput: ");

                            // Throw exception if not a integer input
                            int num = input.nextInt();

                            // Validate input
                            if((num < 1) || (num>j)){
                                throw new InputMismatchException("\nThat is not an option.");
                            }

                            System.out.printf("\nAll courses in room #%d are:", roomList.get(num-1).getNumber());

                            // Get courses assigned to room
                            for(Course c: courseList){
                                if(c.getRoom().getNumber() == roomList.get(num-1).getNumber()){
                                    System.out.printf("\n%s", c);
                                }
                            } 
                        }
                        else{   // No rooms exist
                            System.out.print("\nNo rooms exist. Add a room then display courses.");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Display courses attended by single student
                case 11:
                     try{
                         // Check if students exist
                         if(!studentList.isEmpty()){
                            System.out.printf("\nEnter number of student you would like to display courses assigned: ");
                            int j = 0;

                            // List available students
                            for(Student stu : studentList){
                                System.out.printf("\n%d- %s %s %d", (j+1), stu.getFName(), stu.getLName(), stu.getPhoneNum());
                                j++;
                            }

                            System.out.printf("\nInput: ");
                            
                            // Throw exception if not a integer input
                            int num = input.nextInt();

                            // Validate input
                            if((num < 1) || (num>j)){
                                throw new InputMismatchException("\nThat is not an option.");
                            }

                            System.out.printf("\nCourses assigned to student %s:", studentList.get(num-1).getFName());
                            
                            // Get courses student is assigned to
                            for (Course c : courseList){
                                if(c.getStudentL().contains(studentList.get(num-1))){
                                    System.out.printf("\n%s", c);
                                }
                            }
                        }
                         else{  // Student does not exist
                             System.out.print("\nInvalid. Add student then display courses.");
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
                    
                // Display courses at timeslot
                case 12:
                    try{
                        System.out.print("\nFor what time (9-5) would you like to see all courses: ");
                        // Throw exception if not input
                        int num = input.nextInt();
                        
//                      // Validate input
                        if(!((num>8 && num<12)||(num>0 && num <6))){
                            throw new InputMismatchException("\nThat is not an option.");
                        }
                        
                        System.out.printf("\nCourses at time %d:",num);
                        // Go through course list
                        for(Course c : courseList){
                            if(c.getTime() == num){
                                System.out.printf("\n%s",c);
                            }
                        }
                    }
                    catch (InputMismatchException e){
                        System.out.print("\nInvalid input.");
                        input.nextLine();
                    }
                    break;
            }
        }
        
    }
}
