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
    private  ArrayList<Integer> days = new ArrayList<>();
    private float marginBottom = 0.0f;
    private float padding = 0.0f;

    public Course(String name, String info, String course_id, String course_number,
                  String instructor, String class_times, String exam_time, int units, int capacity,
                  int id) {
        this.name = name;
        this.info = info;
        this.course_id = course_id;
        this.course_number = course_number;
        this.instructor = instructor;
        this.class_times = calculate_time(class_times);
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

    private String calculate_time(String time){
        if (time.equals("[]"))
            return "";
        else{

            StringBuilder class_time = new StringBuilder();
            String day = "";
            Pattern p = Pattern.compile("\\d+\\.*\\d*");
            Matcher m = p.matcher(time);
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
            String res = class_time.toString().replaceAll("\\.",":");
            if (res.contains(":5")){
                res = res.replaceAll(":5",":30");
            }
            return res;
        }
    }

    public String getClass_times() {
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

    public float getTimeForSort(){
        return getStart_times().get(0);
    }

    public static Course getCourseById(int id){
        for (int i = 0; i < MainActivity.allCourses.size(); i++) {
            if (MainActivity.allCourses.get(i).getId() == id)
                return MainActivity.allCourses.get(i);
        }
        return null;
    }

    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public float getPadding() {
        return padding;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }


}

