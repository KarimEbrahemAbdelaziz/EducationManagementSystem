package com.company;

import java.util.ArrayList;

/**
 * Created by Karim Ebrahem on 08/01/2017.
 */
public class DoctorsManager {
    //public static DoctorsManager _doctors_manager = new DoctorsManager();
    public ArrayList<Doctor> _doctors = new ArrayList<Doctor>();

    public DoctorsManager(){}

    public void add_new_doctor(Doctor doc){
        Doctor ne = new Doctor(doc);
        _doctors.add(ne);
        return;
    }
}
