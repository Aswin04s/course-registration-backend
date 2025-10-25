package com.example.Course.Registration.System.Service;

import com.example.Course.Registration.System.Model.Course;
import com.example.Course.Registration.System.Model.CourseRegistry;
import com.example.Course.Registration.System.Repository.CourseRepository;
import com.example.Course.Registration.System.Repository.RegistryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepositoryObj;

    @Autowired
    RegistryRepo registryRepoObj;

    public List<Course> showCourses() {
        return courseRepositoryObj.findAll();
    }

    public List<CourseRegistry> enrolledStudents() { return registryRepoObj.findAll();}

    public void courseEnroll(String name,String emailId,String courseName) {
        CourseRegistry st = new CourseRegistry(name,emailId,courseName);

        registryRepoObj.save(st);

    }



    public String addCourse(String courseId, String courseName, String trainer, int durationInWeeks) {
        try {
            if (courseRepositoryObj.existsById(courseId)) {
                return "Error: Course ID already exists!";
            }

            Course course = new Course();
            course.setCourseId(courseId);
            course.setCourseName(courseName);
            course.setTrainer(trainer);
            course.setDurationInWeeks(durationInWeeks);

            courseRepositoryObj.save(course);
            return "Course added successfully!";
        } catch (Exception e) {
            return "Error adding course: " + e.getMessage();
        }
    }

    public String updateCourse(String courseId, String courseName, String trainer, int durationInWeeks) {
        try {
            Course course = courseRepositoryObj.findById(courseId).orElse(null);
            if (course != null) {
                course.setCourseName(courseName);
                course.setTrainer(trainer);
                course.setDurationInWeeks(durationInWeeks);
                courseRepositoryObj.save(course);
                return "Course updated successfully!";
            }
            return "Course not found!";
        } catch (Exception e) {
            return "Error updating course: " + e.getMessage();
        }
    }

    public String deleteCourse(String courseId) {
        try {
            if (courseRepositoryObj.existsById(courseId)) {
                courseRepositoryObj.deleteById(courseId);
                return "Course deleted successfully!";
            }
            return "Course not found!";
        } catch (Exception e) {
            return "Error deleting course: " + e.getMessage();
        }
    }

    public String updateStudent(int enrollmentId, String name, String emailId, String courseName) {
        try {
            CourseRegistry enrollment = registryRepoObj.findById(enrollmentId).orElse(null);
            if (enrollment != null) {
                enrollment.setName(name);
                enrollment.setEmailId(emailId);
                enrollment.setCourseName(courseName);
                registryRepoObj.save(enrollment);
                return "Student enrollment updated successfully!";
            }
            return "Enrollment not found!";
        } catch (Exception e) {
            return "Error updating student: " + e.getMessage();
        }
    }

    public String deleteStudent(int enrollmentId) {
        try {
            if (registryRepoObj.existsById(enrollmentId)) {
                registryRepoObj.deleteById(enrollmentId);
                return "Student enrollment deleted successfully!";
            }
            return "Enrollment not found!";
        } catch (Exception e) {
            return "Error deleting student: " + e.getMessage();
        }
    }
}
