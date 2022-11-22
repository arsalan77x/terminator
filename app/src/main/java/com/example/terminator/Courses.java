package com.example.terminator;

public class Courses {

    private final String name;
    private final String info;
    private final String course_id;
    private final String course_number;
    private final String instructor;
    private final String class_times;
    private final String exam_time;
    private final int units;
    private final int capacity;
    private final int id;


    public Courses(String name, String info, String course_id, String course_number,
                   String instructor, String class_times, String exam_time, int units, int capacity,
                    int id) {
        this.name = name;
        this.info = info;
        this.course_id = course_id;
        this.course_number = course_number;
        this.instructor = instructor;
        this.class_times = class_times;
        this.exam_time = exam_time;
        this.units = units;
        this.capacity = capacity;
        this.id = id;

    }
    public String getName() {
        return name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getInstructor() {
        return instructor;
    }

}

