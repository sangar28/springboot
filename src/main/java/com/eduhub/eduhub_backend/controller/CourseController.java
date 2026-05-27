package com.eduhub.eduhub_backend.controller;


import com.eduhub.eduhub_backend.component.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    static List<Course> courseList = new ArrayList<>();
    static
    {
        courseList.add(new Course("U23CS401","DSA",4));
        courseList.add(new Course("U23CS402","CC",3));
        courseList.add(new Course("U23CS403","SE",2));
        courseList.add(new Course("U23CS404","OOPs",4));
        courseList.add(new Course("U23CS405","PSC",3));
    }


    @GetMapping("course")
    public ResponseEntity<Course> getCourse()
    {
        Course course = new Course("U23CS409","DSA",4);
        return new ResponseEntity(course, HttpStatus.OK);
    }

    @GetMapping("courses")
    public ResponseEntity<List<Course>> getCourses()
    {
        List<Course> coursesList = new ArrayList<Course>();
        coursesList.add(new Course("U23CS401","DSA",4));
        coursesList.add(new Course("U23CS402","CC",3));
        coursesList.add(new Course("U23CS403","SE",2));
        coursesList.add(new Course("U23CS404","OOPs",4));
        coursesList.add(new Course("U23CS405","PSC",3));
        return new ResponseEntity<>(coursesList,HttpStatus.OK);
    }

    @GetMapping("courses/{courseCode}/{CourseName}/{credits}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseCode,@PathVariable String courseName,@PathVariable int credits)
    {
        Course course = new Course(courseCode,courseName,credits);
        return new ResponseEntity(course,HttpStatus.OK);
    }

    @PostMapping("courses/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {

        System.out.println(course.getCourseCode());
        System.out.println(course.getCourseName());
        System.out.println(course.getCredits());
        courseList.add(course);
        return ResponseEntity.ok(course);
    }

    @PutMapping("courses/{courseCode}/update")
    public ResponseEntity<Course> updateCourse(
            @PathVariable String courseCode,
            @RequestBody Course course) {

        return ResponseEntity.accepted().body(course);
    }
    @GetMapping("courses/getCourses/{courseCode}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseCode)
    {
        return courseList.stream().filter(c -> c.getCourseCode() .equalsIgnoreCase(courseCode)).findFirst().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }



}
