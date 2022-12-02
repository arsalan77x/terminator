package com.example.terminator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

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

        LinearLayout parent = (LinearLayout) findViewById(R.id.parent);
        for (int i = 0; i < 7; i++) {

            LinearLayout childLinear = new LinearLayout(context);

            childLinear.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            childLinear.setOrientation(LinearLayout.VERTICAL);
            TextView title = new TextView(this);
            title.setText("شنبه");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 16);
            title.setLayoutParams(params);
            childLinear.addView(title);

            LinearLayout horizontalLinear = new LinearLayout(context);
            horizontalLinear.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            horizontalLinear.setOrientation(LinearLayout.HORIZONTAL);
            childLinear.addView(horizontalLinear);

            LinearLayout timeLinear = new LinearLayout(context);
            timeLinear.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            timeLinear.setOrientation(LinearLayout.VERTICAL);
            childLinear.addView(timeLinear);

            for (int i = 0; i < times.length; i++) {
                TextView textView = new TextView(this);
                textView.setText(times[i]);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(0, 0, 0, 50);
                textView.setLayoutParams(params);
                timeLinear.addView(textView);
            }


            parent.addView(childLinear);

        }

        LinearLayout saturdayLinear = (LinearLayout) findViewById(R.id.saturday_sub_linear);


        RecyclerView saturdayRecyclerview = (RecyclerView) findViewById(R.id.saturday_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        saturdayRecyclerview
                .setLayoutManager(layoutManager);
        List<Object> courses = new ArrayList<>(CourseDetailPopup.selectedCourses);
        RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter = new Recycle(this, courses);
        saturdayRecyclerview.setAdapter(mAdapter);
    }

}