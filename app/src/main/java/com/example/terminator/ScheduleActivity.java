package com.example.terminator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        String[] times = {
                "7:00",
                "8:00",
                "9:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
                "16:00",
                "17:00",
                "18:00",
                "19:00",
                "20:00",
        };
        ArrayList<String> days = new ArrayList<>(Arrays.asList("شنبه",
                "یکشنبه",
                "دوشنبه",
                "سه شنبه",
                "چهارشنبه",
                "پنجشنبه",
                "جمعه"));
        LinearLayout parent = (LinearLayout) findViewById(R.id.parent);
        parent.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 7; i++) {

            LinearLayout childLinear = new LinearLayout(this);
            childLinear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            childLinear.setOrientation(LinearLayout.VERTICAL);
            TextView title = new TextView(this);
            title.setText(days.get(i));
            title.setBackgroundColor(Color.parseColor("#016A69"));
            title.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.weight = 1.0f;
            params.gravity = Gravity.CENTER;
            params.setMargins(0, 0, 0, 16);
            title.setGravity(Gravity.CENTER);
            title.setPadding(0,10,0,10);
            title.setLayoutParams(params);

            LinearLayout horizontalLinear = new LinearLayout(this);
            horizontalLinear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            horizontalLinear.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout timeLinear = new LinearLayout(this);
            LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params4.weight = 6.0f;
            params4.setMargins(6,0,0,0);
            timeLinear.setLayoutParams(params4);
            timeLinear.setOrientation(LinearLayout.VERTICAL);
            for (int j = 0; j < times.length; j++) {
                TextView textView = new TextView(this);
                textView.setText(times[j]);

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params2.setMargins(0, 0, 0, 50);
                textView.setLayoutParams(params2);
                timeLinear.addView(textView);
            }

            RecyclerView recyclerView = new RecyclerView(this);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params3.weight = 1.0f;
            recyclerView.setVerticalScrollBarEnabled(true);
            recyclerView.setLayoutParams(params3);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            List<Object> courses = new ArrayList<>();
            List<Course> coursesTime = new ArrayList<>();

            Collections.sort(CourseDetailPopup.selectedCourses, new Comparator<Course>() {
                @Override
                public int compare(Course o1, Course o2) {
                    return Math.round(o1.getTimeForSort() - (o2.getTimeForSort()));
                }
            });

            for (int j = 0; j < CourseDetailPopup.selectedCourses.size(); j++) {
                if (CourseDetailPopup.selectedCourses.get(j).getDays().contains(i)) {
                    courses.add(CourseDetailPopup.selectedCourses.get(j));
                    coursesTime.add(CourseDetailPopup.selectedCourses.get(j));
                }
            }
            float pad = 0.0f;
            for (int j = 0; j < courses.size(); j++) {
                 pad = (coursesTime.get(j ).getEnd_times().get(0) -
                        coursesTime.get(j).getStart_times().get(0)) * 99;
                coursesTime.get(j).setPadding(pad);
            }

            for (int j = 0; j < courses.size(); j++) {
                if (j != courses.size() - 1) {
                    float dist = (coursesTime.get(j + 1).getStart_times().get(0) -
                            coursesTime.get(j).getEnd_times().get(0)) * 40;
                    coursesTime.get(j).setMarginBottom(dist);

                }
            }
            RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter = new ScheduleAdapter(this, courses);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setPadding(0,0,32,0);
            horizontalLinear.addView(timeLinear);
            horizontalLinear.addView(recyclerView);
            childLinear.addView(title);
            childLinear.addView(horizontalLinear);
            parent.addView(childLinear);

        }

    }

}