package com.example.Course.Registration.System.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Course {

    @Id
    private String courseId;
    private String courseName;
    private String trainer;
    private int durationInWeeks;

    public Course(String courseId,String courseName, String trainer, int durationInWeeks) {
        this.courseId = courseId;
        this.trainer = trainer;
        this.courseName = courseName;
        this.durationInWeeks = durationInWeeks;
    }

    public Course() {
    }


}
