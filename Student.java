package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Main._courses_manager;

/**
 * Created by Karim Ebrahem on 08/01/2017.
 */
public class Student {
    public String first_name, last_name, email, id,password;
    public ArrayList<Course> registered_courses = new ArrayList<Course>();
    Scanner in = new Scanner(System.in);

    public Student(){}
    public Student(String first_name, String last_name, String email, String id,String password){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.id = id;
        this.password = password;
    }
    public Student(Student tes){
        this.first_name = tes.first_name;
        this.last_name = tes.last_name;
        this.email = tes.email;
        this.id = tes.id;
        this.password = tes.password;
    }

    public void show_registered_courses(){
        // handle if he hasn't reigster in any course yet
        if (registered_courses.size() == 0){
            System.out.print("you havn't register in any Course yet.\n\n\n\n");
            Menu();
            return;
        }
        System.out.print("Your Registered Courses:\n");
        for (Course _course : registered_courses)
            System.out.print("Course " + _course.course_name + " - Code " +
                    _course.course_code + " - Doc " + _course.course_doctor + '\n');
        System.out.print("\n\n\n\n");
        return;
    }

    public void Menu(){
        System.out.print("Please choose one of the following option:" + '\n');
        int option;
        System.out.print("\t1 - Register in a course" + '\n');
        System.out.print("\t2 - List My courses" + '\n');
        System.out.print("\t3 - view course" + '\n');
        System.out.print("\t4 - log out" + '\n');
        System.out.print("Enter your choice: ");
        option = in.nextInt();
        // ignore invalid input such as char instead of int
        while (option > 4 || option < 1){
            System.out.print("Invalid Choice, Please try again\n");
            System.out.print("Enter your choice: ");
            option = in.nextInt();
        }
        System.out.print("\n\n\n");
        if (option == 1){
            Register_new_course();
            Menu();
            return;
        }
        else if (option == 2){
            show_registered_courses();
            Menu();
            return;
        }
        else if (option == 3){
            view_student_course_inf();
            Menu();
            return;
        }
        else if (option == 4){
            return;
        }
        else{
            // invalid option don't handle char instead of int ... this crush ... To-Do
            System.out.print("Invalid Option ... Taking you back to your Menu\n\n\n");
        }
        return;
    }

    public void Register_new_course(){
        System.out.print("Available Courses to register in:\n");
        for (Course new_course : _courses_manager._courses){
            boolean taken = false;
            for (Course cur_course:registered_courses)
                if (new_course == cur_course){
                    // he has registered in this course before
                    taken = true;
                    break;
                }
            // he hasn't registered in this course before
            if (!taken)
                System.out.print("Course name: " + new_course.course_name + "   Code: " +
                        new_course.course_code + "   Taught by Dr: " + new_course.course_doctor + '\n');
        }
        // Handle if there were no available courses. To-Do

        String choose_course;
        System.out.print("\n\nplease Enter the course code you want to register on: ");
        choose_course = in.next();
        for (Course new_course : _courses_manager._courses)
            if (new_course.course_code == choose_course){
                System.out.print("You have successuflly registered in course " + new_course.course_doctor + " with doc: "
                        + new_course.course_doctor + '\n');

                // add the course to the student registerd courses
                Course ne_course = new Course(new_course.course_name,new_course.course_code,new_course.course_doctor);
                registered_courses.add(ne_course);

                // add the student to the course registered student
                Student ne_student = new Student(first_name,last_name,email,id,password);
                new_course.registered_students.add(ne_student);
                System.out.print("\n\n\n");
                // successfully registered in a new course
                return;
            }
        // unsuccessful registeration
        System.out.print("Invalid Course Code.. taking you back to your menu\n\n\n");
        Register_new_course();
        return;
    }

    public void unregister_from_course(String _course_code){
        for (Course _course : _courses_manager._courses)
            if(_course_code.equals(_course.course_code) ){
                int cur = 0;
                // remove the student from the registered students in the course
                for (Student _student : _course.registered_students){
                    if (_student.id.equals(id)){
                        _course.registered_students.remove(cur);
                        break;
                    }
                    cur++;
                }
                // remove the student assignments ( if he has any )
                for (AssignmentManager _assignments : _course.course_assignments){
                    cur = 0;
                    for (Assignment _assignment:_assignments.assignment_submissions)
                        if (_assignment.owner.equals(id)){
                            _assignments.assignment_submissions.remove(cur);
                            break;
                        }
                }
            }
        // remove the course from the student courses list
        for (Course _course : registered_courses){
            int cur = 0;
            if (_course.course_code.equals(_course_code)){
                registered_courses.remove(cur);
                break;
            }
            cur++;
        }
        System.out.print("You have successuflly unregistered from course code: " +
                _course_code + '\n');
        return;
    }

    public void view_student_course_inf(){
        // handle if he hasn't reigster in any course yet
        if (registered_courses.size() == 0){
            System.out.print("you havn't register in any Course yet.\n\n\n\n");
            Menu();
            return;
        }
        // view all course he's registered in
        show_registered_courses();
        System.out.print("Select one of your Registered course code" +
                "( course code not course name ) to view: ");
        String _code;
        _code = in.next();

        // check if course code he entered exist
        boolean wrong_code = true;
        for (Course _course:registered_courses)
            if (_course.course_code.equals(_code)){
                int option;
                System.out.print("Please choose one of the following options: \n");
                System.out.print("\t1 - Unregister from this Course\n");
                System.out.print("\t2 - View Course information and assignments\n");
                System.out.print("Enter your choice here: ");
                option = in.nextInt();
                // ignore invalid input such as char instead of int
                while (option > 2 || option < 1){
                    System.out.print("Invalid Choice, Please try again\n");
                    System.out.print("Enter your choice: ");
                    option = in.nextInt();
                }
                System.out.print("\n\n\n");
                if (option == 1){
                    unregister_from_course(_code);
                    Menu();
                }
                else if(option == 2){
                    for (Course __course : _courses_manager._courses)
                        if(__course.course_code.equals(_code)){
                            __course.view_student_course_inf(id);
                            break;
                        }
                }else{
                    break; // wrong code
                }
                wrong_code = false; // course code exist
                break;
            }

        if (wrong_code){
            System.out.print("You've entered invalid course code, " +
                    "please try again.\n\n\n\n");
            view_student_course_inf();
            return;
        }
        return;
    }
}
