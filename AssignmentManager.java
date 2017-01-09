package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Karim Ebrahem on 08/01/2017.
 */
public class AssignmentManager {
    public String assignment_info;
    public int max_grade;
    Scanner in = new Scanner(System.in);

    public AssignmentManager(){}

    public AssignmentManager(String assignment_info, int max_grade){
        this.assignment_info = assignment_info;
        this.max_grade = max_grade;
    }

    public void view_assignment(){
        System.out.print("Please choose one of the following option:" + "\n");
        int option;
        System.out.print("\t1 - Show Info" + "\n");
        System.out.print("\t2 - Show grades Report" + "\n");
        System.out.print("\t3 - List Solutions" + "\n");
        System.out.print("\t4 - View Solution" + "\n");
        System.out.print("\t5 -  Back" + "\n");
        System.out.print("Enter your choice: ");
        option = in.nextInt();
        // ignore invalid input such as char instead of int
        if (option > 5 || option < 1){
            System.out.print("Invalid Choice, Please try again\n");
            System.out.print("Enter your choice: ");
            option = in.nextInt();
        }
        if (option == 1){
            System.out.print(assignment_info + "\n\n\n");
        }
        else if (option == 2){
            grades_report();
        }
        else if (option == 3){
            list_students_solutions();
        }
        else if (option == 4){
            view_student_solution();
        }
        else if (option == 5){
            return;
        }
        else{
            System.out.print("Invalid Option ... Taking you back to your Menu\n\n\n");
        }
        view_assignment();
        return;
    }

    public void grades_report(){
        System.out.print("Student Grades:\n");
        for (Assignment _assignment : assignment_submissions)
            System.out.print("Student id: " + _assignment.owner + " Grade: " + _assignment.grade + "\n");
        System.out.print("\n\n\n\n");
    }

    public void list_students_solutions(){
        System.out.print("Student Solutions:\n");
        for (Assignment _assignment : assignment_submissions)
            System.out.print("Student id: " + _assignment.owner + " Solution: " + _assignment.solution + "\n");
        System.out.print("\n\n\n\n");
    }

    public void view_student_solution(){
        list_students_solutions();
        String _id;
        System.out.print("Please enter student id: ");
        _id = in.next();
        for (Assignment _assignment:assignment_submissions)
            if (_assignment.owner.equals(_id)){
                int option;
                System.out.print("Please choose ine of the following options:\n");
                System.out.print("\t1 - Set-grade\n");
                System.out.print("\t2 - back");
                System.out.print("Enter your choice: " + '\n');
                option = in.nextInt();
                // ignore invalid input such as char instead of int
                while (option > 2 || option < 1){
                    System.out.print("Invalid Choice, Please try again\n");
                    System.out.print("Enter your choice: ");
                    option = in.nextInt();
                }
                if (option == 1){
                    System.out.print("Please enter student grade: ");
                    _assignment.grade = in.nextInt();
                }else{
                    System.out.print("Invalid Option, Please try again \n\n\n\n");
                    view_student_solution();
                }
                return;
            }
        System.out.print("Invalid id, Please try again \n\n\n\n");
        view_student_solution();
    }

    public ArrayList<Assignment> assignment_submissions = new ArrayList<Assignment>();
}
