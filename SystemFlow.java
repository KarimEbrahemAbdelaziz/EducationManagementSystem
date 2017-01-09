package com.company;

import java.io.*;
import java.util.Scanner;

import static com.company.Main._courses_manager;
import static com.company.Main._doctors_manager;
import static com.company.Main._students_manager;

/**
 * Created by Karim Ebrahem on 08/01/2017.
 */
public class SystemFlow {
    public static SystemFlow _system = new SystemFlow();
    Scanner in = new Scanner(System.in);

    public void main_Menu(){
        System.out.print("Please choose one of the following option:" + '\n');
        int option;
        System.out.print("\t1 - Sign in" + '\n');
        System.out.print("\t2 - Sign up" + '\n');
        System.out.print("\t3 - Shutdown system" + '\n');
        System.out.print("Enter your choice: " );
        option = in.nextInt();

        while (option > 3 || option < 1){
            System.out.print("Invalid Choice, Please try again\n");
            System.out.print("Enter your choice: ");
            option = in.nextInt();
        }
        if (option == 1){
            Sign_in();
        }
        else if (option == 2){
            Sign_up();
        }
        else if (option == 3){
            return;
        }
        else{
            System.out.print("Invalid Option ... Taking you back to your Menu\n\n\n");
            main_Menu();
        }
        return;
    }

    public void Sign_in(){
        System.out.print("Please enter your id and password\n");
        String _id,_password;
        System.out.print("Enter your id: ");
        _id = in.next();
        System.out.print("Enter your Password: ");
        _password = in.next();
        System.out.print("\n\n\n\n");

        boolean logged_in = false;
        for (Student student :_students_manager._students)
            if (student.id.equals(_id) && student.password.equals(_password)){
                student.Menu();
                logged_in = true;
            }

        if (logged_in == false){
            for (Doctor doctor : _doctors_manager._doctors)
                if (doctor.id.equals(_id) && doctor.password.equals(_password)){
                    doctor.Menu();
                    logged_in = true;
                }
        }
        if (logged_in == false)
            System.out.print("Incorrect ID or Password, Please try again\n\n\n\n");
        _system.main_Menu();
        return;
    }

    public void Sign_up(){
        String first_name, last_name, email, id, password;
        System.out.print("please Enter your first name: ");
        first_name = in.next();
        System.out.print("please Enter your last name: ");
        last_name = in.next();
        System.out.print("please Enter your email: ");
        email = in.next();
        System.out.print("please Enter your id: ");
        id = in.next();
        System.out.print("please Enter your password: ");
        password = in.next();

        int option;
        System.out.print("Are you :\n");
        System.out.print("\t1 - A student\n");
        System.out.print("\t2 - A doctor\n");
        System.out.print("Enter your choice: ");
        option = in.nextInt();

        while (option > 2 || option < 1){
            System.out.print("Invalid Choice, Please try again\n");
            System.out.print("Enter your choice: ");
            option = in.nextInt();
        }
        if (option == 1){
            for (Student _student: _students_manager._students)
                if (_student.id.equals(id)){
                    System.out.print("This id is already taken, Please sign up " +
                            "again with different id\n\n\n");
                    Sign_up();
                    return;
                }
            Student st = new Student(first_name, last_name, email, id, password);
            _students_manager.add_new_student(st);
        }
        else{
            for (Doctor _doctor : _doctors_manager._doctors)
                if (_doctor.id.equals(id)){
                    System.out.print("This id is already taken, " +
                            "Please sign up again with different id\n\n\n");
                    Sign_up();
                    return;
                }
            Doctor dtt = new Doctor(first_name, last_name, email, id, password);
            _doctors_manager.add_new_doctor(dtt);
        }
        System.out.print("You have successfully registered, please  log in now.\n\n\n\n\n");
        main_Menu();
        return;
    }

