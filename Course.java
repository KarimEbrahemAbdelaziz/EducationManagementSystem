package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Karim Ebrahem on 08/01/2017.
 */
public class Course {
    public String course_name;
    public String course_code;
    public String course_doctor;
    Scanner in = new Scanner(System.in);

    public Course(){}
    public Course(String course_name, String course_code, String course_doctor){
        this.course_name = course_name;
        this.course_code = course_code;
        this.course_doctor = course_doctor;
    }

    public void show_registered_students(){
        System.out.print("Registered Students ID:");
        for (Student _student : registered_students)
            System.out.print(" " + _student.id);
        System.out.print("\n\n\n\n");
        return;
    }

    public void view_student_course_inf(String cur_student_id){
        System.out.print("course " + course_name + " - code " + course_code +
                " - doc " + course_doctor + '\n');
        System.out.print("course has " + course_assignments.size() + " assignments\n");

        int assignment_number = 0;
        // go over all assignments of this course
        for (AssignmentManager _assignment : course_assignments){
            boolean student_done_this_assignment = false;
            for (Assignment _student_assignmnent : _assignment.assignment_submissions)
                if (_student_assignmnent.owner == cur_student_id){
                    System.out.print("Assignment " + ++assignment_number + " Submitted" + " - ");
                    // check if he has a grade yet
                    if (_student_assignmnent.grade == -1)
                        System.out.print("NA");
                    else
                        System.out.print(_student_assignmnent.grade);
                    System.out.print(" / " + _assignment.max_grade + "\n\n");
                    student_done_this_assignment = true;
                }
            // he havn't submit this assignment yet ( he might want to submit it now )
            if (student_done_this_assignment == false){
                System.out.print("Do you want to submit this assignment now ?\n");
                System.out.print("\t1 - submit assignment now\n");
                System.out.print("\t2 - don't submit now\n");
                int option;
                System.out.print("Enter your choice: ");
                option = in.nextInt();
                // ignore invalid input such as char instead of int
                while (option > 2 || option < 1){
                    System.out.print("Invalid Choice, Please try again\n");
                    System.out.print("Enter your choice: ");
                    option = in.nextInt();
                }
                if (option == 1){
                    // he want to submit this assignment now
                    Assignment new_assignment = new Assignment();
                    System.out.print("Please Enter your Solution (one word): ");  // To-Do make assignment more than 1 word
                    new_assignment.solution = in.next();
                    new_assignment.grade = -1;
                    new_assignment.owner = cur_student_id;
                    _assignment.assignment_submissions.add(new_assignment);
                    System.out.print("MAX_GRADE :" + _assignment.max_grade + "\n");
                    System.out.print("You've submitted you assignment successfully \n\n\n\n");
                }
            }
        }
        System.out.print("\n\n\n\n");
        return;
    }

    public void view_doctor_course_inf(){
        System.out.print("Please choose one of the following option:" + '\n');
        int option;
        System.out.print("\t1 - List assignment" + '\n');
        System.out.print("\t2 - add assignment" + '\n');
        System.out.print("\t3 - View assignment" + '\n');
        System.out.print("\t4 -  Back" + '\n');
        System.out.print("Enter your choice: " );
        option = in.nextInt();
        // ignore invalid input such as char instead of int
        while (option > 4 || option < 1){
            System.out.print("Invalid Choice, Please try again\n");
            System.out.print("Enter your choice: ");
            option = in.nextInt();
        }
        System.out.print("\n\n\n");
        if (option == 1){
            list_course_assignment();
        }
        else if (option == 2){
            add_assignment();
        }
        else if (option == 3){
            select_assignment();
        }
        else if (option == 4){
            return; // go back to previous window
        }
        else{
            System.out.print("Invalid Option ... Taking you back to your Menu\n\n\n");
        }
        view_doctor_course_inf();
        return;
    }

    public void list_course_assignment(){
        System.out.print("This Couse Has " + course_assignments.size() + " assignments\n");
        int assignmentNumber = 0;
        for (AssignmentManager assignment : course_assignments)
            System.out.print("Assignment " + ++assignmentNumber + ":" + assignment.assignment_info +
                    ", Grade:" + assignment.max_grade + '\n');
        System.out.print("\n\n\n");
    }

    public void add_assignment(){
        AssignmentManager new_assignment = new AssignmentManager();
        new_assignment.assignment_info = "";
        System.out.print("Please enter some inf about the assignment " +
                "( end it with # in a sepearte word ):");
        String _inf;
        while (true){
            _inf = in.next();
            if (_inf.charAt(0) == '#')
                break;
            new_assignment.assignment_info += " " + _inf;
        }
        System.out.print("Please enter max grade of the assignment :");
        new_assignment.max_grade = in.nextInt();
        System.out.print("\n\n\n\n");
        course_assignments.add(new_assignment);
        // handle input error To-Do
    }

    public void select_assignment(){
        int numberOfassignment = 0;
        for (AssignmentManager _assignment : course_assignments){
            System.out.print("Assignment number " + ++numberOfassignment + '\n');
            System.out.print("Enter one of the following option: \n");
            System.out.print("\t1 - view this assignment\n");
            System.out.print("\t2 - Continue\n");
            System.out.print("Enter your choice: ");
            int option;
            option = in.nextInt();
            // ignore invalid input such as char instead of int
            while (option > 2 || option > 1){
                System.out.print("Invalid Choice, Please try again\n");
                System.out.print("Enter your choice: ");
                option = in.nextInt();
            }
            System.out.print("\n\n");
            if (option == 1)
                _assignment.view_assignment();
            System.out.print("\n\n");
        }
    }

    public ArrayList<AssignmentManager> course_assignments = new ArrayList<AssignmentManager>();
    public ArrayList<Student> registered_students = new ArrayList<Student>();
}
