package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createCourseAndStudent(appDAO);
            // findCourseAndStudents(appDAO);
            // findStudentAndCourses(appDAO);
            addMoreCoursesForStudent(appDAO);

        };
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int theID = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theID);

        Course tempCourse = new Course("Rubik's Cube - How to Speed Cube");
        C
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        System.out.println("Loaded student: "+ tempStudent);
        System.out.println("Course: "+tempStudent.getCourses());
        System.out.println("Done!");
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int theId = 4;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded course: "+ tempCourse);
        System.out.println("Students: "+tempCourse.getStudents());
        System.out.println("Done!");
    }

    private void createCourseAndStudent(AppDAO appDAO) {
        Course tempCourse = new Course("Pacman - How to score one million points");
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "public", "mary.public@luv2code.com");

        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        System.out.println("Saving the course " +tempCourse);
        System.out.println("associated students " +tempCourse.getStudents());

        appDAO.saveCourse(tempCourse);
        System.out.println("Done!");
    }

    private void deleteCourseAndReview(AppDAO appDAO) {
        int theId = 3;
        appDAO.deleteCourseById(theId);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int theId = 3;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
        System.out.println("Done!");

    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course tempCourse = new Course("Pacman - How to score one million points");
        tempCourse.addReview(new Review("Great course...love it"));
        tempCourse.addReview(new Review("Cool course, job well done"));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.saveCourse(tempCourse);

    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 1;
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 1;
        Course tempCourse = appDAO.findCourseById(theId);
        // update the course
        System.out.println("Update course id: " + theId);
        tempCourse.setTitle("Enjoy the Simple Things");

        appDAO.update(tempCourse);
        System.out.println("Done");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 5;
        // find the instructor
        System.out.println("Finding instructor id: "+theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Updating instructor id: " +theId);
        tempInstructor.setLastName("TESTER");
        appDAO.update(tempInstructor);
        System.out.println("Done!");
    }

    private void findInstructorWithCourseJoinFetch(AppDAO appDAO) {
        int theId = 5;
        System.out.println("Finding instructor idL " +theId);

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " +tempInstructor.getCourses());
    }

    private void findCourseForInstructor(AppDAO appDAO) {
        int theId = 5;
        System.out.println("Finding instructor with id " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);

        // find courses for instructor
        System.out.println("Finding courses for instructor id " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void findInstructorWithCourse(AppDAO appDAO) {
        int theId = 5;
        System.out.println("Finding instructor with id " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated course: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // create the instructor
        Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Video Games");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Air guitar- The Ultimate Guide");
        Course tempCourse2 = new Course("the Pinball Masterclass");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        //
        // NOTE: this will ALSO save the courses
        // because of CascadeType.PERSIST
        //
        System.out.println("Saving instructor: " +tempInstructor);
        System.out.println("The course: "+tempInstructor.getCourses());

        appDAO.save(tempInstructor);
        System.out.println("Done!");

    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theID = 5;
        System.out.println("Deleting instructor detail id: " + theID);
        appDAO.deleteInstructorDetailById(theID);

        System.out.println("Done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        // get the instructor detail object
        int theId = 3;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        // print the instructor detail
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        // print the associated instructor
        System.out.println("the associated instructor: "+ tempInstructorDetail.getInstructor());
        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Delete instructor id: "+theId);
        appDAO.deleteInstructorById(theId);
        System.out.println("Done");
    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Instructor findById " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("Instructor findById " + tempInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
//        // create the instructor
//        Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
//
//        // create the instructor detail
//        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code!!!");

        // create the instructor
        Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        //
        // NOTE: this will ALSO save details object
        // because of CascadeType.ALL
        System.out.println("Saving Instructor" + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }
}