    public void load_doctors_courses_assignments() throws IOException {
        Scanner sc = new Scanner(new File("doctors_courses_assignments.txt"));

        String _first_name, _last_name, _email, _id, _pass, _course;
        int numberOfCourses;

        while(sc.hasNext()){
            String line = sc.nextLine();
            String[] data = line.split(" ");
            _first_name = data[0];
            _last_name = data[1];
            _email = data[2];
            _id = data[3];
            _pass = data[4];
            numberOfCourses = Integer.parseInt(data[5]);

            Doctor new_doctor = new Doctor(_first_name,_last_name,_email,_id,_pass);

            while (numberOfCourses-- > 0){

                String cos = sc.nextLine();
                String[] dos = cos.split(" ");
                Course __course= new Course();
                __course.course_name = dos[0];
                __course.course_code = dos[1];
                __course.course_doctor = _first_name + " " + _last_name;


                int numberOfAssignments;
                numberOfAssignments = Integer.parseInt(dos[2]);
                while (numberOfAssignments-- > 0){

                    AssignmentManager new_assignment = new AssignmentManager();
                    int i = 3;
                    while (!dos[i].equals("#")){
                        new_assignment.assignment_info += " " + dos[i];
                        i++;
                    }
                    i++;
                    new_assignment.max_grade = Integer.parseInt(dos[i]);
                    __course.course_assignments.add(new_assignment);
                }
                _courses_manager._courses.add(__course);
                new_doctor.created_courses.add(__course);
            }
            _doctors_manager._doctors.add(new_doctor);
        }
        sc.close();
        return;
    }

    public void load_students() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("students.txt"));


        String _first_name, _last_name, _email, _id, _pass;
        int numberOfCourses, _assignment_number, assignment_grade;
        String _course_name, _assignment_solution;
        boolean _assignment_solution_done;

        while (sc.hasNext()){
            String line = sc.nextLine();
            String[] data = line.split(" ");
            _first_name = data[0];
            _last_name = data[1];
            _email = data[2];
            _id = data[3];
            _pass = data[4];
            numberOfCourses = Integer.parseInt(data[5]);

            Student new_student = new Student(_first_name, _last_name, _email, _id, _pass);

            while (numberOfCourses-- > 0){
                String cos = sc.nextLine();
                String[] dos = cos.split(" ");
                _course_name = dos[0];
                _assignment_solution_done = Integer.parseInt(dos[1])>0?true:false;

                for (Course __course:_courses_manager._courses)
                    if (__course.course_name.equals(_course_name)){ // this is his course

                        if (_assignment_solution_done == true){
                            _assignment_number = Integer.parseInt(dos[2]);
                            _assignment_solution = dos[3];
                            assignment_grade = Integer.parseInt(dos[4]);

                            Assignment new_assignment = new Assignment();
                            new_assignment.grade = assignment_grade;
                            new_assignment.owner = new_student.id;
                            new_assignment.solution = _assignment_solution;

                            int assignments_counter = 1;

                            for (AssignmentManager _course_assignments_number:__course.course_assignments)
                                if (assignments_counter == _assignment_number){
                                    _course_assignments_number.assignment_submissions.add(new_assignment);
                                }
                        }

                        new_student.registered_courses.add(__course);

                        __course.registered_students.add(new_student);
                        break;
                    }
            }

            _students_manager._students.add(new_student);
        }
        sc.close();
        return;
    }

    public void show_students_data(){
        System.out.print("students in the system\n");
        for (Student _student : _students_manager._students)
            System.out.println(_student.id + " " + _student.password);
    }

    public void show_doctors_data(){
        System.out.print("doctors in the system\n");
        for (Doctor _doctor : _doctors_manager._doctors)
            System.out.println(_doctor.id + " " + _doctor.password);
    }

    void save_doctors_courses_assignments() throws IOException {
        FileWriter fw = new FileWriter("doctors_courses_assignments.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        for (Doctor _doctor : _doctors_manager._doctors){

            out.print(_doctor.first_name + " " + _doctor.last_name + " " +
                    _doctor.email + " " + _doctor.id + " " + _doctor.password + " " + _doctor.created_courses.size() + "\n");

            for (Course _course : _doctor.created_courses){

                out.print(_course.course_name + " " + _course.course_code + " ");

                if (_course.course_assignments.size() > 0){
                    out.print(_course.course_assignments.size() + " ");
                    for (AssignmentManager _course_assignment : _course.course_assignments){
                        out.print(_course_assignment.assignment_info + " " + _course_assignment.max_grade + " ");
                    }
                    out.print("\n");
                }else{
                    out.print("0"+ "\n");
                }
            }
        }
        out.close();
    }

    public void save_students() throws IOException {

        FileWriter fw = new FileWriter("students.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

        for (Student _student : _students_manager._students){
            out.print(_student.first_name + " " + _student.last_name + " " +
                    _student.email + " " + _student.id + " " + _student.password +
                    _student.registered_courses.size() + '\n');

        }
        out.close();
    }
}

