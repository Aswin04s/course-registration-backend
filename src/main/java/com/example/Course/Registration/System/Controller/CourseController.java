package com.example.Course.Registration.System.Controller;

import org.springframework.ui.Model;
import com.example.Course.Registration.System.Model.Course;
import com.example.Course.Registration.System.Model.CourseRegistry;
import com.example.Course.Registration.System.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    CourseService courseServiceObj;


    @GetMapping("courses")
    @ResponseBody
    public List<Course> showCourses(){
        return courseServiceObj.showCourses();
    }

    @PostMapping("courses/register")
    public String courseEnroll(@RequestParam("name") String name,
                                @RequestParam("emailId") String emailId,
                                @RequestParam("courseName") String courseName,
                               Model model){

        courseServiceObj.courseEnroll(name,emailId,courseName);

        model.addAttribute("studentName", name);
        model.addAttribute("course", courseName);

        return "success";


    }

    @GetMapping("courses/enrolled")
    @ResponseBody
    public List<CourseRegistry> enrolledStudents(){
        return courseServiceObj.enrolledStudents();
    }

    // Add Course
    @PostMapping("courses/add")
    @ResponseBody
    public String addCourse(@RequestParam String courseId,
                            @RequestParam String courseName,
                            @RequestParam String trainer,
                            @RequestParam int durationInWeeks) {
        return courseServiceObj.addCourse(courseId, courseName, trainer, durationInWeeks);
    }

    // Update Course
    @PostMapping("courses/update")
    @ResponseBody
    public String updateCourse(@RequestParam String courseId,
                               @RequestParam String courseName,
                               @RequestParam String trainer,
                               @RequestParam int durationInWeeks) {
        return courseServiceObj.updateCourse(courseId, courseName, trainer, durationInWeeks);
    }

    // Delete Course
    @DeleteMapping("courses/delete/{courseId}")
    @ResponseBody
    public String deleteCourse(@PathVariable String courseId) {
        return courseServiceObj.deleteCourse(courseId);
    }

    // Update Student Enrollment
    @PostMapping("students/update")
    @ResponseBody
    public String updateStudent(@RequestParam int enrollmentId,
                                @RequestParam String name,
                                @RequestParam String emailId,
                                @RequestParam String courseName) {
        return courseServiceObj.updateStudent(enrollmentId, name, emailId, courseName);
    }

    // Delete Student Enrollment
    @DeleteMapping("students/delete/{enrollmentId}")
    @ResponseBody
    public String deleteStudent(@PathVariable int enrollmentId) {
        return courseServiceObj.deleteStudent(enrollmentId);
    }




}
