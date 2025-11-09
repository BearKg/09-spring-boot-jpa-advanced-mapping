package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);
    Instructor findById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);
}
