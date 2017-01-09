package com.company;

import java.util.ArrayList;

/**
 * Created by Karim Ebrahem on 08/01/2017.
 */
public class StudentManager {
    //public static StudentManager _students_manager = new StudentManager();
    public ArrayList<Student> _students = new ArrayList<Student>();

    public StudentManager(){

    }

    public void add_new_student(Student a){
        Student ne = new Student(a);
        _students.add(ne);
        return;
    }
}
