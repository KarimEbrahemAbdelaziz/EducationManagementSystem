package com.company;

import java.io.IOException;

public class Main {
    /**
     * static variables to use them in all classes in the project
     */
    public static DoctorsManager _doctors_manager = new DoctorsManager();
    public static CoursesManager _courses_manager = new CoursesManager();
    public static StudentManager _students_manager = new StudentManager();

    public static void main(String[] args) throws IOException {

        SystemFlow ne = new SystemFlow();
        ne.load_doctors_courses_assignments();
        ne.load_students();
        ne.show_students_data();
        ne.show_doctors_data();
        ne.main_Menu();
        ne.save_doctors_courses_assignments();
        ne.save_students();
    }
}
