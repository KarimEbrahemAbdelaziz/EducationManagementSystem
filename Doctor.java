package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Main._courses_manager;

/**
 * Created by Karim Ebrahem on 08/01/2017.
 */
public class Doctor {
    Scanner in = new Scanner(System.in);
    public String first_name, last_name, email,id,password;
    public ArrayList<Course> created_courses = new ArrayList<Course>();

    public Doctor(String first_name, String last_name, String email,String id,String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public Doctor(Doctor doc){
        this.first_name = doc.first_name;
        this.last_name = doc.last_name;
        this.email = doc.email;
        this.id = doc.id;
        this.password = doc.password;
    }


    public void show_my_courses(){
        System.out.print("Courses you are teaching:\n");
        for (Course _course : created_courses)
            System.out.print("Course " + _course.course_name + " - Code " +
                    _course.course_code + " - Doc " + _course.course_doctor + '\n');
        System.out.print("\n\n");
    }

    public void Menu(){
        System.out.print("Please choose one of the following option:" + '\n');
        int option;
        System.out.print("\t1 - Create Course" + '\n');
        System.out.print("\t2 - List My courses" + '\n');
        System.out.print("\t3 - view course" + '\n');
        System.out.print("\t4 - log out" + '\n');
        System.out.print("Enter your choice: " );
        option = in.nextInt();
        if (option == 1){
            create_course();
            Menu();
        }
        else if (option == 2){
            show_my_courses();
            Menu();
        }
        else if (option == 3){
            show_my_courses();
            select_course();
            Menu();

        }
        else if (option == 4){
            return;
        }
        else{
            // invalid option don't handle char instead of int ... this crush ... To-Do
            System.out.print("Invalid Option ... Taking you back to your Menu\n\n\n");
            Menu();

        }
        return;
    }

    public void create_course(){
        // make the new course
        Course new_course = new Course();

        System.out.print("Please enter the Course name: ");
        new_course.course_name = in.next();

        System.out.print("Please enter the Course Code: ");
        new_course.course_code = in.next();

        new_course.course_doctor = first_name + " " + last_name;
        // add the course the the avaible courses list
        _courses_manager._courses.add(new_course);
        created_courses.add(new_course);
        System.out.print("Course has been successfully created\n\n\n");
        return;
    }

    public void select_course(){
        String _code;
        System.out.print("Please Enter one of your courses code: ");
        _code = in.next();
        for (Course _course:created_courses)
            if (_course.course_code.equals(_code)){
                System.out.print("\n\n\n");
                _course.view_doctor_course_inf();
                return;
            }
        System.out.print("You have entered invalid code, Please try again\n\n\n\n");
        show_my_courses();
        select_course();
        return;
    }
}
