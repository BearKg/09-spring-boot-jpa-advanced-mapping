package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createInstructor(appDAO);
            // findInstructor(appDAO);
            // deleteInstructor(appDAO);
            // findInstructorDetail(appDAO);
            // deleteInstructorDetail(appDAO);

            createInstructorWithCourses(appDAO);
        };
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
        int theID = 3;
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
        Instructor tempInstructor = appDAO.findById(theId);
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
