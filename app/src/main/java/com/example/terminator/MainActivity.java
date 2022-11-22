package com.example.terminator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private final List<Object> viewItems = new ArrayList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter = new Recycle(this, viewItems);
        mRecyclerView.setAdapter(mAdapter);
        callJSON();
    }

    private void callJSON() {
        try {
            String jsonDataString = readJSONFile();

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
                Log.d(LOG_TAG,name);
                Courses courses = new Courses(name,info,course_id,course_number,instructor,
                        class_times,exam_time,Integer.parseInt(units),Integer.parseInt(capacity),
                        Integer.parseInt(id));
                viewItems.add(courses);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder output = new StringBuilder();
        try {
            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.a);
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
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }


}

