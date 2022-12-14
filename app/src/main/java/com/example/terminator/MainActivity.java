package com.example.terminator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final List<Object> viewItems = new ArrayList<>();
    public static ArrayList<Course> allCourses = new ArrayList<>();
    private static final String TAG = "MainActivity";
    public static SharedPreferences sp ;
    private final ArrayList<String> departments = new ArrayList<>(Arrays.asList("math","computer"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("courses",MODE_PRIVATE);
        Button goScheduleButton = (Button) findViewById(R.id.go_schedule_button);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.departments, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.dropdown_layout);
        spinner.setAdapter(adapter);
        Context c = getApplicationContext();


        for (int i = 0; i < departments.size(); i++) {
            String department = departments.get(i);
            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(c);
            mRecyclerView.setLayoutManager(layoutManager);
            viewItems.clear();
            callJSON(department);
            RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter = new Recycle(c, viewItems);
            mRecyclerView.setAdapter(mAdapter);
        }

        goScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseDetailPopup.isStarted = true;
                launchSecondActivity(v);
            }
        });




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                String department = "math";
                if (spinner.getSelectedItem().toString().equals("?????????????? ??????????"))
                    department = "math";
                else if  (spinner.getSelectedItem().toString().equals("?????????????? ????????????????"))
                    department = "computer";

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(c);
                mRecyclerView.setLayoutManager(layoutManager);
                viewItems.clear();
                callJSON(department);
                RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter = new Recycle(c, viewItems);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                callJSON("math");
            }

        });


        if (!CourseDetailPopup.isStarted){
            for (int i = 0; i < sp.getAll().size(); i++) {
                int id = sp.getInt("id"+i,0);
                if (Course.getCourseById(id) != null)
                    CourseDetailPopup.selectedCourses.add(Course.getCourseById(id));
            }
        }


    }

    private void callJSON(String department) {
        try {
            String jsonDataString = readJSONFile(department);
            JSONArray jsonArray = new JSONArray(jsonDataString);
            for (int i=0; i<jsonArray.length(); ++i) {
                JSONObject itemObj = jsonArray.getJSONObject(i);
                String name = itemObj.getString("name");
                String info = itemObj.getString("info");
                String course_number =  itemObj.getString("course_number");
                String course_id = itemObj.getString("course_id");
                String units = itemObj.getString("units");
                String capacity = itemObj.getString("capacity");
                String instructor = itemObj.getString("instructor");
                String class_times = itemObj.getString("class_times");
                String id = itemObj.getString("id");
                String exam_time = itemObj.getString("exam_time");
                Course courses = new Course(name,info,course_id,course_number,instructor,
                        class_times,exam_time,Integer.parseInt(units),Integer.parseInt(capacity),
                        Integer.parseInt(id));
                viewItems.add(courses);
                allCourses.add(courses);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONFile(String department) throws IOException{
        InputStream inputStream = null;
        StringBuilder output = new StringBuilder();
        int resId = this.getResources().getIdentifier(department, "raw", this.getPackageName());
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(resId);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((jsonString = bufferedReader.readLine()) != null) {
                output.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(output);
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, ScheduleActivity.class);
        startActivity(intent);
    }


}

