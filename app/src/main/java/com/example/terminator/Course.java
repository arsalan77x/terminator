package com.example.terminator;

import android.icu.util.DateInterval;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {

    private final String name;
    private final String info;
    private final String course_id;
    private final String course_number;
    private final String instructor;
    private String class_times;
    private final String exam_time;
    private final int units;
    private final int capacity;
    private final int id;
    private ArrayList<Float> start_times = new ArrayList<>();
    private ArrayList<Float> end_times = new ArrayList<>();
    private final ArrayList<Integer> days = new ArrayList<>();


    public Course(String name, String info, String course_id, String course_number,
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

    public String getInstructor() {
        return instructor;
    }

    public String getInfo() {
        return info;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getCourse_number() {
        return course_number;
    }

    public String getClass_times() {
        StringBuilder class_time = new StringBuilder();
        String day = "";
        Pattern p = Pattern.compile("\\d+\\.*\\d*");
        Matcher m = p.matcher(class_times);
        int check = 0;
        while(m.find()) {
            if (m.group().equals("0")){
                day = "شنبه";
                days.add(0);
                class_time.deleteCharAt(class_time.length()-1);
                class_time.append(" ").append(day).append("\n");
            }
            else if (m.group().equals("1")){
                day = "یکشنبه";
                days.add(1);
                class_time.deleteCharAt(class_time.length()-1);
                class_time.append(" ").append(day).append("\n");
            }
            else if (m.group().equals("2")){
                day = "دوشنبه";
                days.add(2);
                class_time.deleteCharAt(class_time.length()-1);
                class_time.append(" ").append(day).append("\n");
            }
            else if (m.group().equals("3")){
                day = "سه شنبه";
                days.add(3);
                class_time.deleteCharAt(class_time.length()-1);
                class_time.append(" ").append(day).append("\n");
            }
            else if (m.group().equals("4")){
                day = "چهارشنبه";
                days.add(4);
                class_time.deleteCharAt(class_time.length()-1);
                class_time.append(" ").append(day).append("\n");
            }
            else if (m.group().equals("5")){
                day = "پنجشنبه";
                days.add(5);
                class_time.deleteCharAt(class_time.length()-1);
                class_time.append(" ").append(day).append("\n");
            }
            else if (m.group().equals("6")){
                day = "جمعه";
                days.add(6);
                class_time.deleteCharAt(class_time.length()-1);
                class_time.append(" ").append(day).append("\n");
            }
            else {
                class_time.append(m.group()).append("-");
                if (check == 0) {
                    start_times.add(Float.parseFloat(m.group()));
                    check = 1;
                } else {
                    end_times.add(Float.parseFloat(m.group()));
                    check = 0;
                }
            }
        }
        class_time.deleteCharAt(class_time.length() - 1);
        this.class_times = class_time.toString().replaceAll("\\.",":");
        if (this.class_times.contains(":5")){
            this.class_times = this.class_times.replaceAll(":5",":30");
        }
        return class_times;

    }

    public String getExam_time() {
        return exam_time;
    }

    public int getUnits() {
        return units;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Float> getStart_times(){
        return start_times;
    }
    public ArrayList<Float> getEnd_times(){
        return end_times;
    }
    public ArrayList<Integer> getDays(){
        return days;
    }

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    public boolean isIntervened(Course course1, Course course2){
        List<String> myList = new ArrayList<String>(Arrays.asList(course1.getClass_times().split("\n")));
        Log.d(LOG_TAG, myList.get(0));

        return true;
    }


}

